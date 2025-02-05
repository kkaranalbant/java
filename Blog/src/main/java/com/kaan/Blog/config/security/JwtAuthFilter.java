package com.kaan.Blog.config.security;

import com.kaan.Blog.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService ;

    public JwtAuthFilter(JwtService jwtService ) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie [] cookies = request.getCookies() ;
        String encryptedJwt = null ;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Authorization") && cookie.getValue().startsWith("Bearer+")) {
                encryptedJwt = cookie.getValue().substring(7) ;
                break ;
            }
        }
        if (encryptedJwt == null) {
            filterChain.doFilter(request,response);
        }

        try {
            jwtService.validate(encryptedJwt) ;
        }
        catch (ExpiredJwtException ex) {
            encryptedJwt = jwtService.refresh(encryptedJwt) ;
        }
        UserDetails user = jwtService.getUser(encryptedJwt) ;
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user , null , user.getAuthorities()) ;
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
