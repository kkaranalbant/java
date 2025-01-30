package com.kaan.Blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_likes")
public class PostLike extends BaseEntity{

    @ManyToOne
    @JoinColumn(name="actor_id" , nullable = false )
    private User actor ;

    @ManyToOne
    @JoinColumn(name = "post_id" , nullable = false)
    private Post post ;

    private LocalDateTime likingTime ;

    public User getActor() {
        return actor;
    }

    public void setActor(User actor) {
        this.actor = actor;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getLikingTime() {
        return likingTime;
    }

    public void setLikingTime(LocalDateTime likingTime) {
        this.likingTime = likingTime;
    }
}
