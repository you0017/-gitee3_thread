package com.yc.thread;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 */
public class Test5_pool {
    public static void main(String[] args) {
        //核心线程池大小
        int corePoolSize = 3;
        //核心线程池最大线程数
        int maxPoolSize = 5;
        //线程池最大空闲时间
        long keepAliveTime = 10;
        //时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        //阻塞队列，容量为2  最多允许放入两个空闲任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);//同时最大任务量为7   ->  超过7个任务，则拒绝执行
        //线程创建工程
        ThreadFactory threadFactory = new NameThreadFactory();
        //线程池拒绝策略
        RejectedExecutionHandler handler = new MyIgnorePolicy();

        ThreadPoolExecutor executor = null;
        try {
            //推荐的创建线程池的方式
            //不推荐使用现成的api创建线程池
            executor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,unit,workQueue,threadFactory,handler);

            //预启动所有核心线程 提升效率
            executor.prestartAllCoreThreads();
            //任务数量
            int count = 10;
            for (int i = 0; i < count; i++) {
                MyIgnorePolicy.Task task = new MyIgnorePolicy.Task(String.valueOf(i));

                executor.submit(task);//线程池中最多同时执行5个任务+2个队列   提交任务到线程池  还有3个任务无法执行
            }
        }finally {
            assert executor != null;  //断言可开关 vm设置参数 -ea开  -da关(默认
            executor.shutdown();
        }
    }

    /**
     * 线程工厂
     */
    static class NameThreadFactory implements ThreadFactory{
        //线程id  AtomicInteger 原子类  原子整型类
        private final AtomicInteger threadId = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable runnable){
            Thread t = new Thread(runnable,"线程-"+threadId.getAndIncrement());//i++ => i  I+1 ->赋值
            System.out.println(t.getName()+"已经被创建");
            return t;
        }
    }

    /**
     * 拒绝策略
     */
    public static class MyIgnorePolicy implements RejectedExecutionHandler{
        @Override                       //被拒绝的任务     线程池对象
        public void rejectedExecution(Runnable runnable,ThreadPoolExecutor e){
            doLog(runnable,e);
        }
        private void doLog(Runnable runnable,ThreadPoolExecutor e){
            //可做记录日志等
            System.err.println("线程池："+e.toString()+runnable.toString()+"被拒绝执行");
        }

        //任务类
        static class Task implements Runnable{
            private String name;
            public Task(String name){
                this.name = name;
            }
            @Override
            public void run(){
                try {
                    System.out.println(this.toString()+"is running!");
                    //
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            @Override
            public String toString(){
                return "RunnableTask [name="+name+"]";
            }
        }
    }
}
