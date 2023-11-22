package com.example.NewsWebstieJava.Repository;

import com.example.NewsWebstieJava.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
