package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Account;
import com.example.NewsWebstieJava.Models.Author;
import com.example.NewsWebstieJava.Models.Post;
import com.example.NewsWebstieJava.Service.AccountService;
import com.example.NewsWebstieJava.Service.AuthorService;
import com.example.NewsWebstieJava.Service.PostLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminPostLocationController {
    @Autowired
    AccountService accountService;
    @Autowired
    PostLocationService postLocationService;
    @Autowired
    AuthorService authorService;
    @GetMapping("adminpage/postlocation/{id}")
    public String adminPageLocationPost(@PathVariable("id") Integer id, Model model){
        Account account = accountService.getAccount(id);
        List<Post> postList = postLocationService.getAllPost();

        model.addAttribute("postList", postList);
        model.addAttribute("account", account);

        return "locationAdmin";

    }
}