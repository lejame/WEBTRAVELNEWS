package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.*;
import com.example.NewsWebstieJava.Repository.DetailsUserPostRepository;
import com.example.NewsWebstieJava.Repository.LocationRepository;
import com.example.NewsWebstieJava.Repository.UserPostRepository;
import com.example.NewsWebstieJava.Service.AccountService;
import com.example.NewsWebstieJava.Service.DetailsUserPostService;
import com.example.NewsWebstieJava.Utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    AccountService accountService;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    DetailsUserPostRepository detailsUserPostRepository;
    @Autowired
    UserPostRepository userPostRepository;
    @Autowired
    DetailsUserPostService detailsUserPostService;
    @GetMapping("homeuser/{id}")
    private String userPage(Model model, @PathVariable("id") Integer id){
        List<Location> locationList = locationRepository.findAll();
        model.addAttribute("account", accountService.getAccount(id));
        model.addAttribute("locationlist", locationList);
        return "homeUser";
    }

    @GetMapping("post/{id}")
    private String postPage(Model model, @PathVariable("id") Integer id){
        model.addAttribute("id", id);
        return "postUser";
    }

    @RequestMapping(value = "postSuccess/{id}", method = RequestMethod.POST)
    private RedirectView addPost(@PathVariable("id") Integer id , @RequestParam("title") String title,
                                 @RequestParam("content") String content, @RequestParam("summary") String summary,
                                 @RequestParam("location") String location, @RequestParam("category") String category,
                                 @RequestParam("imageHome")MultipartFile images, @RequestParam("imageDetails1") MultipartFile imagesDT1,
                                 @RequestParam("imageDetails2") MultipartFile imagesDT2, RedirectAttributes redirectAttributes) throws ParseException, IOException {

        String imagesPath = StringUtils.cleanPath(Objects.requireNonNull(images.getOriginalFilename()));
        String imagesDt1Path = StringUtils.cleanPath(Objects.requireNonNull(imagesDT1.getOriginalFilename()));
        String imagesDt2Path = StringUtils.cleanPath(Objects.requireNonNull(imagesDT2.getOriginalFilename()));



        // Convert date in java to date in sql
        DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String s = dtf.format(LocalDate.now()).toString();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        java.util.Date d = sdf.parse(s);


        Date date = new Date(d.getTime());
        // end convert
        Account user = accountService.getAccount(id);

        UserPost userPost = userPostRepository.save(new UserPost(id, user.getEmail(), user.getFullname(), 0));

        DetailsPostUser detailsPostUser = detailsUserPostRepository.save(new DetailsPostUser(userPost.getId(), title, summary, content, date, category, location, imagesPath, imagesDt1Path, imagesDt2Path));

        //Set new path of images is follow id
        imagesPath = "/images/user/" + category +"/" + detailsPostUser.getId() + "_" + imagesPath;
        imagesDt1Path = "/images/user/" + category + "/" + detailsPostUser.getId() + "_DT1_" + imagesDt1Path;
        imagesDt2Path = "/images/user/" + category + "/" + detailsPostUser.getId() + "_DT2_" + imagesDt2Path;

        detailsPostUser.setImagesHome(imagesPath);
        detailsPostUser.setImagesDT1(imagesDt1Path);
        detailsPostUser.setImagesDT2(imagesDt2Path);

        detailsUserPostService.updateImagesPath(detailsPostUser.getImagesHome(), detailsPostUser.getImagesDT1(), detailsPostUser.getImagesDT2(), detailsPostUser.getId());

        FileUploadUtils.saveFile("src/main/resources/static/images/user/" + category, detailsPostUser.getId() + "_" + StringUtils.cleanPath(Objects.requireNonNull(images.getOriginalFilename())), images);
        FileUploadUtils.saveFile("src/main/resources/static/images/user/" + category, detailsPostUser.getId() + "_DT1_" + StringUtils.cleanPath(Objects.requireNonNull(imagesDT1.getOriginalFilename())), imagesDT1);
        FileUploadUtils.saveFile("src/main/resources/static/images/user/" + category, detailsPostUser.getId() + "_DT2_" + StringUtils.cleanPath(Objects.requireNonNull(imagesDT2.getOriginalFilename())), imagesDT2);


        redirectAttributes.addAttribute("id", id);
        return  new RedirectView("/homeuser/{id}");
    }
}
