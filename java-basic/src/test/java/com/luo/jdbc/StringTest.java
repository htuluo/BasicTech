package com.luo.jdbc;

import com.sun.javafx.iio.ImageStorage;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.tools.ant.util.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author ： luolm
 * @date ：Created in 2020/9/17
 * @description： //TODO
 */
public class StringTest {
    @Test
    public void test1() {
        String str = "abc@123.com";
        int x = str.indexOf("@");
        System.out.println(str.substring(0, x));
        System.out.println(str.substring(x));
    }

    @Test
    public void test2() {
        Double d = Double.valueOf("0d");
        System.out.println(d == 0);
    }


    @Test
    public void test3() {
        System.out.println(true ^ false);
        System.out.println(true ^ true);
        System.out.println(false ^ false);
        System.out.println(false ^ true);
        Set<String> a = new HashSet<>();
        a.add("aaa");
        a.add("bbb");
        a.add("ccc");
        a.add("ddd");
        Set<String> b = new HashSet<>(a);
        a.remove("aaa");
        System.out.println(Arrays.asList(b).toString());
    }

    @Test
    public void test4() {
        int a = 1, b = 3;

        System.out.println((float) a / b);
    }

    @Test
    public void test5() {
        String str = "这个人";
        String encodeToString = Base64.getEncoder().encodeToString(str.getBytes());
        System.out.println(encodeToString);
        String encodeApacheBase64String = org.apache.commons.codec.binary.Base64.encodeBase64String(str.getBytes());
        System.out.println(encodeApacheBase64String);
        System.out.println("-------------");
        byte[] decode = Base64.getDecoder().decode(encodeToString);
        for (int i = 0; i < decode.length; i++) {
            System.out.print(decode[i]);
            System.out.print(",");

        }
        System.out.println();
        byte[] bytes = org.apache.commons.codec.binary.Base64.decodeBase64(encodeApacheBase64String);
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
            System.out.print(",");

        }
    }

    /**
     * 1000000次
     * encode:254
     * string byte:116
     * 图片文件byte
     * encode:3058
     * string byte:12935
     */
    @Test
    public void test6() throws IOException {

//        BufferedImage image = new BufferedImage(300, 150, BufferedImage.TYPE_4BYTE_ABGR);
        BufferedImage image = ImageIO.read(new File("D:/srcImg.png"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "png", out);
        //转成byte数组
        byte[] bytes = out.toByteArray();
        String str = "个人水电费";

        System.out.println(new String(bytes));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int i1 = 10000;
        for (int i = 0; i < i1; i++) {
            String encodeToString = Base64.getEncoder().encodeToString(bytes);
        }
        System.out.println("encode:" + stopWatch.getTime());
        stopWatch.reset();
        stopWatch.start();
        for (int i = 0; i < i1; i++) {
            new String(bytes);
        }
        stopWatch.stop();
        System.out.println("string byte:" + stopWatch.getTime());


    }

    @Test
    public void test7() throws IOException {
        String tmpExpress = "2&&&&";
        boolean stringFoundTwice = isStringFoundTwice(tmpExpress, "&");
        System.out.println(stringFoundTwice);
        System.out.println("结束");

    }

    @Test
    public void test8() throws IOException {
        String tmpExpress = "20,30,40,2020";
        Integer tmp = 20;
        Optional<String> first = Arrays.stream(tmpExpress.split(",")).filter(item -> item.equals(tmp.toString())).findFirst();
        System.out.println(first.isPresent());
        System.out.println(first.isPresent() ? first.get() : "null");

    }

    @Test
    public void test9() throws IOException {
        String tmpExpress = "m_min_alalsd";

        System.out.println(tmpExpress.substring(0,tmpExpress.lastIndexOf("_")+1));

    }

    /**
     * 校验字符是否成对出现
     *
     * @param wholeString
     * @param findStr
     * @return
     */
    private boolean isStringFoundTwice(String wholeString, String findStr) {
        String str2 = findStr + findStr;
        int fromIndex = 0;
        int index = wholeString.indexOf(findStr, fromIndex);
        while (fromIndex < wholeString.length() && index != -1) {
            int index2 = wholeString.indexOf(str2, fromIndex);
            if (index != index2) {
                return false;
            }
            fromIndex = index + 2;
            index = wholeString.indexOf(findStr, fromIndex);
        }
        return true;
    }

    @Test
    public void test10(){
        Assert.assertNotEquals(0l,0l);
    }

}
