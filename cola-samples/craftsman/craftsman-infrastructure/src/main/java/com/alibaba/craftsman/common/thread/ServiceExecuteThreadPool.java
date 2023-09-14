package com.alibaba.craftsman.common.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 全局线程池
 *
 * @author hanquan
 * @version 2020-02-28
 */
@Slf4j
public enum ServiceExecuteThreadPool {
	instance;

	private static final int DEFAULT_MONITOR_PERIOD = 60;
	private final static int corePoolSize = 10;
	private final static int maxPoolSize = 300;
	/** 监控时间间隔，单位：s*/
	private int monitorPeriod;
	private AtomicLong rejectedTaskCount = new AtomicLong(0);

	private ThreadPoolExecutor threadPool;
	private ScheduledExecutorService monitor;
	private Runnable monitorTask = new Runnable() {
		@Override
		public void run() {
			try {
				int activeCount = threadPool.getActiveCount(); 					// 正在执行的任务数
				long completedTaskCount = threadPool.getCompletedTaskCount(); 	// 已完成任务数
				long totalTaskCount = threadPool.getTaskCount(); 				// 总任务数
				int largestPoolSize = threadPool.getLargestPoolSize(); 			// 最大线程数
				int poolSize = threadPool.getPoolSize();
				log.warn("active_thread:{}, waiting_thread:{}, completed_thread:{}, rejected_thread:{}, current_pool_size:{}, largest_pool_size:{}",
						new Object[]{activeCount, totalTaskCount - activeCount - completedTaskCount, completedTaskCount, rejectedTaskCount.get(), poolSize, largestPoolSize});
			} catch (Exception e) {
				log.error("[SYSTEM-SafeGuard]Monitor thread run fail", e);
			}
		}
	};

	private ServiceExecuteThreadPool() {
		threadPool = new ThreadPoolExecutor(corePoolSize,     // 核心线程数
											maxPoolSize,      // 最大线程数
											5L,               // 存活时间
											TimeUnit.MINUTES, // 存活时间单位
											new ArrayBlockingQueue<Runnable>(300),    // 等待队列
											new NamedThreadFactory("ServiceProcessor"), // 命名工厂
											new ThreadPoolExecutor.CallerRunsPolicy()         // 拒绝策略
		) {

			@Override
			protected void beforeExecute(Thread t, Runnable r) {
				log.debug("{} start.", t.getName());
			}

			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				log.debug("{} finish.", Thread.currentThread().getName());
			}

			@Override
			protected void terminated() {
				super.terminated();
			}
		};

		monitorPeriod = monitorPeriod <= 0 ? DEFAULT_MONITOR_PERIOD : monitorPeriod;
		monitor = Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("solution-processor-monitor", true));
		monitor.scheduleAtFixedRate(monitorTask, monitorPeriod, monitorPeriod, TimeUnit.SECONDS);
	}

	public <T> Future<T> submit(@NonNull Callable<T> task) {
		return threadPool.submit(task);
	}

	public void destroy() {
		log.warn("start to stop thread pool");
		threadPool.shutdown();
		log.warn("finish to stop thread pool");
	}
}
