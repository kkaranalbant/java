package com.kaan.Blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author ;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post ;

    private LocalDateTime commentTime ;

    private boolean isEditted ;

    private String context ;

}
