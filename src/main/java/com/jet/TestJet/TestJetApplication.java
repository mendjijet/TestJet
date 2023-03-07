package com.jet.TestJet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TestJetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestJetApplication.class, args);
	}

}
