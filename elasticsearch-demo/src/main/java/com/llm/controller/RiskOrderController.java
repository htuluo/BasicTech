package com.llm.controller;

import com.llm.service.RiskOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiskOrderController {
    @Autowired
    private RiskOrderService riskOrderService;
    @GetMapping(path = "/save")
    public void save(){
        riskOrderService.save();
    }
}
