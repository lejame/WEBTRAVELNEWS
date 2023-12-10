package com.example.NewsWebstieJava.Service;

import com.example.NewsWebstieJava.Repository.DetailsUserPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailsUserPostService {
    @Autowired
    DetailsUserPostRepository detailsUserPostRepository;
    public boolean updateImagesPath(String images, String images1, String images2, Integer id){
        try{
            detailsUserPostRepository.updateImagesPath(images, images1, images2, id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
