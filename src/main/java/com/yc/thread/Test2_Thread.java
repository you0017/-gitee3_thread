package com.yc.thread;

public class Test2_Thread {
    public static void main(String[] args) {
        System.out.println("主方法开始");
        Thread t = new MyThread();
        t.start();
        //简写成 new MyThread().start();

        //内部类
        InnerThread it = new InnerThread();
        it.start();

        //写法：匿名内部类
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("匿名Thread:" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();
    }

    //因为要在main()这个静态方法中调用这个内部类，所以这个内部类声明要加静态static
    //内部类写法：只有这个Test1_thread这个类才会用到InnerThread
    static class InnerThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("InnerThread:" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

//方案一：外部类写一个类继承自Thread，重写run()方法，在这个方法放入耗时操作或阻塞操作
//MyThread.start() -> start0() native cpp  -> run()

class MyThread extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("MyThread:" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
	}
}