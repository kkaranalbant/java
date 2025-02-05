package com.kaan.Blog.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "blocks")
public class Block extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "blocker_id" , nullable = false)
    private User blocker ;

    @ManyToOne
    @JoinColumn(name = "blocked_id" , nullable = false)
    private User blocked ;

    @Column(nullable = false)
    private LocalDateTime blockingTime ;

    public User getBlocker() {
        return blocker;
    }

    public void setBlocker(User blocker) {
        this.blocker = blocker;
    }

    public User getBlocked() {
        return blocked;
    }

    public void setBlocked(User blocked) {
        this.blocked = blocked;
    }

    public LocalDateTime getBlockingTime() {
        return blockingTime;
    }

    public void setBlockingTime(LocalDateTime blockingTime) {
        this.blockingTime = blockingTime;
    }
}
