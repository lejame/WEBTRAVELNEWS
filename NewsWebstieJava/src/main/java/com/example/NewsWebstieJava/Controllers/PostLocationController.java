package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Post;
import com.example.NewsWebstieJava.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class PostLocationController {
    @Autowired
    PostService postLocationService;

    //method to show all post location
    @GetMapping("locationpage/{location}")
    public String locaitonPage(@PathVariable("location") String location, Model model){
        List<Post> postList = postLocationService.getAllPostByLocation(location);
        if(postList == null){
            return "404";
        }else{

            model.addAttribute("postList", postList);
            model.addAttribute("location", location);
        }

        return "homeLocation";
    }
}
