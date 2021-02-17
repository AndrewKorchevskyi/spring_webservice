package com.simplewebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SimpleWebserviceApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SimpleWebserviceApplication.class, args);
		BinarySearchImpl binarySearch = applicationContext.getBean(BinarySearchImpl.class);
		int result = binarySearch.search(new int[] {1,3,2,5,7,6,4}, 3);
		System.out.println(result);

	}
}
