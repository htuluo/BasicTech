package com.luo.test;

import basic.tech.response.DataResponse;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * @author ： luoleiming
 * @date ：Created in 2020/9/28
 * @description： //TODO
 */
public class JsonTest {
    @Test
    public void test1(){
        DataResponse dataResponse=new DataResponse();
        dataResponse.setData("aa");
        System.out.println(JSONObject.toJSONString(dataResponse));
    }
}
