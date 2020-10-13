package com.luo.jdbc;

import org.junit.Test;

import java.io.*;

/**
 * @author ： luoleiming
 * @date ：Created in 2020/10/13
 * @description： //TODO
 */
public class FileReaderTest {
    @Test
    public void test1() throws IOException {
        FileInputStream inputStream = new FileInputStream(new File("D:\\pushGrandOrderInfo.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        LineNumberReader reader = new LineNumberReader(br);
        reader.setLineNumber(20);
        String s = reader.readLine();
        reader.setLineNumber(25);//无用，还是从第一行开始读
        s = reader.readLine();
        System.out.println(s);
//        while (s != null) {
//            s = reader.readLine();
//            if (!(s != null)) break;
//
//        }

    }
}
