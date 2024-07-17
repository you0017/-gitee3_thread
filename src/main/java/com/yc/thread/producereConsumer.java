package com.yc.thread;

/**
 * 生产者和消费者
 */
public class producereConsumer {
    public static void main(String[] args) {
        AppleBox ab = new AppleBox();

        //创建生产者
        Producer p = new Producer(ab);
        Producer p2 = new Producer(ab);
        //不管有多少个Producer，操作同一个appleBox
        //创建消费者
        Consumer c1 = new Consumer(ab);

        //以上是任务，要用线程绑定
        new Thread(p).start();
        new Thread(p2).start();
        new Thread(c1).start();
    }
}

class Apple {
    int id;

    Apple(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "id=" + id +
                '}';
    }
}


//消息中间件：存消息（apple
class AppleBox {
    Apple[] apples = new Apple[5];

    int index = 0;//当前有几条消息

    //存消息的方法
    public synchronized void deposite(Apple apple) throws InterruptedException {
        while (index == apples.length) {
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
            this.wait();
        }
        apples[index] = apple;
        this.notifyAll();//通知其他线程可以继续
        index++;
    }


    //消费消息
    public synchronized Apple withDraw() throws InterruptedException {
        while (index == 0) {
            /*try {
                Thread.sleep(1000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }*/
            this.wait();
        }
        index--;
        this.notifyAll();
        return apples[index];
    }
}

//生产消息的任务
class Producer implements Runnable {
    AppleBox ab = null;//生产完的消息要存放到applebox中

    public Producer(AppleBox ab) {
        this.ab = ab;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Apple a = new Apple(i);
            try {
                ab.deposite(a);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "生产了消息：" + a);//a.toString()
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer implements Runnable {
    AppleBox ab = null;

    public Consumer(AppleBox ab) {
        this.ab = ab;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            Apple apple = null;
            try {
                apple = ab.withDraw();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "消费了：" + apple);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
