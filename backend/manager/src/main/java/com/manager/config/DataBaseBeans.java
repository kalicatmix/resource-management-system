package com.manager.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.manager.mapper.UserMapper;

@Configuration
@MapperScan(basePackages={"com.manager.mapper"})
public class DataBaseBeans {
    @Bean
	public DriverManagerDataSource driverManagerDataSource() throws IOException {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		ClassPathResource resource=new ClassPathResource("db.properties");
		Properties properties;
		properties=PropertiesLoaderUtils.loadProperties(resource);
		dataSource.setUrl(properties.getProperty("jdbc.url"));
		dataSource.setDriverClassName(properties.getProperty("jdbc.driver"));
		dataSource.setUsername(properties.getProperty("jdbc.user"));
		dataSource.setPassword(properties.getProperty("jdbc.pwd"));
		return dataSource;		
	}
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
    	SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
    	sessionFactoryBean.setDataSource(driverManagerDataSource());
    	return sessionFactoryBean;
    } 
    @Bean
    public DataSourceTransactionManager transactionManager() throws IOException {
    	return new DataSourceTransactionManager(driverManagerDataSource());
    }
}
