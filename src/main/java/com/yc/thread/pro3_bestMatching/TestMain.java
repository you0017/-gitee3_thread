package com.yc.thread.pro3_bestMatching;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //String path = System.getProperty("user.home");
        //path = path + File.separator;
        String path = "src/main/java/com/yc/thread/pro3_bestMatching/data/UK Advanced Cryptics Dictionary.txt";
        List<String> list = WordsLoader.load(path);
        /*System.out.println(list);
        System.out.println(list.size());*/

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        String word = "sdt";

        //BestMatchingTask task = new BestMatchingTask(list,word,0,list.size());
        //FutureTask<BestMatchingData> futureTask = new FutureTask<BestMatchingData>(task);
        //Thread thread = new Thread(futureTask);
        //thread.start();


        //BestMatchingData bestMatchingData = futureTask.get();//get()是阻塞式方法，只有任务运行完了，才可以得到结果，程序继续向下
        //System.out.println(bestMatchingData);
        long start = System.currentTimeMillis();
        BestMatchingData bestMatchingWords = BestMatchingConcurrentCalculation.getBestMatchingWords(word, list);
        System.out.println(bestMatchingWords);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }
}
