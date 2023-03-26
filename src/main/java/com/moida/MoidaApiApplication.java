package com.moida;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.moida.mapper")
@SpringBootApplication
public class MoidaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoidaApiApplication.class, args);
	}
}
