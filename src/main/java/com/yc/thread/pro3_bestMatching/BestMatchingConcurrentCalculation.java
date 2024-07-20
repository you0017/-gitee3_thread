package com.yc.thread.pro3_bestMatching;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池的任务分配及调度，结果汇总
 */
public class BestMatchingConcurrentCalculation {
    public static BestMatchingData getBestMatchingWords(String word, List<String> dictionary) throws InterruptedException, ExecutionException {
        BestMatchingData result = new BestMatchingData();
        //1.任务数
        int numCores = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numCores);

        //2.计算每个任务对应词汇量  startIndex endIndex
        int size = dictionary.size();
        int endIndex = size / numCores;
        int startIndex = 0;
        //计数的
        CountDownLatch endController = new CountDownLatch(numCores);

        List<BestMatchingTask> tasks = new ArrayList<>();

        List<Future<BestMatchingData>> futures = new ArrayList<>();
        //3.创建BestMatchingTask
        for (int i = 0; i < numCores; i++) {
            /*BestMatchingTask task = new BestMatchingTask(dictionary, word, startIndex, endIndex, endController);
            FutureTask<BestMatchingData> futureTask = new FutureTask<>(task);
            executor.execute(futureTask);
            startIndex = endIndex;
            endIndex = i == numCores-2 ? size : endIndex + size / numCores;
            tasks.add(task);*/

            BestMatchingTask task = new BestMatchingTask(dictionary, word, startIndex, endIndex, endController);
            Future<BestMatchingData> submit = executor.submit(task);
            futures.add(submit);
            startIndex = endIndex;
            endIndex = i == numCores-2 ? size : endIndex + size / numCores;
        }
        //一次性提交所有任务//阻塞式的，必须全部完成才会执行下面 invokeAll自带的阻塞
        //List<Future<BestMatchingData>> futures = executor.invokeAll(tasks);

        endController.await();

        result.setDistance(futures.get(0).get().getDistance());
        result.setWords(futures.get(0).get().getWords());
        for (int i = 1; i < futures.size(); i++) {
            if (futures.get(i).get().getDistance()<result.getDistance()){
                result.setDistance(futures.get(i).get().getDistance());
                result.setWords(futures.get(i).get().getWords());
            } else if (futures.get(i).get().getDistance() == result.getDistance()) {
                 result.getWords().addAll(futures.get(i).get().getWords());
            }
        }

        executor.shutdown();

        //4.将上面的创建的BestMatchingTask提交给线程池执行
        //5.通过FutureTask.get()获取以上任务的执行结果BestMatchingData   汇总(找最小距离及对应的词汇表
        //6.包装成BestMatchingData

        return result;
    }
}
