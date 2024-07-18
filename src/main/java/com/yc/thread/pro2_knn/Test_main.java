package com.yc.thread.pro2_knn;

import com.yc.thread.pro2_knn.bean.BankMarketing;
import com.yc.thread.pro2_knn.bean.Sample;

import java.util.*;

public class Test_main {
    public static void main(String[] args) {
        //问题：路径：

        //用System.getProperty("user.home"); 用户目录：c:\\users\zy   /home/用户名

        //用System.getProperty("user.dir")获取当前项目的路径
        //System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.dir")+"\\src\\main\\java\\com\\yc\\thread\\pro2_knn\\data\\bank.data");
        String path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\yc\\thread\\pro2_knn\\data\\bank.data";
        List<BankMarketing> list = BankMarketingLoader.load(path);

        for (BankMarketing bankMarketing : list) {
            System.out.println(bankMarketing);
        }
        System.out.println(list.size());

        EuclideanDistanceCalculator edc = new EuclideanDistanceCalculator();
        System.out.println(edc.calculate(new Student(2,4), new Student(3, 5)));

        //对数组
        int[] x = new int[]{4,2,5,7};
        Arrays.sort(x);//单线程
        Arrays.parallelSort(x);//多线程

        //对集合排序
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(100);
        l.add(3);
        Collections.sort(l);//单线程

    }
}


class Student extends Sample{
    private double weight;
    private double height;

    public Student() {
    }

    public Student(double weight, double height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public String getTag() {
        return null;
    }

    @Override
    public double[] getExample() {
        return new double[]{weight,height};
    }
}
