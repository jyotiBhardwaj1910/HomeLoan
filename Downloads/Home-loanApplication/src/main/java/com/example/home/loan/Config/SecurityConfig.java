package com.example.home.loan.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            		 .requestMatchers("/login", "/api/customers/login","/loan","/api/loans/submitLoanApplication", "/eligibilityCalculator","/api/loans/calculateEligibility","/raise-request","/api/loans/submitRequest","/api/customers/register","/dashboard","/css/**", "/images/**","/resources/**").permitAll()    //public access
                     .requestMatchers("/homeLoanOffering").hasAnyRole("ADMIN","USER")
                     .requestMatchers("/dashboard").authenticated()
                     .requestMatchers("/loanDetails").authenticated()
                     .requestMatchers("/newHomeLoan").authenticated()
                     .requestMatchers("/homeLoanTracker").authenticated()
                     .requestMatchers("/loanApplication").authenticated()
                     .requestMatchers("/register").authenticated()
                    .requestMatchers("/eligibilityCalculator").authenticated()
                    .requestMatchers("about").authenticated()
                    .requestMatchers("/reach").authenticated()
                    .requestMatchers("/call-us").authenticated()
                    .requestMatchers("/visit-branch").authenticated()
                    .requestMatchers("/raise-request").authenticated()
                    .requestMatchers("/faqs").authenticated()
                   


            //.requestMatchers("/homeLoanOffering").permitAll() 
            .anyRequest().authenticated()

            )
            
            .formLogin(form -> form.disable())
            	   // .loginPage("/login") // Path to your login page
            	   // .defaultSuccessUrl("/homeLoanOffering", true) // Redirect after successful login
            	    //.failureUrl("/login?error=true") // Redirect after a failed login
            	//    .permitAll()
            	//)
            .logout(logout -> logout
            	.logoutSuccessUrl("/login?logout")
                .permitAll()
                
                )
            .httpBasic(httpBasic -> {});
            

        return http.build();
    }
	


	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public UserDetailsService userDetailsService() {
	        UserDetails user = User.builder()
	            .username("user")
	            .password(passwordEncoder().encode("password"))
	            .roles("USER")
	            .build();

	        UserDetails admin = User.builder()
	            .username("admin")
	            .password(passwordEncoder().encode("password"))
	            .roles("ADMIN")
	            .build();

	        return new InMemoryUserDetailsManager(user, admin);
	    }
	}