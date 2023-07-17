package com.llm.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.WriteTypeHint;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细表
 *
 * @author zhanglianqi
 * @date 2022-02-11 17:25
 */
@Data
@Document(indexName = "risk_order_detail",writeTypeHint = WriteTypeHint.FALSE)
@TypeAlias("risk_order_detail")
public class RiskOrderDetail implements Serializable {

    private static final long serialVersionUID = -4485279311323670778L;

    @Id
    @Field("id")
    @JSONField(name = "id")
    private String id;

    @Field("order_id")
    @JSONField(name = "order_id")
    private Long orderId;
    @Field("cust_id")
    @JSONField(name = "cust_id")
    private Long custId;
    @Field("order_ip")
    @JSONField(name = "order_ip")
    private String orderIp;
    @Field("receiver_name")
    @JSONField(name = "receiver_name")
    private String receiverName;
    @Field("receiver_mobile_tel")
    @JSONField(name = "receiver_mobile_tel")
    private String receiverMobileTel;
    @Field("receiver_country_id")
    @JSONField(name = "receiver_country_id")
    private Integer receiverCountryId;
    @Field("receiver_province_id")
    @JSONField(name = "receiver_province_id")
    private Integer receiverProvinceId;
    @Field("receiver_city_id")
    @JSONField(name = "receiver_city_id")
    private Integer receiverCityId;
    @Field("receiver_town_id")
    @JSONField(name = "receiver_town_id")
    private Integer receiverTownId;
    @Field("quarter_id")
    @JSONField(name = "quarter_id")
    private Integer quarterId;
    @Field("receiver_address")
    @JSONField(name = "receiver_address")
    private String receiverAddress;
    @Field("paid_amount")
    @JSONField(name = "paid_amount")
    private BigDecimal paidAmount;
    @Field("coupon_amount")
    @JSONField(name = "coupon_amount")
    private BigDecimal couponAmount;
    @Field("promotion_code_amount")
    @JSONField(name = "promotion_code_amount")
    private BigDecimal promotionCodeAmount;
    //    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Field("creation_date")
    @JSONField(name = "creation_date")
    private String creationDate;
    @Field("order_status")
    @JSONField(name = "order_status")
    private Integer orderStatus;
    @Field("order_is_flag")
    @JSONField(name = "order_is_flag")
    private Integer orderIsFlag;

}
