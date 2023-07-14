package com.llm.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.llm.domain.RiskOrderDetail;
import com.llm.service.RiskOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SeriviceTest {
    @Autowired(required = false)
    private RiskOrderService service;

    @Test
    public void testSave() {
        service.save();
    }
    @Test
    public void testUpdate() {
        service.update();
    }
    @Test
    public void testJsonParse() {
        RiskOrderDetail orderDetail = new RiskOrderDetail();
        orderDetail.setId("222000");
        orderDetail.setOrderId(222000L);
        orderDetail.setOrderIp("127.0.0.1");
        orderDetail.setOrderIsFlag(56);
        orderDetail.setOrderStatus(900);
        orderDetail.setOrderStatus(100);
        String jsonStr = JSONObject.toJSONString(orderDetail);
        System.out.println(jsonStr);
        String json2="{\"id\":\"222000\",\"orderId\":222000,\"orderIp\":\"127.0.0.1\",\"orderIsFlag\":56,\"orderStatus\":100}";
        ParserConfig config=new ParserConfig(false);
        config.setAsmEnable(false);
        config.setJacksonCompatible(true);
        RiskOrderDetail riskOrderDetail = JSONObject.parseObject(json2, RiskOrderDetail.class);
        System.out.println(riskOrderDetail);
    }
}
