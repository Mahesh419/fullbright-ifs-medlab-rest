package com.fullbright.medlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@ComponentScan("com.fullbright.medlab")
@SpringBootApplication
public class MedicalLaboratoryRestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalLaboratoryRestServiceApplication.class, args);
	}
	
	 @Bean
	   public WebMvcConfigurer corsConfigurer() {
	      return new WebMvcConfigurerAdapter() {
	         @Override
	         public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/user").allowedOrigins("https://nawalokamedlabs.herokuapp.com/");
	         }
	      };
	   }
}

