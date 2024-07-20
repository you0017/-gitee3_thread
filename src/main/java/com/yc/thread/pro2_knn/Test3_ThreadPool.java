package com.yc.thread.pro2_knn;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Test3_ThreadPool {
    public static void main(String[] args) {
        //线程池的引入
        //问题:程序中创建的县城太多了
        //复习线程池智识
        //Array->Arrays
        //Collection->Collections
        //拓展：ThreadPoolExecutor的快速线程池创建工具类  Executors
        //Executors.newFixedThreadPool();//Fixed固定线程数
        //Executors.newCachedThreadPool();//带缓冲的
        //Executors.newSingleThreadExecutor();//单线程
        //Executors.newWorkStealingPool();//
        //线程数要是核数的1.5倍最好
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool((int) (Runtime.getRuntime().availableProcessors() * 1.5));
        executor.submit(new RunnableTask("任务类"));

        executor.shutdown();
    }

    static class RunnableTask implements Runnable {
        private String name;

        public RunnableTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.toString());
                //让任务执行慢一点
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "RunnableTask{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}