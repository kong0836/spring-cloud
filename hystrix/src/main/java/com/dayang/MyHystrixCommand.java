package com.dayang;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

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
        // super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));

        // 信号量策略配置
        // super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
        //         .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
        //                 .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE
        //                 )));

        // 线程隔离策略配置
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(10)
                        .withMaxQueueSize(100).withMaximumSize(100)));

        this.name = name;
    }

    @Override
    protected String run() {
        // 模拟超时
        // try {
        //     Thread.sleep(1000 * 10);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        System.err.println("get data");
        return this.name + ":" + Thread.currentThread().getName();
    }

    @Override
    protected String getFallback() {
        return "失败了 ";
    }

    /**
     * 结果缓存
     *
     * @return
     */
    @Override
    protected String getCacheKey() {
        return String.valueOf(this.name);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 同步调用方式
        // String result = new MyHystrixCommand("zhangsan").execute();
        // System.out.println(result);

        // 异步调用方式
        // Future<String> future = new MyHystrixCommand("zhangsan").queue();
        // System.out.println(future.get());

        // 验证可使用缓存
        // HystrixRequestContext context = HystrixRequestContext.initializeContext();
        // String result = new MyHystrixCommand("zhangsan").execute();
        // System.out.println(result);
        // Future<String> future = new MyHystrixCommand("zhangsan").queue();
        // System.out.println(future.get());
        // context.shutdown();

        // 验证缓存清除
        // HystrixRequestContext context = HystrixRequestContext.initializeContext();
        // String result = new ClearCacheHystrixCommand("zhangsan").execute();
        // System.out.println(result);
        // ClearCacheHystrixCommand.flushCache("zhangsan");
        // Future<String> future = new ClearCacheHystrixCommand("zhangsan").queue();
        // System.out.println(future.get());

        // 合并请求
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        Future<String> f1 = new MyHystrixCollapser("zhangsan").queue();
        Future<String> f2 = new MyHystrixCollapser("zhangsan333").queue();
        System.out.println(f1.get() + "=" + f2.get());
        context.shutdown();
    }
}
