package com.order.system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("{noop}admin").roles("ADMIN")
			.and()
			.withUser("chef").password("{noop}chef").roles("CHEF");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement()
		.and()
			.authorizeRequests()
//			.antMatchers("/", "/home", "/css/**", "/images/**", "/item/**", "/category/**", "/addtocart/**",
//					"/cart/**", "/checkout/**")
//			.permitAll()
			.antMatchers("/admin", "/admin/**").hasRole("ADMIN")
			.antMatchers("/chef", "/chef/**").hasRole("CHEF")
			.antMatchers("/**").permitAll()
			.antMatchers(HttpMethod.POST,"/**").permitAll()
			.and().formLogin().and().csrf().disable();
		
//		http.sessionManagement()
//			.and()
//			.authorizeRequests()
//			.antMatchers("/", "/home", "/css/**", "/images/**", "/item/**", "/category/**", "/addtocart/**",
//					"/cart/**", "/checkout/**").anonymous()
//			.antMatchers("/admin", "/admin/**").hasRole("ADMIN")
//			.antMatchers("/chef", "/chef/**").hasRole("CHEF")
	}
	
}
