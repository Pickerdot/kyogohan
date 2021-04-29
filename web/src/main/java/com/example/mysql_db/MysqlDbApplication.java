package com.example.mysql_db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MysqlDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlDbApplication.class, args);
	}

}
