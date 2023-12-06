package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Account;
import com.example.NewsWebstieJava.Repository.AccountRepository;
import com.example.NewsWebstieJava.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;
    @GetMapping("adminpage/{id}")
    private String adminPage(@PathVariable("id") Integer id, Model model){
        Account account = accountService.getAccount(id);
        model.addAttribute("account", account);
        List<Account> accountList = accountRepository.findAll();
        model.addAttribute("accountList", accountList);
        return "adminpage";
    }

    @GetMapping("deleteAccount/{idacc}/{id}")
    private RedirectView deleteAccount(@PathVariable("idacc") Integer idacc, @PathVariable("id") Integer id,
                                       RedirectAttributes redirectAttributes){
        try{
            accountRepository.deleteById(idacc);
            redirectAttributes.addAttribute("id", id);
            return new RedirectView("/adminpage/{id}");
        }catch (Exception e){
            return new RedirectView("/error");
        }
    }


}
