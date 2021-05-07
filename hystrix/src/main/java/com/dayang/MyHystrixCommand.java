package com.dayang;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author konglinghui
 * @description
 * @date 2021/5/7 14:24
 */
public class MyHystrixCommand extends HystrixCommand<String> {

    private final String name;

    public MyHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
        this.name = name;
    }

    // @Override
    // protected String run() {
    //     return this.name + ":" + Thread.currentThread().getName();
    // }


    @Override
    protected String run() {
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.name + ":" + Thread.currentThread().getName();
    }
    @Override
    protected String getFallback() {
        return "失败了 ";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 同步调用方式
        // String result = new MyHystrixCommand("zhangsan").execute();
        // System.out.println(result);

        // 异步调用方式
        Future<String> future = new MyHystrixCommand("zhangsan").queue();
        System.out.println(future.get());
    }
}
