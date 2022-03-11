package com.sanowar.inventoryManApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.sanowar.inventoryManApi"})
public class InventoryManApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.out.println("Start Running...");
		SpringApplication.run(InventoryManApp.class, args);
		System.out.println("Application Running Successfully...!");
	}

}
