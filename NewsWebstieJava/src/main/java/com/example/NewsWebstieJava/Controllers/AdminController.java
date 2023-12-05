package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Account;
import com.example.NewsWebstieJava.Repository.AccountRepository;
import com.example.NewsWebstieJava.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @GetMapping("adminpage/{id}")
    public String adminPage(@PathVariable("id") Integer id, Model model){
        Account account = accountService.getAccount(id);
        model.addAttribute("account", account);
        List<Account> accountList = accountRepository.findAll();
        model.addAttribute("accountList", accountList);
        return "adminpage";
    }



}
