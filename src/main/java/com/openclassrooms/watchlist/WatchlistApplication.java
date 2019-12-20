package com.openclassrooms.watchlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//* @SpringBootApplication combines the three tags below in one
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class WatchlistApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(WatchlistApplication.class, args);
		String[] allBeans = applicationContext.getBeanDefinitionNames();
		for (String beanName : allBeans) {
			System.out.println(beanName);
		}
//		System.out.println(applicationContext.getBean("movieRatingServiceLiveImpl").toString());

	}
}


