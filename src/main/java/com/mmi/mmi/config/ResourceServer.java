package com.mmi.mmi.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
        .csrf().disable()
        .exceptionHandling()
        .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS).permitAll()
		.antMatchers("/").permitAll()
		// .antMatchers("/survey/**").authenticated()
		 .antMatchers("/api/**").authenticated()
		.antMatchers("/capi/**").authenticated()
//		.antMatchers("/graphql/**").authenticated()
		.antMatchers("/altair/**").authenticated() // pas production di comment
//        .antMatchers("/**").authenticated()
        .and()
        .httpBasic();
	}
}
