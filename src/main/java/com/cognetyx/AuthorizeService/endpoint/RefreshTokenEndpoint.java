package com.cognetyx.AuthorizeService.endpoint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognetyx.AuthorizeService.SpringSecurityConfig;
import com.cognetyx.AuthorizeService.UserService;
import com.cognetyx.AuthorizeService.config.JwtSettings;
import com.cognetyx.AuthorizeService.entity.User;
import com.cognetyx.AuthorizeService.exceptions.InvalidJwtToken;
import com.cognetyx.AuthorizeService.jwt.extractor.TokenExtractor;
import com.cognetyx.AuthorizeService.jwt.verifier.TokenVerifier;
import com.cognetyx.AuthorizeService.model.UserContext;
import com.cognetyx.AuthorizeService.model.token.JwtToken;
import com.cognetyx.AuthorizeService.model.token.JwtTokenFactory;
import com.cognetyx.AuthorizeService.model.token.RawAccessJwtToken;
import com.cognetyx.AuthorizeService.model.token.RefreshToken;

/**
 * RefreshTokenEndpoint
 * 
 */

@RestController
public class RefreshTokenEndpoint {

    @Autowired private JwtTokenFactory tokenFactory;
    @Autowired private JwtSettings jwtSettings;
    @Autowired private UserService userService;
    @Autowired private TokenVerifier tokenVerifier;
    @Autowired @Qualifier("jwtHeaderTokenExtractor") private TokenExtractor tokenExtractor;

    @RequestMapping(value="/api/auth/token", method=RequestMethod.GET, produces={ MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody JwtToken refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String tokenPayload = tokenExtractor.extract(request.getHeader(SpringSecurityConfig.JWT_TOKEN_HEADER_PARAM));
        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(() -> new InvalidJwtToken());
        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
       	User user = userService.getByUsername(subject);
       	if (user.getRole() == null) throw new InsufficientAuthenticationException("User has no roles assigned");
       	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
       	authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        UserContext userContext = UserContext.create(user.getUsername(), authorities);
        return tokenFactory.createAccessJwtToken(userContext);
    }
}
