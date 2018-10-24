package com.se.fishbook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.se.fishbook.mapper")
public class FishbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishbookApplication.class, args);
	}
}
