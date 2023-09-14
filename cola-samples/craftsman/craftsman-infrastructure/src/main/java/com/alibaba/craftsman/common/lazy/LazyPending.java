package com.alibaba.craftsman.common.lazy;

import com.alibaba.craftsman.common.thread.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public abstract class LazyPending<T> {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /** 任务堆积区*/
    protected LinkedBlockingQueue<T> entityQueue;

    /** 待处理的任务区*/
    private List<T> buffer;

    /** 线程池：真正去执行实体任务*/
    protected ExecutorService callExecutor;

    private Semaphore semaphore;

    /** 是否应用多线程*/
    private boolean useTaskPool;

    private NamedThreadFactory namedThreadFactory;

    private int taskTimeout;

    /** 是否已加载标识：false-没有加载， true-已加载*/
    private final AtomicBoolean loaded = new AtomicBoolean(false);

    private long lastTaskTime;

    private String _name;

    /** 单线程任务：触发buffer中的任务，让callExecutor真正去执行实体任务*/
    private PushTask pushTask;

    /**
     * 初始化线程
     */
    public void init() {
        if (loaded.compareAndSet(false, true)) {
        	taskTimeout = taskTimeout();
        	if (taskTimeout < 10) {
        		taskTimeout = 1000;
        	}
        	int maxEntitySize = maxEntitySize();
        	if (maxEntitySize <= 10) {
        		maxEntitySize = Integer.MAX_VALUE;
        	}
            _name = getName();
        	entityQueue = new LinkedBlockingQueue<>(maxEntitySize);
        	namedThreadFactory = new NamedThreadFactory(_name);
            pushTask = new PushTask();
        	Thread bufferReadTask = namedThreadFactory.newThread(pushTask);
        	bufferReadTask.start();

            int callThreads = callThreads();
            useTaskPool = callThreads > 0;
            if (callThreads() > 0) {
                callExecutor = Executors.newFixedThreadPool(callThreads, new NamedThreadFactory(_name + "-call"));
                semaphore = new Semaphore(callThreads);
            }

            logger.info("init success...");
        }
    }

    public abstract String getName();

    public int maxEntitySize() {
    	return 100;
    }

    public int taskTimeout() {
    	return 1000;
    }



    /**
     * 堆积任务
     *
     * @param params
     * @return
     */
    public int doPending(T params) {
        if (!loaded.get()) { // 未被化始会无法下发
            logger.warn(getClass() + " is uninitialized, init now...");
            init();
        }
        if (null == entityQueue) {    //  destroy过
            return -1;
        }
        try {
			entityQueue.put(params);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        if (logger.isDebugEnabled()) {
            logger.info("{} entityQueue {} ... ", new Object[]{_name, entityQueue.size()});
        }
        return entityQueue.size();
    }

    public boolean isDataReady(List<T> readyData) {
        boolean res = readyData.size() >= packageSize();
        if (res) {
            if (logger.isDebugEnabled()) {
                logger.debug("is data ready");
            }
        }
        return res;
    }

    public boolean isTimeReady(long lastTaskTime) {
        boolean res = System.currentTimeMillis() - lastTaskTime > taskTimeout;
        if (res) {
            if (logger.isDebugEnabled()) {
                logger.debug("is time ready");
            }
        }
        return res;
    }

    protected abstract class PendingTask implements Runnable {

        private boolean run = true;

        public PendingTask() {
            run = true;
        }

        public void stopRun() {
            run = false;
        }

        public abstract void doTask();

        @Override
        public void run() {
            for (;run;) {
                doTask();
            }
        }
    }

    /**
     * 推送任务
     *
     * @author tuoxie
     */
    public class PushTask extends PendingTask {

        public PushTask() {
            super();
            buffer = new ArrayList<>();
        }

        @Override
        public void doTask() {
            try {
                if(null == entityQueue){
                    pushTask.stopRun();
                    return;
                }
            	T t = entityQueue.poll(taskTimeout, TimeUnit.MILLISECONDS);
                boolean isEmptyQueue = null == t;
            	if (!isEmptyQueue) {
                    buffer.add(t);
                }
                if (logger.isDebugEnabled()) {
                    logger.info("{} poll {} tasks ... ", new Object[]{_name, buffer.size()});
                }
                if (!CollectionUtils.isEmpty(buffer) && (isEmptyQueue || isTimeReady(lastTaskTime) || isDataReady(buffer))) {
            	    // buffer不为空的情况下，超时取不到内容，或者数据已经准备好就flush
                    lastTaskTime = System.currentTimeMillis();
                	List<T> tempBuffer = buffer;
                    buffer = new ArrayList<>();
                    if (useTaskPool) {
                    	try {
                    		semaphore.acquire();
                    	} catch (InterruptedException ee) {
                    		logger.error("semaphore acquire fail", ee);
                    	}
                    	callExecutor.execute(new CallTask(tempBuffer));
                    } else {
                    	call(tempBuffer);
                    }
                }
            } catch (Exception e) {
                logger.error("what happen ?", e);
            }
        }
    }

    private class CallTask implements Runnable {

        private List<T> buffer = null;

        public CallTask(List<T> buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
        	try {
        		call(buffer);
        	} finally {
        		if (null != semaphore) {
        			semaphore.release();
        		}
        	}
        }
    }

    public void destroy() {

        if (null != pushTask) {
        	pushTask.doTask();
            pushTask.stopRun();
        }
        if (null != callExecutor) {
            callExecutor.shutdown();
        }
        if (null != entityQueue) {
            entityQueue.clear();
            entityQueue = null;
        }
        if (null != buffer) {
            buffer.clear();
            buffer = null;
        }
        semaphore = null;
        namedThreadFactory = null;
    }

    public abstract void call(List<T> sendData);

    /**
     * 合并包大小
     *
     * @return
     */
    public abstract int packageSize();

    public abstract int callThreads();
}
