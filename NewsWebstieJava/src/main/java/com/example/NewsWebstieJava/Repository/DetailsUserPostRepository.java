package com.example.NewsWebstieJava.Repository;

import com.example.NewsWebstieJava.Models.DetailsPostUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DetailsUserPostRepository extends JpaRepository<DetailsPostUser, Integer> {
    @Modifying
    @Transactional
    @Query(value = "Update DetailsPostUser p set p.imagesHome = :images, p.imagesDT1 = :images1, p.imagesHome = :images2 where p.id = :id")
    void updateImagesPath(@Param("images") String images, @Param("images1") String images1, @Param("images2") String images2, @Param("id") Integer id);

    @Query(value = "select p from DetailsPostUser p where p.idpostuser = :idpostuser")
    DetailsPostUser findPostByIdUserPost(@Param("idpostuser") Integer id);
}
