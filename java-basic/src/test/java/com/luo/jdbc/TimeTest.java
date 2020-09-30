package com.luo.jdbc;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
}
