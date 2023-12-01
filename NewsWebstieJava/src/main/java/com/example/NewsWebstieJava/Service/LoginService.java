package com.example.NewsWebstieJava.Service;

import com.example.NewsWebstieJava.Models.Account;
import com.example.NewsWebstieJava.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    AccountRepository accountRepository;

    public Account getAccount(String email, String password){
        try{
            return accountRepository.getAccount(email, password);
        }catch (Exception e){}
        return null;
    }
}
