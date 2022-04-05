package com.project.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamApplication 
{
	private static final Logger logger = LoggerFactory.getLogger(ExamApplication.class);
	
	public static void main(String[] args) 
	{
		SpringApplication.run(ExamApplication.class, args);
		logger.info("ExamProject Aplication started");
	}
}
