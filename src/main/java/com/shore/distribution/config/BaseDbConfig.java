package com.shore.distribution.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 *
 * base 数据源配置
 *
 */
@Configuration
@MapperScan(basePackages = BaseDbConfig.PACKAGE, sqlSessionFactoryRef = "baseSqlSessionFactory")
public class BaseDbConfig {

    public static final String PACKAGE = "com.shore.distribution.dao";
    public static final String MAPPER_LOCATION = "classpath*:mapper/*.xml";

    @Autowired
    @Qualifier("base")
    private DataSource baseDs;

    @Bean
    public SqlSessionFactory baseSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(BaseDbConfig.MAPPER_LOCATION);
        factoryBean.setMapperLocations(resources);
        factoryBean.setDataSource(baseDs);
        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate baseSqlSessionTemplate() throws Exception {
        // 使用注解中配置的Factory
        SqlSessionTemplate template = new SqlSessionTemplate(baseSqlSessionFactory());
        return template;
    }


}

