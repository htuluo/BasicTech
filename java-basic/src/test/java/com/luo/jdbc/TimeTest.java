package com.luo.jdbc;

import com.google.common.base.Stopwatch;
import org.apache.commons.lang3.ThreadUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author ： luoleiming
 * @date ：Created in 2020/9/30
 * @description： //TODO
 */
public class TimeTest {
    @Test
    public void test1() throws ParseException {
        SimpleDateFormat sdf =new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.US);
        SimpleDateFormat sdf1 =new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.CHINA);
        SimpleDateFormat sdf2=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.JAPAN);
        SimpleDateFormat sdf3 =new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.FRANCE);
        Date date = DateUtils.parseDate("2020-10-01 00:00:00","YYYY-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));
        System.out.println(sdf1.format(date));
        System.out.println(sdf2.format(date));
        System.out.println(sdf3.format(date));
    }

    @Test
    public void test2() throws InterruptedException {

        Stopwatch stopWatch= Stopwatch.createStarted();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("cost time:"+stopWatch.stop());
        stopWatch.start();
        Thread.sleep(2012);
        stopWatch.stop();
        System.out.println(stopWatch.elapsed());
        System.out.println(stopWatch.elapsed(TimeUnit.SECONDS));
        stopWatch.reset().start();
        Thread.sleep(5012);
        stopWatch.stop();
        System.out.println(stopWatch.elapsed(TimeUnit.MILLISECONDS));

    }
}
