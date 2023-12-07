package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Post;
import com.example.NewsWebstieJava.Service.AdminPostService;
import com.example.NewsWebstieJava.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class PostLocationController {
    @Autowired
    PostService postService;
    @Autowired
    AdminPostService adminPostService;
    //method to show all post location
    @GetMapping("locationpage/{location}")
    private String locaitonPageByProvince(@PathVariable("location") String location, Model model){
        List<Post> postList = postService.getAllPostByLocation(location).stream().filter(p -> p.getCategory().equalsIgnoreCase("location")).sorted(Comparator.comparing(Post::getDate).reversed()).toList();
        if(postList == null){
            return "404";
        }else{

            model.addAttribute("postList", postList);
            model.addAttribute("location", location);
        }

        return "homeLocation";
    }

    @GetMapping("location")
    private String locationPage(Model model){
        List<Post> postList = postService.getAllPost().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("location"))
                .sorted(Comparator.comparing(Post::getId).reversed()).toList();

        model.addAttribute("postList", postList);
        model.addAttribute("location", "Toàn quốc");
        return "homeLocation";
    }

    @GetMapping("location/filter")
    private String filterCuisinePage(Model model, @RequestParam("option") String option
            , @RequestParam("province") String province){
        if(option.equalsIgnoreCase("choose")){
            List<Post> postList = postService.getAllPost().stream()
                    .filter(p -> p.getCategory().equalsIgnoreCase("location") && p.getLocation().equalsIgnoreCase(province))
                    .toList();
            model.addAttribute("postList", postList);
            model.addAttribute("location", province);
        }else if(province.equalsIgnoreCase("choose")){
            if(option.equalsIgnoreCase("view")){
                List<Post> postList = postService.getAllPost().stream()
                        .filter(p -> p.getCategory().equalsIgnoreCase("location"))
                        .sorted(Comparator.comparing(Post::getView).reversed())
                        .toList();
                model.addAttribute("postList", postList);
                model.addAttribute("location", "Toàn quốc");
            }else{
                List<Post> postList = postService.getAllPost().stream()
                        .filter(p -> p.getCategory().equalsIgnoreCase("location"))
                        .sorted(Comparator.comparing(Post::getDate).reversed())
                        .toList();
                model.addAttribute("postList", postList);
                model.addAttribute("location", "Toàn quốc");
            }
        }else if(!option.equals("choose") && !province.equals("choose")){
            if(option.equals("date")){
                List<Post> postList = postService.getAllPost().stream()
                        .filter(p -> p.getCategory().equalsIgnoreCase("location") && p.getLocation().equalsIgnoreCase(province))
                        .sorted(Comparator.comparing(Post::getDate).reversed())
                        .toList();
                model.addAttribute("postList", postList);
                model.addAttribute("location", province);
            }else{
                List<Post> postList = postService.getAllPost().stream()
                        .filter(p -> p.getCategory().equalsIgnoreCase("location") && p.getLocation().equalsIgnoreCase(province))
                        .sorted(Comparator.comparing(Post::getView).reversed())
                        .toList();
                model.addAttribute("postList", postList);
                model.addAttribute("location", province);
            }
        }
        return "homeLocation";
    }

    @GetMapping("location/search")
    private String searchLocationPage(Model model, @RequestParam("keySearch") String keySearch){
        List<Post> postList = adminPostService.getPostCategoryByKeySearch("location", "%" + keySearch + "%");

        model.addAttribute("postList", postList);
        model.addAttribute("location", "Toàn quốc");
        return "homeLocation";
    }
}
