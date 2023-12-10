package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Account;
import com.example.NewsWebstieJava.Repository.PostRepository;
import com.example.NewsWebstieJava.Service.AccountService;
import com.example.NewsWebstieJava.Service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Set;

@Controller
@RequestMapping("/adminpage/statistical")
public class AdminStatisticalController {
    @Autowired
    AccountService accountService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    StatisticalService statisticalService;
    @GetMapping("/{id}")
    private String statisticalPage(Model model, @PathVariable("id") Integer id) throws SQLException {
        Account account = accountService.getAccount(id);
        LinkedHashMap<String, Integer> listView = statisticalService.getViewStatisticalByCategory("all");
        LinkedHashMap<String, Integer> listPostLocation = statisticalService.getPostStatisticalByCategory("location");
        LinkedHashMap<String, Integer> listPostCuisine = statisticalService.getPostStatisticalByCategory("cuisine");
        Set<String> key = listView.keySet();
        System.out.println(listView);
        model.addAttribute("key", key);
        model.addAttribute("listView", listView);
        model.addAttribute("listPostLocation", listPostLocation);
        model.addAttribute("listPostCuisine", listPostCuisine);
        model.addAttribute("account", account);
        return "statisticalAdmin";
    }

    @GetMapping("/{id}/chartBy")
    private String chartByCategory(Model model, @RequestParam("optionChart") String optionChart,
                                   @PathVariable("id") Integer id) throws SQLException {
        Account account = accountService.getAccount(id);
        LinkedHashMap<String, Integer> listView = statisticalService.getViewStatisticalByCategory(optionChart);
        LinkedHashMap<String, Integer> listPostLocation = statisticalService.getPostStatisticalByCategory("location");
        LinkedHashMap<String, Integer> listPostCuisine = statisticalService.getPostStatisticalByCategory("cuisine");
        Set<String> key = listView.keySet();

        model.addAttribute("key", key);
        model.addAttribute("listView", listView);
        model.addAttribute("listPostLocation", listPostLocation);
        model.addAttribute("listPostCuisine", listPostCuisine);
        model.addAttribute("account", account);
        return "statisticalAdmin";
    }
}
