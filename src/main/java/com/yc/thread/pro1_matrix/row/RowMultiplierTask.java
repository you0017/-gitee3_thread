package com.yc.thread.pro1_matrix.row;

/**
 * 基于结果矩阵行的任务类：结果矩阵每一行一个任务
 */
public class RowMultiplierTask implements Runnable{
    private final double[][] result;        //final:确保线程安全性
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final int i;

    /**
     *
     * @param result    结果矩阵
     * @param matrix1   矩阵A
     * @param matrix2   矩阵B
     * @param i         结果矩阵的第i行
     */
    public RowMultiplierTask(double[][] result, double[][] matrix1, double[][] matrix2, int i) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.i = i;
    }

    @Override
    public void run() {
        for (int j = 0; j < matrix2[0].length; j++) {
            result[i][j] = 0;
            //累加求和
            for (int k = 0; k < matrix1[0].length; k++) {
                result[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }

    }
}
