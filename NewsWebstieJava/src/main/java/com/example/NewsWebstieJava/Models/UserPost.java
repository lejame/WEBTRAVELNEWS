package com.example.NewsWebstieJava.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
@Table(name = "userpost", schema = "endterm_java", catalog = "" )
public class UserPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "iduser")
    private Integer iduser;

    @Column(name = "email")
    private String email;

    @Column(name = "nameuser")
    private String nameuser;

    @Column(name = "status")
    private Integer status;

    public UserPost(Integer iduser, String email, String nameuser, Integer status) {
        this.iduser = iduser;
        this.email = email;
        this.nameuser = nameuser;
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "id=" + id +
                ", iduser=" + iduser +
                ", email='" + email + '\'' +
                ", nameuser='" + nameuser + '\'' +
                ", status=" + status +
                '}';
    }
}
