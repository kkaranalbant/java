package com.kaan.Blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseModel{

    @JoinColumn(nullable = false , name = "user_id")
    @ManyToOne
    private User sender ;

    @ManyToOne
    @JoinColumn(nullable = false , name = "post_id")
    private Post post ;

    private String context ;

    private LocalDateTime sendingTime ;

    private boolean isEditted ;

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public LocalDateTime getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(LocalDateTime sendingTime) {
        this.sendingTime = sendingTime;
    }

    public boolean isEditted() {
        return isEditted;
    }

    public void setEditted(boolean editted) {
        isEditted = editted;
    }
}

