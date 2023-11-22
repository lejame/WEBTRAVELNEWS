package com.example.NewsWebstieJava.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "viewpost", schema = "endterm_java", catalog = "" )
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ViewPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "view")
    private Integer view;

    @Column(name = "viewofday")
    private Integer viewofday;

    @Column(name = "idpost", insertable = false, updatable = false)
    private Integer idpost;

    @OneToOne
    @JoinColumn(name = "idpost", referencedColumnName = "id")
    private Post postById;
}
