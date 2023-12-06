package com.example.NewsWebstieJava.Service;

import com.example.NewsWebstieJava.Models.Post;
import com.example.NewsWebstieJava.Repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class AdminPostService {
    @Autowired
    PostRepository postRepository;

    @Transactional
    public boolean updatePost(Integer id, String title, String summary,
                               String content, String category, String location,
                               String author){
        try{
            postRepository.updatePost(title, summary, content, category, location, author, id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateImagesPath(Integer id, String query) throws SQLException {
        String queryFinal = query + " where post.id = '" + id + "'";

        String url = "jdbc:mysql://localhost:3306/Endterm_Java";
        Connection conn = DriverManager.getConnection(url, "root", "");
        Statement st = null;
        try{
            st = conn.createStatement();
            st.execute(queryFinal);
            return true;
        }catch (Exception e){
            return  false;
        }
    }

    public List<Post> getPostByKeySearch(String key){
        try{
            return postRepository.getPostByKeySearch(key);
        }catch (Exception e){
            return null;
        }
    }

    public List<Post> getPostCategoryByKeySearch(String category , String key){
        try{
            if(category.equals("location")){
                return postRepository.getPostLocationByKeySearch(key);
            }else{
                return postRepository.getPostCuisineByKeySearch(key);
            }

        }catch (Exception e){
            return null;
        }
    }
}
