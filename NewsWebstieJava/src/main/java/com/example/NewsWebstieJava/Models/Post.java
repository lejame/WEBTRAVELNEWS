package com.example.NewsWebstieJava.Models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "post", schema = "endterm_java", catalog = "" )
@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "category")
    private String category;

    @Column(name = "location")
    private String location;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "summary")
    private String summary;

    @Column(name = "imagesHome")
    private String images;

    @Column(name = "imagesDetails1")
    private String imagesDetails1;

    @Column(name = "imagesDetails2")
    private String imagesDetails2;

    @Column(name = "date")
    private Date date;

    @Column(name = "view")
    private int view;

    @Column(name = "author")
    private String author;


    public Post(String category, String location, String title, String content, String summary, String images, String imagesDetails1, String imagesDetails2, Date date, int view, String author) {
        this.category = category;
        this.location = location;
        this.title = title;
        this.content = content;
        this.summary = summary;
        this.images = images;
        this.imagesDetails1 = imagesDetails1;
        this.imagesDetails2 = imagesDetails2;
        this.date = date;
        this.view = view;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", location='" + location + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", summary='" + summary + '\'' +
                ", images='" + images + '\'' +
                ", imagesDetails1='" + imagesDetails1 + '\'' +
                ", imagesDetails2='" + imagesDetails2 + '\'' +
                ", date=" + date +
                ", view=" + view +
                ", author='" + author + '\'' +
                '}';
    }
}
