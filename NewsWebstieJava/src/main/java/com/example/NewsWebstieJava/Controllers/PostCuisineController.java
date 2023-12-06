package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Post;
import com.example.NewsWebstieJava.Repository.PostRepository;
import com.example.NewsWebstieJava.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/")
public class PostCuisineController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostService postService;
    @GetMapping("cuisine")
    public String cuisinePage(Model model){
        List<Post> postList = postService.getAllPost().stream().
                filter(p -> p.getCategory().equalsIgnoreCase("cuisine")).
                sorted(Comparator.comparing(Post::getDate).reversed()).toList();

        model.addAttribute("postList", postList);
        model.addAttribute("location", "Toàn quốc");
        return "homeCuisine";
    }

    @GetMapping("cuisine/filter")
    private String filterCuisinePage(Model model, @RequestParam("option") String option
            , @RequestParam("province") String province){
        if(option.equalsIgnoreCase("choose")){
            List<Post> postList = postService.getAllPost().stream()
                    .filter(p -> p.getCategory().equalsIgnoreCase("cuisine") && p.getLocation().equalsIgnoreCase(province))
                    .toList();
            model.addAttribute("postList", postList);
            model.addAttribute("location", province);
        }else if(province.equalsIgnoreCase("choose")){
            if(option.equalsIgnoreCase("view")){
                List<Post> postList = postService.getAllPost().stream()
                        .filter(p -> p.getCategory().equalsIgnoreCase("cuisine"))
                        .sorted(Comparator.comparing(Post::getView).reversed())
                        .toList();
                model.addAttribute("postList", postList);
                model.addAttribute("location", "Toàn quốc");
            }else{
                List<Post> postList = postService.getAllPost().stream()
                        .filter(p -> p.getCategory().equalsIgnoreCase("cuisine"))
                        .sorted(Comparator.comparing(Post::getDate).reversed())
                        .toList();
                model.addAttribute("postList", postList);
                model.addAttribute("location", "Toàn quốc");
            }
        }else if(!option.equals("choose") && !province.equals("choose")){
            if(option.equals("date")){
                List<Post> postList = postService.getAllPost().stream()
                        .filter(p -> p.getCategory().equalsIgnoreCase("cuisine") && p.getLocation().equalsIgnoreCase(province))
                        .sorted(Comparator.comparing(Post::getDate).reversed())
                        .toList();
                model.addAttribute("postList", postList);
                model.addAttribute("location", province);
            }else{
                List<Post> postList = postService.getAllPost().stream()
                        .filter(p -> p.getCategory().equalsIgnoreCase("cuisine") && p.getLocation().equalsIgnoreCase(province))
                        .sorted(Comparator.comparing(Post::getView).reversed())
                        .toList();
                model.addAttribute("postList", postList);
                model.addAttribute("location", province);
            }
        }
        return "homeCuisine";
    }

}
