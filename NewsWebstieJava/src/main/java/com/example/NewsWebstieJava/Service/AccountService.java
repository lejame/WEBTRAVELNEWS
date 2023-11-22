package com.example.NewsWebstieJava.Service;

import com.example.NewsWebstieJava.Models.Account;
import com.example.NewsWebstieJava.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account getAccount(Integer id){
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null);

    }
}
