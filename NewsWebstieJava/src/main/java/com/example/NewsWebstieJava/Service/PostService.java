package com.example.NewsWebstieJava.Service;

import com.example.NewsWebstieJava.Models.Post;
import com.example.NewsWebstieJava.Repository.PostRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            return postRepository.findAll();
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

    @Transactional
    public boolean updateViewPost(Integer idpost){
        try{
            postRepository.updateViewPost(idpost);
            return true;
        }catch (Exception e){return false;}
    }

    public Page<Post> getPostPage(Integer pageIndex, String category){
        Pageable pageable = PageRequest.of(pageIndex - 1, 3);
        return postRepository.findAllByCategoryLike(category, pageable);
    }


}
