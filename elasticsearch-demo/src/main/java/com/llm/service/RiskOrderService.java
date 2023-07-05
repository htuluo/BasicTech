package com.llm.service;

import com.alibaba.fastjson.JSONObject;
import com.llm.domain.RiskOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.stereotype.Service;

@Service
public class RiskOrderService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public void save(){
        RiskOrderDetail orderDetail = new RiskOrderDetail();
        orderDetail.setId("222000");
        orderDetail.setOrderId(222000L);
        orderDetail.setOrderIp("127.0.0.1");
        orderDetail.setOrderIsFlag(56);
        orderDetail.setOrderStatus(900);
        orderDetail.setOrderStatus(100);
//        orderDetail.setCustId(1992982L);
//        orderDetail.setCreationDate(DateUtils.formatDate(new Date()));
//        orderDetail.setCouponAmount(new BigDecimal(12.2));
//        orderDetail.setPaidAmount(new BigDecimal(10.2));
//        orderDetail.setCouponAmount(new BigDecimal(1.2));
//        orderDetail.setReceiverAddress("我的地址");
//        orderDetail.setReceiverProvinceId(141);
//        orderDetail.setReceiverTownId(1410104);
//        orderDetail.setReceiverCityId(1401);
//        orderDetail.setReceiverCountryId(9000);
//        orderDetail.setReceiverName("womingzi");
        elasticsearchRestTemplate.save(orderDetail);
    }


    public void update(){
        RiskOrderDetail riskOrderDetail = new RiskOrderDetail();
        riskOrderDetail.setId("222002");
        riskOrderDetail.setOrderId(222002L);

        riskOrderDetail.setOrderStatus(800);
        UpdateQuery query = UpdateQuery.builder(riskOrderDetail.getId())
         .withDocument(Document.parse(JSONObject.toJSONString(riskOrderDetail)))
                .withDocAsUpsert(true)//true-是无则插入，有则更新
                .withRetryOnConflict(1).build();
        // 使用ElasticsearchRestTemplate执行更新操作
        UpdateResponse response = elasticsearchRestTemplate.update(query, IndexCoordinates.of("risk_order_detail"));
        System.out.println(JSONObject.toJSONString(response));
    }
}
