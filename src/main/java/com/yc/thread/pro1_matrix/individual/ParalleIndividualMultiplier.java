package com.yc.thread.pro1_matrix.individual;

import java.util.ArrayList;
import java.util.List;

/**
 * 并行的针对结果矩阵中每个元素一个线程的乘法器
 */
public class ParalleIndividualMultiplier {
    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {
        List<Thread> threads = new ArrayList<>();


        int rows1 = matrix1.length;  //第一个矩阵的行
        int cols1 = matrix2.length;//第一个矩阵的列

        int rows2 = matrix2[0].length;//第二个矩阵的行
        int cols2 = matrix2[0].length;//第二个矩阵的列

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                //创建线程
                Thread thread = new Thread(new IndividualMultiplierTask(result, matrix1, matrix2, i, j));
                thread.start();

                threads.add(thread);

                if (threads.size()%10==0){
                    waitForAll(threads);
                }
            }
        }
    }

    private static void waitForAll(List<Thread> threads){
        for (Thread thread : threads) {
            try {
                thread.join();//t先运行，其他的线程先等
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
