package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Account;
import com.example.NewsWebstieJava.Models.UserPost;
import com.example.NewsWebstieJava.Repository.UserPostRepository;
import com.example.NewsWebstieJava.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/adminpage/userpost")
public class AdminUserPostController {
    @Autowired
    UserPostRepository userPostRepository;
    @Autowired
    AccountService accountService;
    @GetMapping("/{id}")
    private String adminUserPostPage(@PathVariable("id") Integer id, Model model){
        List<UserPost> userPostList = userPostRepository.findAll();
        Account account = accountService.getAccount(id);
        model.addAttribute("account", account);
        model.addAttribute("userpostList", userPostList);
        return "userPostAdmin";
    }
}
