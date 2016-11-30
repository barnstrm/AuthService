package com.cognetyx.AuthorizeService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.cognetyx.AuthorizeService.entity.User;

public interface UserService {

    public User getByUsername(String username) throws UsernameNotFoundException;
}