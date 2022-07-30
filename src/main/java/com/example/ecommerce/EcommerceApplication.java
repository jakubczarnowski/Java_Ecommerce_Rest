package com.example.ecommerce;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {
	public static void main(String[] args) {
		ApplicationContext appContext = SpringApplication.run(EcommerceApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(@Autowired CategoryRepository repository) {
		return (args) -> {
			try{
				repository.save(new Category("root", "rootCategory", ""));
			}catch(Exception e){

			}
		};
	}

}
