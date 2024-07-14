package com.yc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 带返回值的任务类
 */
public class Test4_callable_FutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //方式一：内部类
        FutureTask<Integer> task1 = new FutureTask<>(new Callable<Integer>() {

            //任务功能是累加和
            @Override
            public Integer call() throws Exception {
                int count = 0;
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    count+=i;
                    /*if (i==10){
                        throw new RuntimeException("error");
                    }*/
                }
                return count;
            }
        });

        //将任务类和线程绑定
        Thread t1 = new Thread(task1);
        t1.start();
        //有返回值
        System.out.println("累加和为："+task1.get());
        System.out.println("请理解 什么是阻塞式");
    }
}
