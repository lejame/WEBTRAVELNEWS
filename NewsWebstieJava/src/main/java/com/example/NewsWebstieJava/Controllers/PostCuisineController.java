package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Post;
import com.example.NewsWebstieJava.Repository.PostRepository;
import com.example.NewsWebstieJava.Service.AdminPostService;
import com.example.NewsWebstieJava.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class PostCuisineController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostService postService;
    @Autowired
    AdminPostService adminPostService;
    @GetMapping("cuisinepage/{location}")
    private String cuisinePageByProvince(@PathVariable("location") String location, Model model){
        List<Post> postList = postRepository.findAll().stream()
                .filter(p -> p.getCategory().equals("cuisine") && p.getLocation().equals(location))
                .collect(Collectors.toList());

        model.addAttribute("postList", postList);
        model.addAttribute("location", location);

        return "homeCuisine";
    }
    @GetMapping("cuisine")
    public String cuisinePage(Model model, @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex){
//        List<Post> postList = postService.getAllPost().stream().
//                filter(p -> p.getCategory().equalsIgnoreCase("cuisine")).
//                sorted(Comparator.comparing(Post::getId).reversed()).toList();

        Page<Post> postList = postService.getPostPage(pageIndex, "cuisine");

        model.addAttribute("totalPage", postList.getTotalPages());
        model.addAttribute("currentPage", pageIndex);

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

    @GetMapping("cuisine/search")
    private String searchLocationPage(Model model, @RequestParam("keySearch") String keySearch){
        List<Post> postList = adminPostService.getPostCategoryByKeySearch("cuisine", "%" + keySearch + "%");

        model.addAttribute("postList", postList);
        model.addAttribute("location", "Toàn quốc");
        return "homeLocation";
    }

}
