package com.webook.config;

import net.sf.log4jdbc.DriverSpy;
import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by bangae1 on 2016-11-29.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.webook.*.mapper"})
@PropertySource("classpath:application.properties")
public class DataSourceConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(env.getProperty("datasource.username"));
        dataSource.setPassword(env.getProperty("datasource.password"));
        dataSource.setUrl(env.getProperty("datasource.url"));
        dataSource.setDriverClassName(env.getProperty("datasource.driver"));
        dataSource.setValidationQuery(env.getProperty("datasource.validationQuery"));
        dataSource.setTestWhileIdle(Boolean.parseBoolean(env.getProperty("datasource.testWhileIdle")));
        dataSource.setInitialSize(3);
        dataSource.setMaxIdle(20);
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource());
        return dataSourceTransactionManager;
    }

}
