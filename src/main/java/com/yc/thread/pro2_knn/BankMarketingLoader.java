package com.yc.thread.pro2_knn;

import com.yc.thread.pro2_knn.bean.BankMarketing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据加载器：读取数据文件data和测试文件test
 */
public class BankMarketingLoader {
    public static List<BankMarketing> load (String path){
        List<BankMarketing> list = new ArrayList<>();
        //TODO:读取path位置的文件，并解析成BankMarketing对象
        //技术 java.io流 File类  InputStream 二进制流
        //  =>包装成Reader => BufferedReader(装饰模式
        //.readLine()

        //2.采用线程安全的文件操作类
        //BIO: InputStream iis = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        //NIO: new FileInputStream(path) => Files.newInputStream(path)

        //3.异常处理 try..catch ...finally
        Path p = Paths.get(path);
        try (
                InputStream iis = Files.newInputStream(p);
                BufferedReader reader = new BufferedReader(new InputStreamReader(iis))
                ){
            String line = null;
            while ( (line = reader.readLine()) != null){
                String[] split = line.split(";");
                BankMarketing bankMarketing = new BankMarketing();
                bankMarketing.setData(split);
                list.add(bankMarketing);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }
}
