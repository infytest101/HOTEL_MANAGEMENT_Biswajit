package com.infosys.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HotelManagmentSystemApplication {
static Logger logger = LoggerFactory.getLogger(HotelManagmentSystemApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(HotelManagmentSystemApplication.class, args);
		logger.info("<<<<<<<<<<<<<<<<<<<appication Started>>>>>>>>>>>>>>>");
	}

}
