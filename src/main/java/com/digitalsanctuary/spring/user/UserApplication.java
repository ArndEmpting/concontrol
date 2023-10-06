package com.digitalsanctuary.spring.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class UserApplication. Basic Spring Boot Application Setup. Adds Async support and Scheduling support to the default Spring Boot stack.
 */
@Slf4j
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class UserApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		log.info("Starting UserApplication...");
		SpringApplication.run(UserApplication.class, args);
		log.info("UserApplication started.");
	}
/*	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}*/
}
