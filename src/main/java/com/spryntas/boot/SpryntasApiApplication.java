package com.spryntas.boot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.spryntas"})
@SpringBootApplication
public class SpryntasApiApplication implements ApplicationRunner{

	private static final Logger logger = LogManager.getLogger(SpryntasApiApplication.class);
	public static void main(String[] args) {
		 logger.debug("Debugging log");
	        logger.info("Info log");
	        logger.warn("Hey, This is a warning!");
	        logger.error("Oops! We have an Error. OK");
	        logger.fatal("Damn! Fatal error. Please fix me.");
		SpringApplication.run(SpryntasApiApplication.class, args);

	}

	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
		for (String name : args.getOptionNames()) {
			System.out.println(("arg-" + name + "=" + args.getOptionValues(name)));
		}

		
		
	}
}
