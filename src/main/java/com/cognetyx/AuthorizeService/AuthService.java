package com.cognetyx.AuthorizeService;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognetyx.AuthorizeService.jwt.JwtAuthenticationToken;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableConfigurationProperties
public class AuthService {

    public static void main(String[] args) {
        SpringApplication.run(AuthService.class, args);
    }
	
    @RequestMapping("/testroute")
    public String testThisRoute()
    {
    	return "Successfully hit the TESTROUTE in the AuthService....";
    }
    
    @RequestMapping("/api/needsAuth")
    public String authRoute(JwtAuthenticationToken token)
    {
    	if(token == null || !token.isAuthenticated())
    		return "You must be authenticated to invoke this service.";
    	
    	return "Successfully hit the AUTHROUTE in the AuthService....";
    }

    // Here we will define our RestService (micro service) for authenticating a user and pass back
	// a response which will either be a JWT or text containing reason for failure.
	@RequestMapping("/auth")
	public String authenticate(HttpServletResponse response, @RequestParam(value="user", required=true) String user,
								 @RequestParam(value="pass", required=true) String pass) 
	{
		String token = null;
		return token;
	}
}
