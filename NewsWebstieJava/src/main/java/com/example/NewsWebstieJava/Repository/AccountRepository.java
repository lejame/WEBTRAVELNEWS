package com.example.NewsWebstieJava.Repository;

import com.example.NewsWebstieJava.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
