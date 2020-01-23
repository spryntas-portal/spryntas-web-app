package com.spryntas.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer{
	
	@Autowired
	Environment env;

	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	        .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")
	        .allowedOrigins("*")
	       .allowedHeaders("*");
	    }
	 

	 @Bean
	    public JavaMailSender getJavaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost(env.getProperty("spring.mail.host"));
	        mailSender.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
	        mailSender.setUsername(env.getProperty("spring.mail.username"));
	        mailSender.setPassword(env.getProperty("spring.mail.password"));
	         
	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smpt.host", "smtp.gmail.com");
	        props.put("mail.smtp.auth", "true");
	     //   props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");
	        props.put("mail.smtp.port", Integer.parseInt(env.getProperty("spring.mail.port")));
	        props.put("mail.smtp.ssl.enable", "true");
	         
	        return mailSender;
	    }
	 
	 @Bean (name="encryptor")
		public StringEncryptor getEncoder() {
			PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
			SimpleStringPBEConfig config = new SimpleStringPBEConfig();
			config.setPassword(env.getProperty("jasypt.encryptor.password"));
			config.setAlgorithm("PBEWithMD5AndDES");
			config.setKeyObtentionIterations("1000");
			config.setPoolSize("1");
			config.setProviderName("SunJCE");
			config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
			config.setStringOutputType("base64");
			encryptor.setConfig(config);
			return encryptor;
		}
	 
		@Bean
	    public DataSource dataSource(){
		       DriverManagerDataSource dataSource = new DriverManagerDataSource();
		       dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		       dataSource.setUrl(env.getProperty("spring.datasource.url"));
		       dataSource.setUsername(env.getProperty("spring.datasource.username"));
		       dataSource.setPassword(env.getProperty("spring.datasource.password"));
		       return dataSource;
	    }
		
		 @Override
		    public void addResourceHandlers(ResourceHandlerRegistry registry) {

		            registry.addResourceHandler("swagger-ui.html")
		                    .addResourceLocations("classpath:/META-INF/resources/");

		            registry.addResourceHandler("/webjars/**")
		                    .addResourceLocations("classpath:/META-INF/resources/webjars/");

		    }

}
