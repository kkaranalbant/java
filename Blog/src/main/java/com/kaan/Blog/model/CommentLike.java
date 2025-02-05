package com.kaan.Blog.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment_likes")
public class CommentLike extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User actor ;

    @ManyToOne
    @JoinColumn(name = "comment_id" , nullable = false)
    private Comment comment ;

    @Column(nullable = false)
    private LocalDateTime likingTime ;

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

    public LocalDateTime getLikingTime() {
        return likingTime;
    }

    public void setLikingTime(LocalDateTime likingTime) {
        this.likingTime = likingTime;
    }
}
