package com.yc.thread.pro3_bestMatching;

public class EditDistance {
    /**
     * 计算从单词1变为单词2要编辑几次 => 编辑距离
     * @param word1
     * @param word2
     * @return
     */
    public static int calculate(String word1,String word2){
        int len1 = word1.length();
        int len2 = word2.length();

        // 创建一个二维数组来存储中间结果
        int[][] dp = new int[len1 + 1][len2 + 1];

        // 初始化第一行和第一列
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        // 动态规划填充dp数组
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        // 返回最后一个元素即为最终的编辑距离
        return dp[len1][len2];
    }
}
