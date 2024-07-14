package com.yc.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test9_ReInterrupted extends Thread{
    public static void main(String[] args) throws InterruptedException {
        //当前线程main
        String threadName = Thread.currentThread().getName();

        Test9_ReInterrupted reInterrupted = new Test9_ReInterrupted();//创建线程
        System.out.println(printDate() + threadName +"线程启动");

        //启动新线程
        reInterrupted.start();


        //main主线程休眠3秒
        Thread.sleep(3000);
        System.out.println(printDate() + threadName + "发送中断信号，设置子线程中断");
        //对新线程设置线程中断
        reInterrupted.interrupt();

        //主线程休眠3秒
        //Thread.sleep(3000);
        System.out.println(printDate() + threadName + "运行结束");
    }

    @Override
    public void run(){
        //当前线程
        String threadName = Thread.currentThread().getName();
        int i=0;
        //while循环等待线程中断
        while (!Thread.currentThread().isInterrupted()){
            System.out.println(printDate()+threadName+"线程正在执行第："+(++i)+"次");
            try {
                //应该会执行三次
                Thread.sleep(1000);
            }catch (Exception e){//因为catch，所以中断信号会恢复为false
                System.out.println(printDate()+threadName+"线程正在执行，收到中断信号，进入catch块处理");
                //检测线程是否中断
                System.out.println(printDate()+threadName+"的状态："+this.isInterrupted());//false
                //如果需要维护中断状态，则需要重新设置中断状态
                //TODO:如果不需要，则不用调用   如果调用interrupt()的话，则当前线程的状态变为中断·这个while循环退出
                Thread.currentThread().interrupt();//true //TODO：如果注释这一句话，则中断不起作用·while循环人会继续·想象一下，这对一些偶然出现的小问题并不会让任务结束
            }
        }
        System.out.println(printDate()+threadName+"线程是否被中断："+this.isInterrupted());
        System.out.println(printDate()+threadName+"线程退出");
    }

    private static String printDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date())+"";
    }
}
