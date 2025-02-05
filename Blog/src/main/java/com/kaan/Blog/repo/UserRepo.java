package com.kaan.Blog.repo;

import com.kaan.Blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    public Optional<User> findUserByUsername (String username) ;

}
