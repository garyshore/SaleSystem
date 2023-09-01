package com.shore.distribution.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 *
 * 数据源配置类
 *
 */
@Configuration
public class DataSourceConfig {

    public static String thisSystemParam;

    @Autowired
    public void setThisSystemParam(@Value("${system.type}") String thisSystemParam) {
        DataSourceConfig.thisSystemParam = thisSystemParam;
    }

    @Bean(name = "cloud")
    @ConfigurationProperties(prefix = "spring.datasource.cloud")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "base")
    @ConfigurationProperties(prefix = "spring.datasource.base")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "shardTransactionManager")
    public PlatformTransactionManager txManager(@Qualifier("base") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}