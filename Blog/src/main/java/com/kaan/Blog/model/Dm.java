package com.kaan.Blog.model;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "dms")
public class Dm extends BaseModel{

    @ManyToOne
    @JoinColumn(nullable = false , name = "sender_id")
    private User sender ;


    @ManyToOne
    @JoinColumn(nullable = false , name = "receiver_id")
    private User receiver ;

    private LocalDateTime sendingTime ;

    private String context ;


    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
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
