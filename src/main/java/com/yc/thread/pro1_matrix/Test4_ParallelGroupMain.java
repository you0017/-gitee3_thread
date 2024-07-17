package com.yc.thread.pro1_matrix;

import com.yc.thread.pro1_matrix.group.ParallelGroupMultiplier;

public class Test4_ParallelGroupMain {
    public static void main(String[] args) {
        double[][] matrix1 = MatrixGenerator.generateMatrix(3000, 3000);
        double[][] matrix2 = MatrixGenerator.generateMatrix(3000, 3000);

        double result[][] = new double[matrix1.length][matrix2.length];

        long start,end;
        start = System.currentTimeMillis();

        //每行一个线程
        ParallelGroupMultiplier.multiply(result, matrix1, matrix2);

        end = System.currentTimeMillis();
        System.out.println("按cpu核数生成任务乘法器：" + (end - start) + "ms");//135094ms

    }
}
