package com.example.NewsWebstieJava.Service;

import com.example.NewsWebstieJava.Models.Author;
import com.example.NewsWebstieJava.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author getAuthorById(Integer id){
        try{
            return authorRepository.findById(id).get();
        }catch (Exception e){}
        return  null;
    }
}
