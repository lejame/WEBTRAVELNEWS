package com.example.NewsWebstieJava.Repository;

import com.example.NewsWebstieJava.Models.DetailsPostUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsUserPostRepository extends JpaRepository<DetailsPostUser, Integer> {
}
