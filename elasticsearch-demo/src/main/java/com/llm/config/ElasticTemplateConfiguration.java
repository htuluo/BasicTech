package com.llm.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.util.Assert;

/**
 * @author ： luoleiming
 * @date ：Created in 2022/1/18
 * @description： //TODO
 */
@Configuration
@Slf4j
public class ElasticTemplateConfiguration extends AbstractElasticsearchConfiguration {
    @Autowired
    ElasticSearchConfigProperties elasticSearchConfig;

    @Override
    @Bean
    @Primary
    public RestHighLevelClient elasticsearchClient() {
        Assert.hasLength(elasticSearchConfig.getClusterNodes(), "Config for Elasticsearch clusterNodes must not be empty");
        String[] hostAndPorts = elasticSearchConfig.getClusterNodes().split(",");
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder().connectedTo(hostAndPorts).build();
        log.info("elasticsearch init cluster,node:{}", elasticSearchConfig.getClusterNodes());
        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
    }

}
