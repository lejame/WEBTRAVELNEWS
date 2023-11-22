package com.example.NewsWebstieJava.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "author", schema = "endterm_java", catalog = "" )
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "post")
    private Integer post;

    @Column(name = "idAccount", insertable = false, updatable = false)
    private Integer idAccount;

    @OneToOne
    @JoinColumn(name = "idAccount", referencedColumnName = "id")
    private Account accountByIdAccount;

    @OneToMany(mappedBy = "authorByIdAuthor")
    private Collection<Post> postCollection;


}
