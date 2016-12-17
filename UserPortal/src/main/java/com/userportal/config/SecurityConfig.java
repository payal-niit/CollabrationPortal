package com.userportal.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth
	        .jdbcAuthentication()
	            .dataSource(dataSource)
	            .usersByUsernameQuery("select username, password, enabled from User where username =?;")
	            .authoritiesByUsernameQuery("select username, role from User where username=?;");
	            
	}
	
	@Bean(name="myAuthenticationManager")
	   public AuthenticationManager authenticationManagerBean() throws Exception {
		
	       return super.authenticationManagerBean();
	   }
	
	
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/as/**").access("hasRole('USER')")
            .and()
            .formLogin().loginPage("/loginpage").failureUrl("/loginpage?error=1")
            .usernameParameter("username").passwordParameter("password")
            .loginProcessingUrl("/perform_login").defaultSuccessUrl("/")
            .and()
            .logout().logoutSuccessUrl("/perform_logout").invalidateHttpSession(true).deleteCookies("JSESSIONID")
            .and()
            .csrf().disable();
    }

}
