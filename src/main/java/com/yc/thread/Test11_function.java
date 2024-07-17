package com.yc.thread;

/**
 * 函数式接口只能有一个抽象方法（我们这里不包括与Object的方法重名的方法
 * 可以有从Object继承过来的抽象方法·因为所有类的最终父类都是Object
 * 接口中唯一抽象方法的命名不重要·因为函数式接口就是对某一行为进行抽象·主要目的就是支持lambda表达式
 * @param <T>
 */

@FunctionalInterface
interface Action<T>{
    void execute(T t);
}

public class Test11_function {
    public static void main(String[] args) {
        //外部类
        Action action3 = new MyAction();
        action3.execute("haha");

        //匿名内部类
        Action action4 = new Action() {
            @Override
            public void execute(Object o) {
                System.out.println(o);
            }
        };


        //lambda语法
        Action action2 = (t)->{
            System.out.println(t);
        };
        action2.execute("action2");


        //直接绑定
        Action action = System.out::println;
        action.execute("action");

        //作为参数用
        test(System.out::println,"test");

    }

    public static void test(Action action,String str){
        action.execute(str);
    }
}

class MyAction implements Action{

    @Override
    public void execute(Object o) {
        System.out.println(o);
    }
}