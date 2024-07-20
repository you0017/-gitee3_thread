package com.yc.thread.pro3_bestMatching;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 编辑距离最短的单词列表
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BestMatchingData {
    private int distance;//最短距离
    private List<String> words;//这个距离对应的单词列表
}
