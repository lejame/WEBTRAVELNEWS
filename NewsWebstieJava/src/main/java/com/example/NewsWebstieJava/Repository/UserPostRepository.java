package com.example.NewsWebstieJava.Repository;

import com.example.NewsWebstieJava.Models.UserPost;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserPostRepository extends JpaRepository<UserPost, Integer> {
    @Modifying
    @Transactional
    @Query(value = "update UserPost p set p.status = :status where p.id = :id")
    void updateStatusPostUser(@Param("id") Integer id, @Param("status") Integer status);
}
