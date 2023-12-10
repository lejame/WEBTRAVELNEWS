package com.example.NewsWebstieJava.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "location", schema = "endterm_java", catalog = "" )
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "location")
    private String location;

    @Column(name = "imageslocation")
    private String imagesLocation;

    @Column(name = "imagescuisine")
    private String imagesCuisine;
}
