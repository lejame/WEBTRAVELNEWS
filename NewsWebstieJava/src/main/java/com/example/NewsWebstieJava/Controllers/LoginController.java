package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Account;
import com.example.NewsWebstieJava.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;

@Controller
@RequestMapping("/")
public class LoginController {
    @Autowired
    LoginService loginService;
    @GetMapping("login")
    public String loginPage(){
        return "login";
    }
    @PostMapping("login/account")
    public RedirectView authenticationAccount(@RequestParam("email") String email,
                                              @RequestParam("password") String password,
                                              RedirectAttributes redirectAttributes){
        Account account = loginService.getAccount(email, password);
        if(account == null){
            return new RedirectView("/error");
        }
        else if(Objects.equals(account.getRole(), "admin")){
            redirectAttributes.addAttribute("id", account.getId());
            return new RedirectView("/adminpage/{id}");
        }else if (Objects.equals(account.getRole(), "user") && account.getEnable() == 1){
            redirectAttributes.addAttribute("id", account.getId());
            return new RedirectView("/home");
        }
        return new RedirectView("/login");
    }

    @GetMapping("error")
    public String errorPage(){
        return "404";
    }
}
