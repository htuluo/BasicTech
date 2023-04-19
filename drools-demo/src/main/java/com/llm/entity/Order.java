package com.llm.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    /**
     * 订单原价金额
     */
    private int price;

    /**
     *下单人
     */
    private User user;

    /**
     *积分
     */
    private int score;
    /**
     * 数量
     */
    private int amout;

    /**
     * 下单日期
     */
    private Date bookingDate;
}
