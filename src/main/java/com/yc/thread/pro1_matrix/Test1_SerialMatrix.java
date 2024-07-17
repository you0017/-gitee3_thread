package com.yc.thread.pro1_matrix;

import com.yc.thread.pro1_matrix.single.SerialMultiplier;

public class Test1_SerialMatrix {
    public static void main(String[] args) {
        double[][] matrix1 = MatrixGenerator.generateMatrix(3000, 3000);
        double[][] matrix2 = MatrixGenerator.generateMatrix(3000, 3000);

        double result[][] = new double[matrix1.length][matrix2.length];

        long start,end;
        start = System.currentTimeMillis();

        //单线程
        SerialMultiplier.multiply(matrix1, matrix2, result);

        end = System.currentTimeMillis();
        System.out.println("串行乘法器：" + (end - start) + "ms");//125623ms
    }
}
