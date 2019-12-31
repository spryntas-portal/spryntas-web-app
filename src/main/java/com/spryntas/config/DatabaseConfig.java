package com.spryntas.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {
	
//	@Value("${spring.datasource.driver-class-name}")
//	private String dbDriverClassName;
//	
//	@Value("${spring.datasource.url}")
//	private String dbUrl;
//	
//	@Value("${spring.datasource.username}")
//	private String dbUsername;
//	
//	@Value("${spring.datasource.password}")
//	private String dbPassword;

	@Bean
    public DataSource dataSource(){
	       DriverManagerDataSource dataSource = new DriverManagerDataSource();
	       dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	       dataSource.setUrl("jdbc:mysql://spryntas-rds-mysql.coo1deslnioz.ap-south-1.rds.amazonaws.com:3306/spryntas_dev?useSSL=false");
	       dataSource.setUsername( "master" );
	       dataSource.setPassword( "master123" );
	       return dataSource;
    }
}