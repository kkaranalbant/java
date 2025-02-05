package com.kaan.Blog.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ComponentConfig {

    private UserDetailsService userDetailsService ;


    public ComponentConfig (UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService ;
    }
    @Bean
    public AuthenticationProvider authenticationProvider () {
        DaoAuthenticationProvider aProvider = new DaoAuthenticationProvider() ;
        aProvider.setUserDetailsService(userDetailsService);
        aProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return aProvider ;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder () {
        return new BCryptPasswordEncoder() ;
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager() ;
    }


}
