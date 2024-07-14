package com.yc.thread;

import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Runnable可运行的任务
 */
public class Test2_Runnable {
    public static void main(String[] args) {
        //创建一个线程，绑定任务
        //语法一：
        Runnable task = new ShowTimeTask();

        Thread t = new Thread(task);
        t.start();

        //语法二：
        Runnable task2 = new ShowTimeTaskInner();
        Thread t2 = new Thread(task2);
        t2.start();

        //语法三：
        //匿名内部类写法
        Thread t3 = new Thread(new Runnable() {
            private boolean flag;

            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
                Date d = null;
                while (!flag) {
                    d = new Date();
                    System.out.println(Thread.currentThread().getName() + "输出当前时间：" + sdf.format(d));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        t3.start();


        //语法四：
        //因为Runnable接口上有@FunctionInterface 表示这个接口支持lambda语法
        Thread t4 = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
            Date d = null;
            while (true) {
                d = new Date();
                System.out.println(Thread.currentThread().getName() + "输出当前时间：" + sdf.format(d));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t4.start();
    }

    //内部类写法
    static class ShowTimeTaskInner implements Runnable {
        private boolean flag;

        @Override
        public void run() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
            Date d = null;
            while (!flag) {
                d = new Date();
                System.out.println(Thread.currentThread().getName() + "输出当前时间：" + sdf.format(d));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

//从任务角度来看
class ShowTimeTask implements Runnable {

    private boolean flag;

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        Date d = null;
        while (!flag) {
            d = new Date();
            System.out.println(Thread.currentThread().getName() + "输出当前时间：" + sdf.format(d));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}