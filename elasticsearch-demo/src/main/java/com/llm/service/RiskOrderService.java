package com.llm.service;

import com.llm.domain.RiskOrderDetail;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class RiskOrderService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public void save(){
        RiskOrderDetail orderDetail = new RiskOrderDetail();
        orderDetail.setOrderId(222000L);
        orderDetail.setOrderIp("127.0.0.1");
        orderDetail.setOrderIsFlag(56);
        orderDetail.setOrderStatus(100);
        orderDetail.setCustId(1992982L);
        orderDetail.setCreationDate(DateUtils.formatDate(new Date()));
        orderDetail.setCouponAmount(new BigDecimal(12.2));
        orderDetail.setPaidAmount(new BigDecimal(10.2));
        orderDetail.setCouponAmount(new BigDecimal(1.2));
        orderDetail.setReceiverAddress("我的地址");
        orderDetail.setReceiverProvinceId(141);
        orderDetail.setReceiverTownId(1410104);
        orderDetail.setReceiverCityId(1401);
        orderDetail.setReceiverCountryId(9000);
        orderDetail.setReceiverName("womingzi");
        elasticsearchRestTemplate.save(orderDetail);
    }
}
