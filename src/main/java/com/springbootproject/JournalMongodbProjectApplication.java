package com.springbootproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//@EnableTransactionManagement
@SpringBootApplication
public class JournalMongodbProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalMongodbProjectApplication.class, args);
	}
//	@Bean
//	public PlatformTransactionManager add(MongoDatabaseFactory dbfactory) {
//		return new MongoTransactionManager( dbfactory);
//	}

}
