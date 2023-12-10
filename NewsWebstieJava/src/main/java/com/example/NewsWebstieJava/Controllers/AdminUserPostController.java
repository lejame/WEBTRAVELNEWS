package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Account;
import com.example.NewsWebstieJava.Models.DetailsPostUser;
import com.example.NewsWebstieJava.Models.Post;
import com.example.NewsWebstieJava.Models.UserPost;
import com.example.NewsWebstieJava.Repository.DetailsUserPostRepository;
import com.example.NewsWebstieJava.Repository.PostRepository;
import com.example.NewsWebstieJava.Repository.UserPostRepository;
import com.example.NewsWebstieJava.Service.AccountService;
import com.example.NewsWebstieJava.Service.DetailsUserPostService;
import com.example.NewsWebstieJava.Service.UserPostService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/adminpage/userpost")
public class AdminUserPostController {
    @Autowired
    UserPostRepository userPostRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    DetailsUserPostService detailsUserPostService;
    @Autowired
    DetailsUserPostRepository detailsUserPostRepository;
    @Autowired
    UserPostService userPostService;
    @Autowired
    PostRepository postRepository;
    @GetMapping("/{id}")
    private String adminUserPostPage(@PathVariable("id") Integer id, Model model){
        List<UserPost> userPostList = userPostRepository.findAll();
        Account account = accountService.getAccount(id);
        model.addAttribute("account", account);
        model.addAttribute("userpostList", userPostList);
        return "userPostAdmin";
    }

    @GetMapping("/acceptance/{idpost}/{id}")
    private RedirectView acceptPostUser(@PathVariable("idpost") Integer idpost, @PathVariable("id") Integer id,
                                        RedirectAttributes redirectAttributes){
        Optional<UserPost> userPost = userPostRepository.findById(idpost);
        DetailsPostUser detailsPostUser = detailsUserPostRepository.findPostByIdUserPost(idpost);
        userPostService.updateStatusPostUser(idpost, 1);
        postRepository.save(new Post(detailsPostUser.getCategory(),
                detailsPostUser.getLocation(), detailsPostUser.getTitle(),
                detailsPostUser.getContent(), detailsPostUser.getSummary(),
                detailsPostUser.getImagesHome(), detailsPostUser.getImagesDT1(),
                detailsPostUser.getImagesDT2(), detailsPostUser.getDate(),
                0, userPost.get().getNameuser()));

        redirectAttributes.addAttribute("id", id);
        return new RedirectView("/adminpage/userpost/{id}");
    }

    @GetMapping("/cancle/{idpost}/{id}")
    private RedirectView canclePostUser(@PathVariable("idpost") Integer idpost, @PathVariable("id") Integer id,
                                        RedirectAttributes redirectAttributes){
        Optional<UserPost> userPost = userPostRepository.findById(idpost);

        DetailsPostUser detailsPostUser = detailsUserPostRepository.findPostByIdUserPost(idpost);

        userPostService.updateStatusPostUser(idpost, -1);
        try{
            postRepository.deletePostByTitle(detailsPostUser.getTitle());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        // xoa bai viet trong post
//        postRepository.save(new Post(detailsPostUser.getCategory(),
//                detailsPostUser.getLocation(), detailsPostUser.getTitle(),
//                detailsPostUser.getContent(), detailsPostUser.getSummary(),
//                detailsPostUser.getImagesHome(), detailsPostUser.getImagesDT1(),
//                detailsPostUser.getImagesDT2(), detailsPostUser.getDate(),
//                0, userPost.get().getNameuser()));

        redirectAttributes.addAttribute("id", id);
        return new RedirectView("/adminpage/userpost/{id}");
    }

    @GetMapping("/detailsView/{idpost}")
    public String detailsPage(@PathVariable("idpost") Integer idpost, Model model){
        DetailsPostUser detailsPostUser = detailsUserPostRepository.findPostByIdUserPost(idpost);

        model.addAttribute("post", detailsPostUser);
        return "detailsView";
    }
}
