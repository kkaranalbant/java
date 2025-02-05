package com.kaan.Blog.repo;

import com.kaan.Blog.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Long> {

    public Optional<Token> findByJwt (String jwt) ;

    public Optional<Token> findByUserId (Long userId) ;

}
