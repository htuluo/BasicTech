package com.llm.redis;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author ： luoleiming
 * @date ：Created in 2022/4/28
 * @description： jedis客户端测试
 */
@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 1)
@Threads(20)
@State(Scope.Benchmark)
@Measurement(iterations = 5, time = 6000, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.SECONDS)
public class JedisJmhTest {
    private JedisCluster jedisCluster;

    @Setup
    public void setup() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxTotal(60);
        jedisPoolConfig.setMinIdle(2);
        jedisPoolConfig.setMaxWaitMillis(3000);
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        String redisHostPorts = "10.255.209.32:8381,10.255.209.36:8381,10.255.209.37:8381,10.255.209.32:8382,10.255.209.36:8382,10.255.209.37:8382";
        for (String hostPort : redisHostPorts.split(",")) {
            hostAndPorts.add(new HostAndPort(hostPort.split(":")[0], Integer.parseInt(hostPort.split(":")[1])));
        }
        jedisCluster = new JedisCluster(hostAndPorts, jedisPoolConfig);

    }

    @Benchmark
    public void get() {
//        System.out.println(jedisCluster.get("alliance:risk:custid:184951018"));
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(JedisJmhTest.class.getSimpleName())
                .forks(1)
                .output("/var/www/jedis-test.log")
                .build();
        new Runner(options).run();
    }
}
