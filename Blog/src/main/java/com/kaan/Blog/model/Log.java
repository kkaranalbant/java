package com.kaan.Blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class Log extends BaseModel{

    @Column(nullable = false)
    private String type ;

    @Column(nullable = false)
    private String message ;

    @Column(nullable = false)
    private LocalDateTime loggingTime ;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLoggingTime() {
        return loggingTime;
    }

    public void setLoggingTime(LocalDateTime loggingTime) {
        this.loggingTime = loggingTime;
    }
}
