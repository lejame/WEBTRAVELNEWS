package com.example.NewsWebstieJava.Repository;

import com.example.NewsWebstieJava.Models.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Modifying
    @Transactional
    @Query(value = "Update Post p set p.images = :images, p.imagesDetails1 = :images1, p.imagesDetails2 = :images2 where p.id = :id")
    void updateImagesPath(@Param("images") String images, @Param("images1") String images1, @Param("images2") String images2, @Param("id") Integer id);
}
