package com.example.NewsWebstieJava.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CuisineController {
    @GetMapping("cuisine")
    public String cuisinePage(Model model){
        return "cuisine";
    }

}
