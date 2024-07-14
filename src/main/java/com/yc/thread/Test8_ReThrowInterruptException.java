package com.yc.thread;

public class Test8_ReThrowInterruptException {
    public static void main(String[] args) throws InterruptedException {
        //当前线程
        Thread thread = Thread.currentThread();
        try {
            //检查当前线程是否被中断
            System.out.println(thread.getName() + "线程是否中断：" + thread.isInterrupted());//false
            //设置中断信号
            thread.interrupt();
            Thread.sleep(3000);
        }catch (Exception e){
            System.out.println(thread.getName()+"抛出InterruptedException中断异常");
            System.out.println(thread.getName()+"做一些清理工作");

            //也可以将中断异常向上继续抛出
            //throw e;
        }

        System.out.println("主程序还能正常执行嘛");
    }
}
