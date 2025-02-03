package com.example.home.loan.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webCofig implements WebMvcConfigurer {
	
	  @Override public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:8081") 
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
		.allowCredentials(true)
		.allowedHeaders("*");
}
	  
	  @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
		   registry.addViewController("/homeLoanOffering").setViewName("HomeLoanOffering");
	       registry.addViewController("/dashboard").setViewName("Dashboard");
	       registry.addViewController("/loanDetails").setViewName("LoanDetails");
	       registry.addViewController("/newHomeLoan").setViewName("NewHomeLoan");
	       registry.addViewController("/homeLoanTracker").setViewName("HomeLoanTracker");
	       registry.addViewController("/register").setViewName("Register");
	       registry.addViewController("/loanApplication").setViewName("LoanApplication");
	       registry.addViewController("/eligibilityCalculator").setViewName("EligibilityCalculator");
	       registry.addViewController("/about").setViewName("About");
	       registry.addViewController("/reach").setViewName("Reach");
	       registry.addViewController("/faqs").setViewName("FAQs");
	       registry.addViewController("/raise-request").setViewName("Raise-Request");
	       registry.addViewController("/visit-branch").setViewName("Visit-Branch");
	       registry.addViewController("/call-us").setViewName("Call-Us");
	     
	       
	    }
}
	  