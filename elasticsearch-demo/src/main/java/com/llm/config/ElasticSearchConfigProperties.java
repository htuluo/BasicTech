package com.llm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author ： luoleiming
 * @date ：Created in 2022/1/18
 * @description： //TODO
 */
@Data
@Configuration
@PropertySource("classpath:es.properties")
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchConfigProperties {
    private String clusterNodes;
    private String clusterName;
}
