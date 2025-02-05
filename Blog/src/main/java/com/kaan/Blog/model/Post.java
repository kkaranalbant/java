package com.kaan.Blog.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post extends BaseModel{

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User author ;

    @Column(nullable = false)
    private String title ;

    @Column(nullable = false)
    private LocalDateTime creatingTime ;

    @Column(nullable = false)
    private boolean isEditted ;

    @Column(nullable = true)
    private byte [] image ;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatingTime() {
        return creatingTime;
    }

    public void setCreatingTime(LocalDateTime creatingTime) {
        this.creatingTime = creatingTime;
    }

    public boolean isEditted() {
        return isEditted;
    }

    public void setEditted(boolean editted) {
        isEditted = editted;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
