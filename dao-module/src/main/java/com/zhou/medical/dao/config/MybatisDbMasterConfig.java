package com.zhou.medical.dao.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author zhouzhou
 *
 */
@Configuration
@MapperScan(basePackages = {"com.zhou.medical"}, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MybatisDbMasterConfig {

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;

    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(masterDataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return factoryBean.getObject();

    }

    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate masterSqlSessionTemplate() throws Exception {
        // 使用上面配置的Factory
        SqlSessionTemplate template = new SqlSessionTemplate(masterSqlSessionFactory());
        return template;
    }

}
