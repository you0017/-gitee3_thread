package com.yc.thread.pro2_knn.group;

import com.yc.thread.pro2_knn.bean.BankMarketing;
import com.yc.thread.pro2_knn.bean.Distance;
import com.yc.thread.pro2_knn.bean.Sample;

import java.util.*;

/**
 * knn调度类
 */
public class ParallelGroupKnnClassifier {
    private int k;  //knn表示近邻几个
    private int numThreads; //线程数
    private boolean parallelSort;   //排序是否并行

    private List<BankMarketing> dataSet;    //训练集39129条，拿一条测试数据和它计算距离

    public ParallelGroupKnnClassifier(int k, int numThreads, boolean parallelSort, List<BankMarketing> dataSet) {
        this.k = k;
        this.numThreads = numThreads;
        this.parallelSort = parallelSort;
        this.dataSet = dataSet;
    }

    public String classify(Sample example){
        Distance[] distances = new Distance[dataSet.size()];
        //1.计算12个线程中每个线程  它的计算任务的 startIndex,endIndex
        int length = dataSet.size() / numThreads;   //分段区间
        int startIndex = 0;
        int endIndex = length;
        List<Thread> list = new ArrayList<>();
        //2.根据numThreads  创建任务，绑定到线程上
        for (int i = 0; i < numThreads; i++) {
            //计算example这个条测试数据与dataSet中39129条数据的距离  （计算  startIndex -endIndex),将距离结果存到distance
            GroupDistanceTask task = new GroupDistanceTask(distances,startIndex,endIndex,dataSet,example);
            Thread t = new Thread(task);
            t.start();
            list.add(t);

            startIndex = endIndex;
            endIndex = i==numThreads-2? dataSet.size() : endIndex+length;
            //System.out.println("第"+i+"线程的计算范围为"+startIndex+"-"+endIndex);
        }

        //
        //3.调用每个线程的join()  让主线程停止，好计算时间
        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //4.排序距离
        //是否支持并行排序
        if (parallelSort){
            Arrays.parallelSort(distances);
        }else{
            Arrays.sort(distances);
        }

        //5.将前k个样本的标签存到一个Map<String,Integer>中
        //                          标签名，次数
        Map<String,Integer> results = new HashMap<>();
        for (int i = 0; i < k; i++) {
            Sample sample = dataSet.get(distances[i].getIndex());
            //sample  getTag()  ->  获取标签  getData()
            String tag = sample.getTag();
            /*if (results.containsKey(tag)){
                results.put(tag,results.get(tag)+1);
            }else{
                results.put(tag,1);
            }*/
            //tag不存在就放入1    如果存在a是原有的值，b是value也就是1
            results.merge(tag,1,(a,b)->a+b);
        }

        //6.取出map次数最多的标签名返回
        //传统写法
        /*int max = 0;
        String maxTag = "";
        Set<Map.Entry<String, Integer>> set = results.entrySet();
        for (Map.Entry<String, Integer> entry : set) {
            String tag = entry.getKey();
            Integer value = entry.getValue();

            if (value>max){
                maxTag = tag;
            }
        }

        return maxTag;*/
                                //对整个键值队排序      根据值排序                   返回的键值队，我要取值
        return Collections.max(results.entrySet(),Map.Entry.comparingByValue()).getKey();
    }
}
