package com.example.NewsWebstieJava.Service;

import com.example.NewsWebstieJava.Models.Post;
import com.example.NewsWebstieJava.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    public List<Post> getAllPostByLocation(String location){
        List<Post> postList;
        List<Post> postLocation = new ArrayList<>();
        try{
            postList = postRepository.findAll();

            for(Post p : postList){

                if(p.getLocation().equalsIgnoreCase(location)){postLocation.add(p);}
            }
            return postLocation;
        }catch (Exception e){return null;}

    }

    public List<Post> getAllPost(){
        try{
            List<Post> postList = postRepository.findAll();
            return postList;
        }catch (Exception e){return null;}

    }

    public boolean updateImagesPath(String images, String images1, String images2, Integer id){
        try{
            postRepository.updateImagesPath(images, images1, images2, id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
