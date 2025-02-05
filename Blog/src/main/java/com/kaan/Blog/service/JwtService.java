package com.kaan.Blog.service;

import com.kaan.Blog.model.Token;
import com.kaan.Blog.repo.TokenRepo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret ;

    @Value("${jwt.expiration}")
    private String expiration ;

    private UserService userService ;

    private TokenRepo tokenRepo ;

    public JwtService(UserService userService , TokenRepo tokenRepo) {
        this.userService = userService;
        this.tokenRepo = tokenRepo ;
    }

    private byte [] decodeSecret (){
        return Decoders.BASE64.decode(secret) ;
    }

    private SecretKey getSecretKey () {
        return Keys.hmacShaKeyFor(decodeSecret()) ;
    }

    private JwtParser getParser () {
        return Jwts.parser().setSigningKey(getSecretKey()).build() ;
    }

    public String createJwt (Long userId , String username) {
        Map<String , Long> idClaim = new HashMap<>() ;
        idClaim.put("id",userId) ;
        String jwt = Jwts.builder().claims(idClaim)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .compact() ;
        Token token = new Token() ;
        token.setJwt(jwt);
        token.setUser(userService.getUserById(userId));
        token.setEnabled(true);
        tokenRepo.save(token) ;
        return jwt ;
    }

    public String refresh (String jwt) {
        Claims claims = extractClaims(jwt) ;
        Long userId = Long.parseLong(String.valueOf(claims.get("id"))) ;
        String username = claims.getSubject();
        String newJwt = createJwt(userId , username) ;
        Optional<Token> tokenOptional = tokenRepo.findByJwt(jwt) ;
        if (tokenOptional.isPresent()) {
            tokenOptional.get().setEnabled(false);
            tokenRepo.save(tokenOptional.get()) ;
        }
        return newJwt ;
    }

    private Claims extractClaims (String jwt) {
        Claims claims = null ;
        try {
            claims = getParser().parseSignedClaims(jwt).getPayload() ;
        }
        catch (ExpiredJwtException ex) {
            claims = ex.getClaims() ;
        }
        return claims ;
    }

    public void validate (String jwt) throws JwtException , com.kaan.Blog.exception.JwtException {
        Optional<Token> tokenOptional = tokenRepo.findByJwt(jwt) ;
        if (tokenOptional.isEmpty()) throw new com.kaan.Blog.exception.JwtException("Jwt Not Found !");
        getParser().parse(jwt) ;
    }

    public Token getByUserId (Long userId) {
        return tokenRepo.findByUserId(userId).orElse(null) ;
    }



}
