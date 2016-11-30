package com.cognetyx.AuthorizeService.user;

import java.util.HashMap;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognetyx.AuthorizeService.UserService;
import com.cognetyx.AuthorizeService.entity.Role;
import com.cognetyx.AuthorizeService.entity.User;

@Service
public class DummyUserService implements UserService {
    private final HashMap<String, User> userMap = new HashMap<String, User>();

    public DummyUserService()
    {
    	User uOne = new User("Eric", "Eric", "eric@cognetyx.com", Role.PREMIUM_MEMBER);
    	User uTwo = new User("Shiva", "Shiva", "shiva@cognetyx.com", Role.ADMIN);
    	userMap.putIfAbsent(uOne.getUsername(), uOne);
    	userMap.putIfAbsent(uTwo.getUsername(), uTwo);    	
    }
    
    public User getByUsername(String username) throws UsernameNotFoundException 
    {
        final User user = userMap.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }
}
