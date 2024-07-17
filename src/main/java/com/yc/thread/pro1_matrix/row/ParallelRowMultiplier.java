package com.yc.thread.pro1_matrix.row;

import java.util.ArrayList;
import java.util.List;

/**
 * 并行第二版：   结果矩阵中的每一行一个任务
 */
public class ParallelRowMultiplier {
    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {
        List<Thread> threads = new ArrayList<>();


        int rows1 = matrix1.length;  //第一个矩阵的行
        int cols1 = matrix2.length;//第一个矩阵的列

        int rows2 = matrix2[0].length;//第二个矩阵的行
        int cols2 = matrix2[0].length;//第二个矩阵的列

        for (int i = 0; i < rows1; i++) {
            //创建线程
            //总共两千个线程
            Thread thread = new Thread(new RowMultiplierTask(result, matrix1, matrix2, i));
            thread.start();

            threads.add(thread);

            if (threads.size() % 10 == 0) {
                waitForAll(threads);
            }
        }
    }

    private static void waitForAll(List<Thread> threads) {
        for (Thread thread : threads) {
            try {
                thread.join();//t先运行，其他的线程先等
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
