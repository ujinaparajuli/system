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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.order.system.service.impl.CustomUserDetailsService;

import javax.sql.DataSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private DataSource dataSource;
	
	@Autowired
	LoginSuccessHandler loginSuccessHandler;
     
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
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
			.antMatchers("/user", "/user/**").hasRole("USER")
			.antMatchers("/**").permitAll()
			.antMatchers(HttpMethod.POST,"/**").permitAll()
			.and().formLogin().loginPage("/login").successHandler(loginSuccessHandler).and().logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").and().csrf().disable();
		
//		http.sessionManagement()
//			.and()
//			.authorizeRequests()
//			.antMatchers("/", "/home", "/css/**", "/images/**", "/item/**", "/category/**", "/addtocart/**",
//					"/cart/**", "/checkout/**").anonymous()
//			.antMatchers("/admin", "/admin/**").hasRole("ADMIN")
//			.antMatchers("/chef", "/chef/**").hasRole("CHEF")
	}
	
}
