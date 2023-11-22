package com.example.NewsWebstieJava.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ReadingDetailsController {

    @GetMapping("details/{category}/{id}")
    public String detailsPage(@PathVariable("category") String category, @PathVariable("id") Integer id, Model model){

        return "reading_details";
    }
}
