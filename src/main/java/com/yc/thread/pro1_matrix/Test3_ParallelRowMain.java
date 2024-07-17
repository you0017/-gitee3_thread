package com.yc.thread.pro1_matrix;

import com.yc.thread.pro1_matrix.row.ParallelRowMultiplier;

public class Test3_ParallelRowMain {
    public static void main(String[] args) {
        double[][] matrix1 = MatrixGenerator.generateMatrix(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generateMatrix(2000, 2000);

        double result[][] = new double[matrix1.length][matrix2.length];

        long start,end;
        start = System.currentTimeMillis();

        //每行一个线程
        ParallelRowMultiplier.multiply(result, matrix1, matrix2);

        end = System.currentTimeMillis();
        System.out.println("按矩阵结果的行生成任务乘法器：" + (end - start) + "ms");

    }
}
