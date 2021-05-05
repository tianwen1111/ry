package com.ruoyi.framework.config;

import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.client.http.JestHttpClient;
import org.elasticsearch.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

@Configuration
public class JestHttpClinetConfig {

    @Bean
    public JestHttpClient getEsClient(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder(
                "http://"+"127.0.0.1:9200")
                .multiThreaded(true)
                .readTimeout(5000)
                .build());

        return (JestHttpClient) factory.getObject();
    }

    /*@Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) {
        return new ElasticsearchTemplate(client);
    }*/
}
