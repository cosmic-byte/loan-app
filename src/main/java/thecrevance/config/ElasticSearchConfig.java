//package com.workforce.config;
//
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.node.NodeBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.repository
//        .config.EnableElasticsearchRepositories;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = {"com.workforce.models.ElasticSearch"
//        ,"com.workforce.elasticrepo"})
//public class ElasticSearchConfig {
//
//    @Bean
//    public NodeBuilder nodeBuilder() {
//        return new NodeBuilder();
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate() throws IOException {
//
//        final Path tmpDir = Files.createTempDirectory(Paths
//                .get(System.getProperty("java.io.tmpdir")), "elasticsearch_data");
//
//
//        final Settings.Builder elasticsearchSettings =
//                Settings.settingsBuilder()
//                        .put("http.enabled", "false")
//                        .put("path.data", tmpDir.toAbsolutePath().toString())
//                        .put("path.home", "/usr/share/elasticsearch");
//
//
//
//        return new ElasticsearchTemplate(nodeBuilder()
//                .local(true)
//                .settings(elasticsearchSettings.build())
//                .node()
//                .client());
//    }
//}

package thecrevance.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;

@Configuration
@EnableElasticsearchRepositories(basePackages = {"com.coderank.io.dto","com.coderank.io.elasticrepo"})
public class ElasticSearchConfig {

    Settings esSettings = Settings.settingsBuilder()
            .put("cluster.name", "mobstaff")
            .build();

    @Bean
    public Client client() throws Exception {

         return TransportClient.builder()
                 .settings(esSettings)
                 .build()
                 .addTransportAddress(new InetSocketTransportAddress(
                         InetAddress.getByName("localhost"), 9300));
    }


    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws Exception {
        return new ElasticsearchTemplate(client());
    }

}

