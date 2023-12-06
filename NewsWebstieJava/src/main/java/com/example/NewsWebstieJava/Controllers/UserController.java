package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Location;
import com.example.NewsWebstieJava.Repository.LocationRepository;
import com.example.NewsWebstieJava.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    AccountService accountService;
    @Autowired
    LocationRepository locationRepository;
    @GetMapping("homeuser/{id}")
    private String userPage(Model model, @PathVariable("id") Integer id){
        List<Location> locationList = locationRepository.findAll();
        model.addAttribute("account", accountService.getAccount(id));
        model.addAttribute("locationlist", locationList);
        return "homeUser";
    }

    @GetMapping("post/{id}")
    private String postPage(Model model, @PathVariable("id") Integer id){
        return "postUser";
    }
}
