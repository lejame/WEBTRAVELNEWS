package com.example.NewsWebstieJava.Controllers;

import com.example.NewsWebstieJava.Models.Account;
import com.example.NewsWebstieJava.Models.Location;
import com.example.NewsWebstieJava.Models.Post;
import com.example.NewsWebstieJava.Repository.LocationRepository;
import com.example.NewsWebstieJava.Repository.PostRepository;
import com.example.NewsWebstieJava.Service.AccountService;
import com.example.NewsWebstieJava.Service.AdminPostService;
import com.example.NewsWebstieJava.Service.PostService;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/adminpage/post")
public class AdminPostController {
    @Autowired
    AccountService accountService;
    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    AdminPostService adminPostService;

    @GetMapping("/{id}")
    private String adminPageLocationPost(@PathVariable("id") Integer id, Model model){
        Account account = accountService.getAccount(id);
        List<Post> postList = postService.getAllPost();

        model.addAttribute("postList", postList);
        model.addAttribute("account", account);

        return "postAdmin";

    }

    @GetMapping("/add/{id}")
    private String addPage(@PathVariable("id") Integer id, Model model){

        model.addAttribute("id", id);
        return "add";
    }

    @RequestMapping(value = "/addPost/{id}", method = RequestMethod.POST)
    private RedirectView addPost(@RequestParam("images")MultipartFile images,
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

        postService.updateImagesPath(post.getImages(), post.getImagesDetails1(), post.getImagesDetails2(), post.getId());

        FileUploadUtils.saveFile("src/main/resources/static/images/" + category, post.getId() + "_" + StringUtils.cleanPath(Objects.requireNonNull(images.getOriginalFilename())), images);
        FileUploadUtils.saveFile("src/main/resources/static/images/" + category, post.getId() + "_DT1_" + StringUtils.cleanPath(Objects.requireNonNull(imagesDetails1.getOriginalFilename())), imagesDetails1);
        FileUploadUtils.saveFile("src/main/resources/static/images/" + category, post.getId() + "_DT2_" + StringUtils.cleanPath(Objects.requireNonNull(imagesDetails2.getOriginalFilename())), imagesDetails2);


        redirectAttributes.addAttribute("id", id);
        return  new RedirectView("/adminpage/post/add/{id}");

    }
    @GetMapping("/update/{idpost}/{id}")
    private String updatePage(@PathVariable("idpost") Integer idpost, @PathVariable("id") Integer id, Model model){
        Optional<Post> post = postRepository.findById(idpost);
        List<Location> locationList = locationRepository.findAll();
        for(Location l : locationList){
            if(l.getLocation().equalsIgnoreCase(post.get().getLocation())){
                locationList.remove(l);
                break;
            }
        }
        model.addAttribute("post", post.orElse(null));
        model.addAttribute("id", id);
        model.addAttribute("locationList",locationList);
        return "update";
    }
    @RequestMapping(value = "/updatepost/{idpost}/{id}", method = RequestMethod.POST)
    private RedirectView updatePost(@PathVariable("idpost") Integer idpost, @PathVariable("id") Integer id,
                                    @RequestParam("images") MultipartFile images,
                                    @RequestParam("imageDetails1") MultipartFile imagesDetails1,
                                    @RequestParam("imageDetails2") MultipartFile imagesDetails2,
                                    @RequestParam("title") String title,
                                    @RequestParam("summary") String summary, @RequestParam("content") String content,
                                    @RequestParam("category")String category, @RequestParam("location") String location,
                                    @RequestParam("author") String author, RedirectAttributes redirectAttributes) throws SQLException, IOException {

        //get the images list to update
        MultipartFile [] multipartFileList = {images, imagesDetails1, imagesDetails2};
        String query = "update post set";
        for(int i =0; i<multipartFileList.length; i++){
            if(!multipartFileList[i].isEmpty()){
                if(i==0){
                    query += " post.images_home = '/images/" + category + "/" + idpost + "_" + multipartFileList[i].getOriginalFilename() + "' ";
                    FileUploadUtils.saveFile("src/main/resources/static/images/"+category, idpost + "_" + multipartFileList[i].getOriginalFilename(), multipartFileList[i]);
                }
                if(i==1){
                    query += ", post.images_details1 = '/images/" + category + "/" + idpost + "_DT1_" + multipartFileList[i].getOriginalFilename() + "' ";
                    FileUploadUtils.saveFile("src/main/resources/static/images/"+category, idpost + "_DT1_" + multipartFileList[i].getOriginalFilename(), multipartFileList[i]);
                }
                if(i==2){
                    query += ", post.images_details1 = '/images/" + category + "/" + idpost + "_DT2_" + multipartFileList[i].getOriginalFilename() + "' ";
                    FileUploadUtils.saveFile("src/main/resources/static/images/"+category, idpost + "_DT2_" + multipartFileList[i].getOriginalFilename(), multipartFileList[i]);
                }
            }
        }

        adminPostService.updateImagesPath(idpost, query);
        adminPostService.updatePost(idpost, title, summary, content, category, location, author);
        redirectAttributes.addAttribute("id", id);
        return new RedirectView("/adminpage/post/{id}");
    }

