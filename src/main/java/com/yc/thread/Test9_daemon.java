package com.yc.thread;

/**
 * 精灵线程
 */
public class Test9_daemon {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.setDaemon(true);//默认的线程是子线程结束，主线程才能结束
        //精灵线程是主线程结束，精灵线程也结束
        //一般用来做资源释放和垃圾回收
    }
}
