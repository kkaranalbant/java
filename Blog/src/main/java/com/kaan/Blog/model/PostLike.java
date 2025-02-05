package com.kaan.Blog.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_likes")
public class PostLike extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User actor ;

    @ManyToOne
    @JoinColumn(name = "post_id" , nullable = false)
    private Comment post ;

    @Column(nullable = false)
    private LocalDateTime likingTime ;

    public User getActor() {
        return actor;
    }

    public void setActor(User actor) {
        this.actor = actor;
    }

    public Comment getPost() {
        return post;
    }

    public void setPost(Comment post) {
        this.post = post;
    }

    public LocalDateTime getLikingTime() {
        return likingTime;
    }

    public void setLikingTime(LocalDateTime likingTime) {
        this.likingTime = likingTime;
    }
}
