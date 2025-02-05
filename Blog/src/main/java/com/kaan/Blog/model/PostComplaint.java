package com.kaan.Blog.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "post_complaints")
public class PostComplaint extends BaseModel{

    @ManyToOne
    @JoinColumn (name = "user_id" , nullable = false)
    private User sender ;

    @ManyToOne
    @JoinColumn(name = "post_id" , nullable = false)
    private Post post ;

    @Column(nullable = false)
    private LocalDateTime sendingTime ;

    @Column(nullable = false)
    private String context ;


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

    public LocalDateTime getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(LocalDateTime sendingTime) {
        this.sendingTime = sendingTime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
