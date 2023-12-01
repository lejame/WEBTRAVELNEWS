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

    @Column(name = "date")
    private Date date;

    @Column(name = "view")
    private int view;

    @Column(name = "author")
    private String author;


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
                ", date=" + date +
                ", author='" + author + '\'' +
                ", view=" + view +
                '}';
    }
}
