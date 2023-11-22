package com.example.NewsWebstieJava.Service;

import com.example.NewsWebstieJava.Models.Post;
import com.example.NewsWebstieJava.Repository.LocationRepository;
import com.example.NewsWebstieJava.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostLocationService {
    @Autowired
    PostRepository postRepository;
    public List<Post> getAllPostLocation(String location){
        List<Post> postList;
        List<Post> postLocation = new ArrayList<>();
        try{
            postList = postRepository.findAll();

            for(Post p : postList){

                if(p.getLocation().equalsIgnoreCase(location)){postLocation.add(p);}
            }
            return postLocation;
        }catch (Exception e){}
        return null;
    }
}
