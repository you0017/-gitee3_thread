package com.yc.thread.pro2_knn;

import com.yc.thread.pro2_knn.bean.BankMarketing;
import com.yc.thread.pro2_knn.group.ParallelGroupKnnClassifier;

import java.util.List;

public class Test2_main {
    public static void main(String[] args) {
        String trainpath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\yc\\thread\\pro2_knn\\data\\bank.data";
        List<BankMarketing> train = BankMarketingLoader.load(trainpath);//训练集
        System.out.println("训练集大小："+train.size());

        String testpath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\yc\\thread\\pro2_knn\\data\\bank.test";
        List<BankMarketing> test = BankMarketingLoader.load(testpath);//测试集
        System.out.println("测试集大小："+test.size());

        //knn的k确定
        int k = 10;
        //java   Test2_main
        if (args!=null&&args.length>0){
            k = Integer.parseInt(args[0]);
        }

        //定义两个变量存测试集  通过模型预测准确率
        int success = 0, mistake = 0;

        int numThreads = Runtime.getRuntime().availableProcessors();
        ParallelGroupKnnClassifier classifier = new ParallelGroupKnnClassifier(k,numThreads,true,train);

        long start = System.currentTimeMillis();
        //循环测试集中的每一条，调用这个模型进行预测
        for (BankMarketing testData : test) {
            //tag是模型预测的类别
            String tag = classifier.classify(testData);
            if (tag.equals(testData.getTag())){
                success++;
            }else {
                mistake++;
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("按cpu核数生成任务，计算时间为："+(end-start));
        System.out.println("正确数："+success+",错误数："+mistake+",正确率："+((double)success/(success+mistake)));
    }
}
