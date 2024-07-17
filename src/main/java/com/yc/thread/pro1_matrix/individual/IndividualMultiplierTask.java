package com.yc.thread.pro1_matrix.individual;

/**
 * 计算result矩阵中[i][j]的元素值  matrix1中对应的列 * matrix2对应的行
 */
public class IndividualMultiplierTask implements Runnable{

    private final double[][] result;        //final:确保线程安全性
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final int i;
    private final int j;

    /**
     *
     * @param result    结果矩阵
     * @param matrix1   矩阵A
     * @param matrix2   矩阵B
     * @param i         结果矩阵的第i行
     * @param j         结果矩阵的第j列
     */
    public IndividualMultiplierTask(double[][] result, double[][] matrix1, double[][] matrix2, int i, int j) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.i = i;
        this.j = j;
    }

    @Override
    public void run() {
        result[i][j] = 0;
        //累加求和
        for (int k = 0; k < matrix1[0].length; k++) {
            result[i][j] += matrix1[i][k] * matrix2[k][j];
        }
    }
}
