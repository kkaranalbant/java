package com.kaan.Blog.service;

import com.kaan.Blog.exception.UserException;
import com.kaan.Blog.model.User;
import com.kaan.Blog.repo.UserRepo;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {

    private UserRepo userRepo ;

    public UserService (UserRepo userRepo) {
        this.userRepo = userRepo ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username Not Found")) ;
    }

    public User getUserById (Long userId) {
        return userRepo.findById(userId).orElseThrow(() -> new UserException("User Not Found")) ;
    }

    public Long getUserIdByUsername (String username) throws UserException{
        User user = userRepo.findUserByUsername(username).orElse(null) ;
        if (user != null) {
            return user.getId() ;
        }
        throw new UserException("Username Not Found") ;
    }

}
