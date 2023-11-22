package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    LocationRepository locationRepository;
    @GetMapping("home")
    public String home(Model model){
        List<com.example.NewsWebstieJava.Models.Location> locationList = locationRepository.findAll();
        model.addAttribute("locationlist", locationList);
        return "home";
    }

//    @GetMapping("details")
//    public String detailsPost(){
//        return "reading_details";
//    }

}
