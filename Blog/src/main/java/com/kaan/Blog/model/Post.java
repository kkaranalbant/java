package com.kaan.Blog.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post extends  BaseEntity{

    @ManyToOne
    @JoinColumn
    private User author ;


    private LocalDateTime creationTime ;

    private boolean isEditted ;


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public boolean isEditted() {
        return isEditted;
    }

    public void setEditted(boolean editted) {
        isEditted = editted;
    }
}
