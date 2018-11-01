package com.se.fishbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.se.fishbook.mapper")
@ComponentScan("com.se.fishbook.*")
public class FishbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishbookApplication.class, args);
	}
}
