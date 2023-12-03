package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Account;
import com.example.NewsWebstieJava.Models.Post;
import com.example.NewsWebstieJava.Repository.PostRepository;
import com.example.NewsWebstieJava.Service.AccountService;
import com.example.NewsWebstieJava.Service.PostLocationService;
import com.example.NewsWebstieJava.Utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;


@Controller
@RequestMapping("/adminpage/postlocation")
public class AdminPostLocationController {
    @Autowired
    AccountService accountService;
    @Autowired
    PostLocationService postLocationService;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/{id}")
    public String adminPageLocationPost(@PathVariable("id") Integer id, Model model){
        Account account = accountService.getAccount(id);
        List<Post> postList = postLocationService.getAllPost();

        model.addAttribute("postList", postList);
        model.addAttribute("account", account);

        return "locationAdmin";

    }

    @GetMapping("/add/{id}")
    public String addPage(@PathVariable("id") Integer id, Model model){

        model.addAttribute("id", id);
        return "add";
    }

    @RequestMapping(value = "/addPost/{id}", method = RequestMethod.POST)
    public RedirectView addPost(@RequestParam("images")MultipartFile images,
                                @RequestParam("imageDetails1") MultipartFile imagesDetails1,
                                @RequestParam("imageDetails2") MultipartFile imagesDetails2,
                                @RequestParam("title") String title,
                                @RequestParam("summary") String summary, @RequestParam("content") String content,
                                @RequestParam("category")String category, @RequestParam("location") String location,
                                @RequestParam("author") String author, @PathVariable("id") Integer id,
                                RedirectAttributes redirectAttributes) throws ParseException, IOException {

        String imagesPath = StringUtils.cleanPath(Objects.requireNonNull(images.getOriginalFilename()));
        String imagesDt1Path = StringUtils.cleanPath(Objects.requireNonNull(imagesDetails1.getOriginalFilename()));
        String imagesDt2Path = StringUtils.cleanPath(Objects.requireNonNull(imagesDetails2.getOriginalFilename()));



        // Convert date in java to date in sql
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String s = dtf.format(LocalDate.now()).toString();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        java.util.Date d = sdf.parse(s);


        Date date = new Date(d.getTime());
        // end convert
        Post post = postRepository.save(new Post(category, location, title, content, summary, imagesPath, imagesDt1Path, imagesDt2Path, date, 0, author));

        //Set new path of images is follow id
        imagesPath = "/images/" + category +"/" + post.getId() + "_" + imagesPath;
        imagesDt1Path = "/images/" + category + "/" + post.getId() + "_DT1_" + imagesDt1Path;
        imagesDt2Path = "/images/" + category + "/" + post.getId() + "_DT2_" + imagesDt2Path;

        post.setImages(imagesPath);
        post.setImagesDetails1(imagesDt1Path);
        post.setImagesDetails2(imagesDt2Path);
        System.out.println(postLocationService.updateImagesPath(post.getImages(), post.getImagesDetails1(), post.getImagesDetails2(), post.getId()) + "id: " + post.getId());

        FileUploadUtils.saveFile("src/main/resources/static/images/" + category, post.getId() + "_" + StringUtils.cleanPath(Objects.requireNonNull(images.getOriginalFilename())), images);
        FileUploadUtils.saveFile("src/main/resources/static/images/" + category, post.getId() + "_DT1_" + StringUtils.cleanPath(Objects.requireNonNull(imagesDetails1.getOriginalFilename())), imagesDetails1);
        FileUploadUtils.saveFile("src/main/resources/static/images/" + category, post.getId() + "_DT2_" + StringUtils.cleanPath(Objects.requireNonNull(imagesDetails2.getOriginalFilename())), imagesDetails2);


        redirectAttributes.addAttribute("id", id);
        return  new RedirectView("/adminpage/postlocation/add/{id}");

    }
}
