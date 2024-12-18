package com.example.NewsWebstieJava.Repository;

import com.example.NewsWebstieJava.Models.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Modifying
    @Transactional
    @Query(value = "Update Post p set p.images = :images, p.imagesDetails1 = :images1, p.imagesDetails2 = :images2 where p.id = :id")
    void updateImagesPath(@Param("images") String images, @Param("images1") String images1, @Param("images2") String images2, @Param("id") Integer id);

    @Modifying
    @Query(value = "update Post p set p.title = ?1, p.summary = ?2, p.content = ?3, p.category = ?4, p.location = ?5, p.author = ?6 where p.id = ?7")
    void updatePost(String title, String summary, String content, String category, String location, String author, Integer id);

    @Query(value = "select p from Post p where p.title like :key or p.summary like :key or p.content like :key")
    List<Post> getPostByKeySearch(@Param("key") String key);

    @Query(value = "select p from Post p where p.category = 'location' and p.title like :key or " +
            "p.category = 'location' and p.summary like :key")
    List<Post> getPostLocationByKeySearch(@Param("key") String key);

    @Query(value = "select p from Post p where p.category = 'cuisine' and p.title like :key or " +
            "p.category = 'cuisine' and p.summary like :key")
    List<Post> getPostCuisineByKeySearch(@Param("key") String key);

    @Modifying
    @Query(value = "update Post p set p.view = p.view + 1 where p.id = :id")
    void updateViewPost(@Param("id") Integer idpost);

    Page<Post> findAllByCategoryLike(String category, Pageable pageable);

    @Transactional
    @Modifying
    void deletePostByTitle(String title);
}
