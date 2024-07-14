package com.yc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        show();
    }

    public static void show() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("2");
    }
}
