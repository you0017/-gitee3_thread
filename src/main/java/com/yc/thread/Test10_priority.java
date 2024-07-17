package com.yc.thread;

/**
 * 线程优先级
 */
public class Test10_priority {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.setPriority(1);
    }
}