    @RequestMapping(value = "/deletepost/{idpost}/{id}", method = RequestMethod.GET)
    private RedirectView deletePostById(RedirectAttributes redirectAttributes,@PathVariable("idpost") Integer idpost, @PathVariable("id") Integer id){
        try{
            // delete reference
            postRepository.deleteById(idpost);
            redirectAttributes.addAttribute("id", id);
            return new RedirectView("/adminpage/post/{id}");
        }catch (Exception e){
            return new RedirectView("/eroor");
        }
    }

    @GetMapping("/{id}/search/")
    private String searchPost(Model model, @RequestParam("keySearch") String keySearch, @PathVariable("id") Integer id){
        List<Post> postList = adminPostService.getPostByKeySearch("%" + keySearch + "%");
        if(postList!=null){
            Account account = accountService.getAccount(id);
            model.addAttribute("postList", postList);
            model.addAttribute("account", account);
            return "postAdmin";
        }
        return "404";
    }

    @RequestMapping(value = "/{id}/filter/category", method = RequestMethod.GET)
    private String filterByCategory(Model model, @RequestParam("optionCategory") String category, @PathVariable("id") Integer id){

        List<Post> postList = postService.getAllPost().stream().filter(p -> p.getCategory().equalsIgnoreCase(category)).toList();

        if(postList != null){
            Account account = accountService.getAccount(id);
            model.addAttribute("postList", postList);
            model.addAttribute("account", account);
            return "postAdmin";
        }
        return "404";
    }

    @RequestMapping(value = "/{id}/filter/option")
    private String filterByOption(Model model, @RequestParam("optionFilter") String optionFilter, @PathVariable("id") Integer id){
        if(optionFilter.equalsIgnoreCase("viewDESC")){
            List<Post> postList = postService.getAllPost().stream().sorted(Comparator.comparingInt(Post::getView)).toList();
            Account account = accountService.getAccount(id);
            model.addAttribute("postList", postList);
            model.addAttribute("account", account);
            return "postAdmin";
        }else if(optionFilter.equalsIgnoreCase("viewASC")){
            List<Post> postList = postService.getAllPost().stream().sorted(Comparator.comparing(Post::getView).reversed()).toList();
            Account account = accountService.getAccount(id);
            model.addAttribute("postList", postList);
            model.addAttribute("account", account);
            return "postAdmin";
        }else if(optionFilter.equalsIgnoreCase("dateDESC")){
            List<Post> postList = postService.getAllPost().stream().sorted(Comparator.comparing(Post::getDate)).toList();
            Account account = accountService.getAccount(id);
            model.addAttribute("postList", postList);
            model.addAttribute("account", account);
            return "postAdmin";
        }else if(optionFilter.equalsIgnoreCase("dateASC")){
            List<Post> postList = postService.getAllPost().stream().sorted(Comparator.comparing(Post::getDate).reversed()).toList();
            Account account = accountService.getAccount(id);
            model.addAttribute("postList", postList);
            model.addAttribute("account", account);
            return "postAdmin";
        }
        return "404";
    }
}
