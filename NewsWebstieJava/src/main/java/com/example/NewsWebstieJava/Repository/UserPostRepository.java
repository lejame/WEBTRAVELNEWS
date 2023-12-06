package com.example.NewsWebstieJava.Repository;

import com.example.NewsWebstieJava.Models.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostRepository extends JpaRepository<UserPost, Integer> {
}
