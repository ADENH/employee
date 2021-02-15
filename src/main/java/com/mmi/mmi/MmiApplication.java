package com.mmi.mmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass=true)  
@EnableJpaRepositories
public class MmiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MmiApplication.class, args);
	}

}
