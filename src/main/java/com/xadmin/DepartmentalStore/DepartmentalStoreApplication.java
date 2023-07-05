package com.xadmin.DepartmentalStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DepartmentalStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentalStoreApplication.class, args);
	}

}
