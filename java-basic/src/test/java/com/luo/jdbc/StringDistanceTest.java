package com.luo.jdbc;

import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import utils.StringDistanceUtils;
import utils.distance.CosineSimilarity;

import java.util.concurrent.TimeUnit;

import static utils.StringDistanceUtils.*;

/*
 *@author:luoleiming
 *@date:2022/6/28 15:09
 *@description:
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class StringDistanceTest {
    //    @Param(value = "北城世纪城冠徽苑6栋（张义18055152990）")
    private static String aStr = "北城世纪城冠徽苑6栋（张义18055152990）";
    //    @Param(value = "北城世纪城冠徽苑6栋1601  张义  18055152990")
    private static String bStr = "北城世纪城冠徽苑6栋1601  张义  18055152990";

    @Benchmark
    public static void jacardTest() {
        jaccard(aStr, bStr);
    }

    @Benchmark
    public static void LevenshteinTest() {
        Levenshtein(aStr, bStr);
    }

    @Benchmark
    public static void hammingTest() {
        hamming(aStr, bStr);
    }

    @Benchmark
    public static void cosTest() {
        cos(aStr, bStr);
    }

    @Benchmark
    public static void cosWordTest() {
        CosineSimilarity.getSimilarity(aStr, bStr);
    }

    @Test
    public void testSimilarity(){
        System.out.println(cos(aStr, bStr));
        System.out.println(CosineSimilarity.getSimilarity(aStr, bStr));
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(StringDistanceTest.class.getSimpleName())
                //启动几个进程测试
                .forks(2)
                //预热次数
                .warmupIterations(3)
                .warmupTime(TimeValue.NONE)
                //实际迭代次数
                .measurementIterations(1000)
                .measurementTime(TimeValue.NONE)
                .build();
        new Runner(options).run();
//        StopWatch stopWatch=new StopWatch();
//        stopWatch.start();
//        for (int i = 0; i < 1000; i++) {
//
//            float jaccard = StringDistanceUtils.jaccard("北城世纪城冠徽苑6栋（张义18055152990）", "北城世纪城冠徽苑6栋1601  张义  18055152990");
//        }
//        stopWatch.stop();
//
//        System.out.println("jaccard");
//        System.out.println(StringDistanceUtils.SorensenDice("北城世纪城冠徽苑6栋（张义18055152990）", "北城世纪城冠徽苑6栋1601  张义  18055152990"));
//        System.out.println(StringDistanceUtils.editDis("北城世纪城冠徽苑6栋（张义18055152990）", "北城世纪城冠徽苑6栋1601  张义  18055152990"));
//        System.out.println(StringDistanceUtils.hamming("北城世纪城冠徽苑6栋（张义18055152990）", "北城世纪城冠徽苑6栋1601  张义  18055152990adfasdfasds"));
//        System.out.println(StringDistanceUtils.cos("北城世纪城冠徽苑6栋（张义18055152990）", "北城世纪城冠徽苑6栋1601  张义  18055152990"));
    }
}
