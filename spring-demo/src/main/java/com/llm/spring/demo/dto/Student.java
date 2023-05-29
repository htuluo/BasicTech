package com.llm.spring.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * @author ： htuluo
 * @date ：Created in 2023/5/29 17:57
 * @description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = 7942502707210851135L;
    private Long id;
    private String name;
}
