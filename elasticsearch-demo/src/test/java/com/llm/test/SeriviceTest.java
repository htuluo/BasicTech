package com.llm.test;

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
}
