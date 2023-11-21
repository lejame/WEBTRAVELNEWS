package com.example.NewsWebstieJava.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "post", schema = "endterm_java", catalog = "" )
@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "summary")
    private String summary;

    @Column(name = "imagesHome")
    private String images;



}
