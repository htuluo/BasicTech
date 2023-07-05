package com.llm.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细表
 *
 * @author zhanglianqi
 * @date 2022-02-11 17:25
 */
@Data
@Document(indexName = "risk_order_detail")
public class RiskOrderDetail implements Serializable {

    private static final long serialVersionUID = -4485279311323670778L;

    @Id
    @Field("_id")
    private String id;

    @Field("order_id")
    private Long orderId;
    @Field("cust_id")
    private Long custId;
    @Field("order_ip")
    private String orderIp;
    @Field("receiver_name")
    private String receiverName;
    @Field("receiver_mobile_tel")
    private String receiverMobileTel;
    @Field("receiver_country_id")
    private Integer receiverCountryId;
    @Field("receiver_province_id")
    private Integer receiverProvinceId;
    @Field("receiver_city_id")
    private Integer receiverCityId;
    @Field("receiver_town_id")
    private Integer receiverTownId;
    @Field("quarter_id")
    private Integer quarterId;
    @Field("receiver_address")
    private String receiverAddress;
    @Field("paid_amount")
    private BigDecimal paidAmount;
    @Field("coupon_amount")
    private BigDecimal couponAmount;
    @Field("promotion_code_amount")
    private BigDecimal promotionCodeAmount;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Field("creation_date")
    private String creationDate;
    @Field("order_status")
    private Integer orderStatus;
    @Field("order_is_flag")
    private Integer orderIsFlag;

}
