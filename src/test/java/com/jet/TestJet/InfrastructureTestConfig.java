package com.jet.TestJet;


import com.jet.TestJet.config.JpaConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@EnableJpaRepositories(basePackages = "com.jet.TestJet.repositories")
@ComponentScan({"com.jet.TestJet.services"})
@Import({JpaConfig.class})
public class InfrastructureTestConfig {
}
