package com.mao.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步线程池配置
 * 异步方法执行会另起线程执行。线程池提供
 * @author mao by 11:47 2020/3/5
 */
@Configuration
@EnableAsync
@PropertySource("classpath:async_task_pool.properties")
@ConfigurationProperties(prefix = "thread")
@Getter
@Setter
public class AsyncTaskPoolConfig {

    private int corePoolSize;
    private int maxPoolSize;
    private int maxQueue;
    private String namePreFix;
    private int keepAlive;

    /**
     * 线程执行器
     * @return Executor
     */
    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(corePoolSize);
        //最大线程数
        executor.setMaxPoolSize(maxPoolSize);
        //队列大小
        executor.setQueueCapacity(maxQueue);
        //回收时间
        executor.setKeepAliveSeconds(keepAlive);
        //线程池名称前缀
        executor.setThreadNamePrefix(namePreFix);
        // 当pool已经达到max size的时候
        // 不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}