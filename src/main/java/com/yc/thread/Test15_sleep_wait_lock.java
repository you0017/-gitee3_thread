package com.yc.thread;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * sleep与wait方法在锁上的区别
 */
public class Test15_sleep_wait_lock {
    /**
     * 此方法会被n个线程调用，会发生争抢，同时只能一个线程进行执行
     * sleep()方法不会释放资源·因此线程是按照先后顺序执行的
     * synchronized的作用：加锁
     */
    //public void run(){}

    public synchronized void sleepMethod(){//synchronized -> Test15这个对象的锁
        System.out.println(printDate() + Thread.currentThread().getName()+"休眠1s");
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(printDate()+Thread.currentThread().getName()+"休眠结束");
    }

    /**
     * wait()方法会释放锁·因此一旦用了wait()方法就会造成其他线程运行
     * 同步方法
     */
    public void waitMethod(){//TODO public synchronized void waitMethod()
        System.out.println(printDate() + Thread.currentThread().getName()+"等待1s");
        synchronized (this){
            try {
                this.wait(1000);//释放锁 wait与sleep区别
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(printDate()+Thread.currentThread().getName()+"等待结束");
    }

    public static void main(String[] args) {
        /*final Test15_sleep_wait_lock test1 = new Test15_sleep_wait_lock();//new一次：一个对象，一把锁
        for (int i = 0; i < 5; i++) {
            //不推荐直接创建线程-限于篇幅·这里会直接创建线程
            //推荐使用线程池管理线程·Runnable是支持函数式编程的接口
            //Runnable对象 void run();
            //  void sleepMethod()
            new Thread(test1::sleepMethod).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    test1.sleepMethod();
                }
            });
        }*/


        //测试另一种情况 wait

        System.out.println("----------------------------------------");
        final Test15_sleep_wait_lock test2 = new Test15_sleep_wait_lock();
        for (int i = 0; i < 5; i++) {
            //不推荐直接创建线程
            //要用线程池
            new Thread(test2::waitMethod).start();
        }
    }


    private static String printDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss s");
        return sdf.format(new Date())+"";
    }

}
