package com.mmi.mmi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	@Lazy
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void configure (AuthorizationServerEndpointsConfigurer endpoints) throws Exception
	{
		endpoints
			.tokenStore(tokenStore())
			.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService);
	}

	@Override
	public void configure (AuthorizationServerSecurityConfigurer security) throws Exception
	{
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure (ClientDetailsServiceConfigurer clients) throws Exception
	{
		clients.inMemory().withClient("app")
				.authorizedGrantTypes("password", "implicit", "refresh_token", "authorization_code").autoApprove(true)
				.secret(bCryptPasswordEncoder.encode("app")).scopes("read", "write")
				.accessTokenValiditySeconds(72000)
				.refreshTokenValiditySeconds(80000);
	}

	@Bean
	public TokenStore tokenStore ()
	{
		return new InMemoryTokenStore();
	}

}
