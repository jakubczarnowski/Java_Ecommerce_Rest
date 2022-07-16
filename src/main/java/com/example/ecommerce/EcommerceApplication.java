package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class EcommerceApplication {
	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(EcommerceApplication.class, args);
	}

}
