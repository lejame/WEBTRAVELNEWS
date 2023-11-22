package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Account;
import com.example.NewsWebstieJava.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AdminController {
    @Autowired
    AccountService accountService;
    @GetMapping("adminpage/{id}")
    public String adminPage(@PathVariable("id") Integer id, Model model){
        Account account = accountService.getAccount(id);
        model.addAttribute("account", account);

        return "adminpage";
    }


}
