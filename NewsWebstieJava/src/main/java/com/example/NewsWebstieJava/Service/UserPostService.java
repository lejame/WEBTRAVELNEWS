package com.example.NewsWebstieJava.Service;

import com.example.NewsWebstieJava.Repository.UserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPostService {

    @Autowired
    UserPostRepository userPostRepository;
    public boolean updateStatusPostUser(Integer idpost, Integer status){
        try{
            userPostRepository.updateStatusPostUser(idpost, status);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
