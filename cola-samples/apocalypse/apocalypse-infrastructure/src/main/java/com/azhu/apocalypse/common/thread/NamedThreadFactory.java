package com.azhu.apocalypse.common.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程命名工厂
 *
 * @version 2020-02-21
 * @author hanquan
 */
public class NamedThreadFactory implements ThreadFactory {

    private final AtomicInteger threadNum = new AtomicInteger(1);

    private final String prefix;

    private final boolean daemo;

    private final ThreadGroup threadGroup;

    public NamedThreadFactory(String prefix) {
        this(prefix, false);
    }

    public NamedThreadFactory(String prefix, boolean daemon) {
        if (null == prefix || prefix.isEmpty()) {
            throw new NullPointerException("Your prefix is Blank!");
        }
        final String suffix = "-thread-";
        this.prefix = new StringBuilder(prefix.length() + suffix.length()).append(prefix).append(suffix).toString();
        this.daemo = daemon;
        final SecurityManager s = System.getSecurityManager();
        this.threadGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();

    }

    @Override
    public Thread newThread(Runnable runnable) {
        String name = prefix + threadNum.getAndIncrement();
        Thread th = new Thread(threadGroup, runnable, name, 0);
        th.setDaemon(daemo);
        return th;
    }
}
