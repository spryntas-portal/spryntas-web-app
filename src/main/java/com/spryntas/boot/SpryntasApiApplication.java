package com.spryntas.boot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.spryntas.*"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class SpryntasApiApplication implements ApplicationRunner{

	private static final Logger LOGGER = LogManager.getLogger(SpryntasApiApplication.class);
	public static void main(String[] args) {
		LOGGER.info("Starting Spryntas Web Application.........");
		System.setProperty("server.servlet.context-path", "/v1");
		SpringApplication.run(SpryntasApiApplication.class, args);

	}

	public void run(ApplicationArguments args) throws Exception {
		
		for (String name : args.getOptionNames()) {
			System.out.println(("arg-" + name + "=" + args.getOptionValues(name)));
		}

		
		
	}
}
