package com.yy.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @Description TODO
 * @Date 2022/8/20 14:14
 */
@Configuration
@ComponentScan({"com.yy"})
// @EnableTransactionManagement(proxyTargetClass = true);
// proxyTargetClass = true，可以解决AccountServiceImpl accountService;声明的类注入失败问题
// 开启spring的声明式事务,等价于xml配置方式的 <tx:annotation-driven />,@EnableTransactionManagement需要和@Transactional注解搭配使用
@EnableTransactionManagement(proxyTargetClass = true)
//@EnableAspectJAutoProxy(exposeProxy = true)
public class JDBCTemplateConfig {

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
