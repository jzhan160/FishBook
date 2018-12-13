package com.se.fishbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//this is the initiating class for SpringBoot Application
@SpringBootApplication
@MapperScan("com.se.fishbook.mapper")
@ComponentScan("com.se.fishbook.*")
public class FishbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishbookApplication.class, args);
	}
}
