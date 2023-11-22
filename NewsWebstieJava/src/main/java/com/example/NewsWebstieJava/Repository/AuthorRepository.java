package com.example.NewsWebstieJava.Repository;

import com.example.NewsWebstieJava.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
