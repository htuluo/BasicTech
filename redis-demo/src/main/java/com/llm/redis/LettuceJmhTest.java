package com.llm.redis;

import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author ： luoleiming
 * @date ：Created in 2022/4/28
 * @description： //TODO
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1)
@Threads(20)
@State(Scope.Benchmark)
@Measurement(iterations = 10, time = 6000, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.SECONDS)
public class LettuceJmhTest {
    private RedisClusterClient redisClusterClient;
    private StatefulRedisClusterConnection<String,String> connection;
    @Setup
    public void setup(){
        redisClusterClient=RedisClusterClient.create("redis://10.255.209.32:8381,10.255.209.36:8381,10.255.209.37:8381,10.255.209.32:8382,10.255.209.36:8382,10.255.209.37:8382");
        connection=redisClusterClient.connect();
    }
    @Benchmark
    public void get(){
        RedisAdvancedClusterCommands<String, String> commands = connection.sync();
        System.out.println(commands.get("a"));
    }
    @TearDown
    public void tearDown(){
        connection.close();
        redisClusterClient.shutdown();
    }

    public static void main(String[] args) throws RunnerException {
        Options options=new OptionsBuilder()
                .include(JedisJmhTest.class.getSimpleName())
                .forks(1)
                .output("/var/www/lettuce-test.log")
                .build();
        new Runner(options).run();
    }
}
