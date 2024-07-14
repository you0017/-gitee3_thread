package com.yc.thread;

public class Test6_Interrupt {
    public static void main(String[] args) {
        //通过interrupter()方法检测线程是否被中断
        //Tread.currentThread() 主线程
        System.out.println(Thread.currentThread().getName()+"线程是否中断："+Thread.interrupted());

        //设置main线程中断信号
        Thread.currentThread().interrupt();

        //main线程
        //Thread.currentThread.stop();  //不能红stop()终止

        //通过interrupted()方法检测线程是否被中断   interrupter()也可以检测中断状态  同时可以清除中断状态
        System.out.println(Thread.currentThread().getName() + "线程是否中断："+Thread.interrupted());//返回true后恢复状态
        //检测interrupted()是否清除线程状态
        System.out.println(Thread.currentThread().getName() + "线程是否中断："+Thread.interrupted());

        //对比：isInterrupted()检测此线程是否终端  但不会清除中断状态
        //检测当前线程是否被中断
        //System.out.println(Thread.currentThread().getName() + "线程是否中断："+Thread.currentThread().isInterrupted());//true
        //检测是否被清除中断状态
        //System.out.println(Thread.currentThread().getName() + "线程是否中断："+Thread.currentThread().isInterrupted());//true
    }
}
