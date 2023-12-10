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

    @Column(name = "title", length = 500)
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
    private String imagesDT2;


    public DetailsPostUser(Integer idpostuser, String title, String summary, String content, Date date, String category, String location, String imagesHome, String imagesDT1, String imagesDT3) {
        this.idpostuser = idpostuser;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.date = date;
        this.category = category;
        this.location = location;
        this.imagesHome = imagesHome;
        this.imagesDT1 = imagesDT1;
        this.imagesDT2 = imagesDT3;
    }

    @Override
    public String toString() {
        return "DetailsPostUser{" +
                "id=" + id +
                ", idpostuser=" + idpostuser +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", category='" + category + '\'' +
                ", location='" + location + '\'' +
                ", imagesHome='" + imagesHome + '\'' +
                ", imagesDT1='" + imagesDT1 + '\'' +
                ", imagesDT3='" + imagesDT2 + '\'' +
                '}';
    }
}
