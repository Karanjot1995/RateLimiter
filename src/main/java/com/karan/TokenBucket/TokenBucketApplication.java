package com.karan.TokenBucket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TokenBucketApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenBucketApplication.class, args);
	}

}
