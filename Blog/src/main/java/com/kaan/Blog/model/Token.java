package com.kaan.Blog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tokens")
public class Token extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user ;

    @Column(nullable = false , unique = true)
    private String jwt ;

    @Column(nullable = false)
    private boolean isEnabled ;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
