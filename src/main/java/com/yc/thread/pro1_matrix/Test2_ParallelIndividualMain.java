package com.yc.thread.pro1_matrix;

import com.yc.thread.pro1_matrix.individual.ParalleIndividualMultiplier;

/**
 * 每个元素一个线程目前是4000000个线程
 */
public class Test2_ParallelIndividualMain {
    public static void main(String[] args) {
        double[][] matrix1 = MatrixGenerator.generateMatrix(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generateMatrix(2000, 2000);

        double result[][] = new double[matrix1.length][matrix2.length];

        long start,end;
        start = System.currentTimeMillis();

        //每个元素一个线程
        ParalleIndividualMultiplier.multiply(result, matrix1, matrix2);

        end = System.currentTimeMillis();
        System.out.println("串行乘法器：" + (end - start) + "ms");
    }
}
