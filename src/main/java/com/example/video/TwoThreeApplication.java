package com.example.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.example.video.dao")
@SpringBootApplication
public class TwoThreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwoThreeApplication.class, args);
	}

}
