package com.yc.thread.pro1_matrix.group;

import java.util.ArrayList;
import java.util.List;

public class ParallelGroupMultiplier {
    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {
        List<Thread> threads = new ArrayList<>();


        int rows1 = matrix1.length;  //第一个矩阵的行
        int cols1 = matrix2.length;//第一个矩阵的列

        int rows2 = matrix2[0].length;//第二个矩阵的行
        int cols2 = matrix2[0].length;//第二个矩阵的列

        //获取当前主机cpu的核数
        int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("总共有" + numThreads + "个核数");
        //计算每个线程要执行运算的行的起始和终止
        int startIndex, endIndex, step;
        step = rows1 / numThreads;
        startIndex = 0;
        endIndex = step;
        //循环numThreads次，每次启动一个线程，指定此线程运行的范围
        for (int i = 0; i < numThreads; i++) {
            //启动任务
            Thread t = new Thread(new GroupMultiplierTask(result, matrix1, matrix2, startIndex, endIndex));
            t.start();
            threads.add(t);

            //计算下一个线程的范围
            startIndex = endIndex;
            endIndex = i == numThreads - 2 ? rows1 : startIndex + step;

            System.out.println("第"+i+"个线程的计算范围为："+startIndex + "->" + endIndex);
            /*if (threads.size() % 10 == 0) {
                waitForAll(threads);
            }*/
        }
        waitForAll(threads);
    }

    private static void waitForAll(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();//主线程下面的所有子线程运行先运行，主线程线程先等
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
