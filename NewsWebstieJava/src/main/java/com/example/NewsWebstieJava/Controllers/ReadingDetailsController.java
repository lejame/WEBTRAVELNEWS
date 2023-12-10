package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Post;
import com.example.NewsWebstieJava.Repository.PostRepository;
import com.example.NewsWebstieJava.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class ReadingDetailsController {
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @GetMapping("details/{idpost}")
    public String detailsPage(@PathVariable("idpost") Integer idpost, Model model){
        Optional<Post> post = postRepository.findById(idpost);
        postService.updateViewPost(idpost);
        model.addAttribute("post", post.get());
        return "reading_details";
    }
}
