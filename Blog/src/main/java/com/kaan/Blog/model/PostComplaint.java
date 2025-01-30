package com.kaan.Blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_complaints")
public class PostComplaint extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_şd")
    private User user ;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post ;

    private LocalDateTime complaintTime ;

    private String context ;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(LocalDateTime complaintTime) {
        this.complaintTime = complaintTime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
