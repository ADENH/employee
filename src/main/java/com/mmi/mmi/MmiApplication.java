package com.mmi.mmi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)  
public class MmiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MmiApplication.class, args);
	}

}
