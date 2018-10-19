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
@MapperScan(basePackages = {"com.zhou.medical"}, sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class MybatisDbSlaveConfig {

    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slaveDataSource;

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory slaveSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(slaveDataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return factoryBean.getObject();

    }

    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate slaveSqlSessionTemplate() throws Exception {
        // 使用上面配置的Factory
        SqlSessionTemplate template = new SqlSessionTemplate(slaveSqlSessionFactory());
        return template;
    }

}
