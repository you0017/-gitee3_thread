package com.yc.thread.pro1_matrix;

/**
 * 矩阵生成器
 */
public class MatrixGenerator {

    /**
     * 生成rows行cols列的矩阵
     * @param rows
     * @param cols
     * @return
     */
    public static double[][] generateMatrix(int rows, int cols) {
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Math.random();
            }
        }
        return matrix;
    }

}
