package com.kaan.Blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment_likes")
public class CommentLike extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "actor_id")
    private User actor ;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment ;

    private LocalDateTime likeTime ;

    public User getActor() {
        return actor;
    }

    public void setActor(User actor) {
        this.actor = actor;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public LocalDateTime getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(LocalDateTime likeTime) {
        this.likeTime = likeTime;
    }
}
