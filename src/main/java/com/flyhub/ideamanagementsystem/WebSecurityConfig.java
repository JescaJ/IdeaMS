package com.flyhub.ideamanagementsystem;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.flyhub.ideamanagementsystem.services.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource; // so as to connect to the database to access the details
	
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
	
	
	/// original method

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/list_users","/add_idea","/process_add_idea").permitAll()
		.anyRequest().permitAll()
		.and().csrf().disable()
		.formLogin()
			.usernameParameter("primary_email")
			.defaultSuccessUrl("/list_users")
			.permitAll()
		.and()
		.logout().logoutSuccessUrl("/list_ideas").permitAll();
	}
	
	
	// end of original
	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//        	.antMatchers("/register").permitAll()
//        	.antMatchers("/").permitAll()
////            .antMatchers("/").hasAnyAuthority("USER", "ADMIN")
//            .antMatchers("/edit/**").hasAnyAuthority("ADMIN", "USER")
//            .antMatchers("/list_users").hasAnyAuthority("ADMIN")
//            .antMatchers("/list_ideas").hasAnyAuthority("ADMIN", "USER")
//            .antMatchers("/idea/delete/**").hasAnyAuthority("ADMIN")
//            .antMatchers("/user/delete/**").hasAnyAuthority("ADMIN")
//            .antMatchers("/delete/**").hasAuthority("ADMIN")
////            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .defaultSuccessUrl("/")
//            .permitAll()
//            .and()
//            .logout()
//            .logoutSuccessUrl("/")
//            .permitAll()
//            .and()
//            .exceptionHandling().accessDeniedPage("/403")
//            ;
//    }
	
	
}
