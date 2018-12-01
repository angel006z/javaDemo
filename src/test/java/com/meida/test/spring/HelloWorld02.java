package com.meida.test.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.meida.test.spring")
public class HelloWorld02 {
	@Bean(name = "aa")
	public HelloWorld aa() {
		return new HelloWorld();
	}
}
