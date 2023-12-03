package com.example.NewsWebstieJava.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "account", schema = "endterm_java", catalog = "" )
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "role")
    private String role;

    @Column(name = "code")
    private String code;



    public Account(String email, String password, String fullname, String role, String code) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.code = code;
    }
}
