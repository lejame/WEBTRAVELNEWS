package com.example.NewsWebstieJava.Repository;

import com.example.NewsWebstieJava.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query(value = "select a from Account a where a.email = :email and a.password = :password")
    public Account getAccount(@Param("email") String email, @Param("password") String password);

    @Query(value = "select a from Account a where a.code = :code")
    public Account getCodeAccount(@Param("code") String code);
}
