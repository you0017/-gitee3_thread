package com.yc.thread.pro3_bestMatching;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BestMatchingTask implements Callable<BestMatchingData> {
    private List<String> words;
    private String word;
    private int startIndex;
    private int endIndex;
    private CountDownLatch endController;

    @Override
    public BestMatchingData call() throws Exception {
        int minDistance = Integer.MAX_VALUE;
        int distance = 0;//计算的距离
        List<String> minDistanceWords = new ArrayList<>();//最短距离的单词列表

        //循环words集合 从startIndex到endIndex，计算编辑距离
        for (int i = startIndex; i < endIndex; i++) {
            //记录最小的编辑距离，记录最小的编辑距离对应的单词
            distance = EditDistance.calculate(word, words.get(i));
            if (distance<minDistance){
                //找到最小的编辑距离
                minDistance = distance;//更新距离
                minDistanceWords.clear();//清空集合
                minDistanceWords.add(words.get(i));
            }else if (distance == minDistance){
                //找到与最小编辑距离相同的单词
                minDistanceWords.add(words.get(i));
            }
        }

        //运行线程数-1
        endController.countDown();
        //将最小的编辑距离及单词列表包装成BestMatchingData对象
        return new BestMatchingData(minDistance,minDistanceWords);
    }
}
