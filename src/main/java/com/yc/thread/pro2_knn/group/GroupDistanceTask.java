package com.yc.thread.pro2_knn.group;

import com.yc.thread.pro2_knn.DistanceCalculator;
import com.yc.thread.pro2_knn.EuclideanDistanceCalculator;
import com.yc.thread.pro2_knn.bean.BankMarketing;
import com.yc.thread.pro2_knn.bean.Distance;
import com.yc.thread.pro2_knn.bean.Sample;

import java.util.List;

public class GroupDistanceTask implements Runnable{
    private Distance[] distances;
    private int startIndex;
    private int endIndex;
    private List<BankMarketing> dataSet;
    private Sample example;

    private DistanceCalculator edc = new EuclideanDistanceCalculator();

    public GroupDistanceTask(Distance[] distances, int startIndex, int endIndex, List<BankMarketing> dataSet, Sample example) {
        this.distances = distances;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.dataSet = dataSet;
        this.example = example;
    }

    public GroupDistanceTask(Distance[] distances, int startIndex, int endIndex, List<BankMarketing> dataSet, Sample example, DistanceCalculator edc) {
        this.distances = distances;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.dataSet = dataSet;
        this.example = example;
        this.edc = edc;
    }

    public DistanceCalculator getEdc() {
        return edc;
    }

    public void setEdc(DistanceCalculator edc) {
        this.edc = edc;
    }

    @Override
    public void run() {
        //循环从startIndex到endIndex   计算 example与dataSet中各条数据的距离
        for (int index = startIndex;index<endIndex;index++){
            Sample sample = dataSet.get(index);
            //计算sample与测试数据example的距离
            distances[index] = new Distance();
            distances[index].setIndex(index);
            distances[index].setDistance(edc.calculate(sample,example));
        }
    }
}
