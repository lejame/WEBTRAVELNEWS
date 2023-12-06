package com.example.NewsWebstieJava.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Table(name = "detailspostuser", schema = "endterm_java", catalog = "" )
public class DetailsPostUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idpostuser")
    private Integer idpostuser;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "summary", length = 2555)
    private String summary;

    @Column(name = "content", length = 2555)
    private String content;

    @Column(name = "date")
    private Date date;

    @Column(name = "category")
    private String category;

    @Column(name = "location")
    private String location;

    @Column(name = "imageshome")
    private String imagesHome;

    @Column(name = "imagesDT1")
    private String imagesDT1;

    @Column(name = "imagesDT2")
    private String imagesDT3;


}
