package com.cognetyx.AuthorizeService.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognetyx.AuthorizeService.jwt.JwtAuthenticationToken;
import com.cognetyx.AuthorizeService.model.UserContext;

/**
 * End-point for retrieving logged-in user details.
 */
@RestController
public class ProfileEndpoint {
    @RequestMapping(value="/api/me", method=RequestMethod.GET)
    public @ResponseBody UserContext get(JwtAuthenticationToken token) {
        return (UserContext) token.getPrincipal();
    }
}