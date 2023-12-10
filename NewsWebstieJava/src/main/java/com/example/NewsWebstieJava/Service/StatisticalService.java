package com.example.NewsWebstieJava.Service;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedHashMap;

@Service
public class StatisticalService {

    public LinkedHashMap<String, Integer> getViewStatisticalByCategory(String category) throws SQLException {
        LinkedHashMap<String, Integer> listResults = new LinkedHashMap<String, Integer>();

        String url = "jdbc:mysql://localhost:3306/Endterm_Java";
        Connection conn = DriverManager.getConnection(url, "root", "");
        String querySelect = "";
        if(category.equals("all")){
            querySelect = "SELECT (location), count(view) as 'TotalView' FROM `post` GROUP BY location";
        }else{
            querySelect = "SELECT (location), count(view) as 'TotalView' FROM `post` WHERE category = ? GROUP BY location";
        }


        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(querySelect);

            if(!category.equals("all")){
                st.setString(1, category);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                listResults.put(rs.getString("location"), rs.getInt("TotalView"));
            }
            return  listResults;
        }catch (Exception e){
            return null;
        }

    }

    public LinkedHashMap<String, Integer> getPostStatisticalByCategory(String category) throws SQLException {
        LinkedHashMap<String, Integer> listResults = new LinkedHashMap<String, Integer>();

        String url = "jdbc:mysql://localhost:3306/Endterm_Java";
        Connection conn = DriverManager.getConnection(url, "root", "");
        String querySelect = "SELECT (location), count(id) as 'TotalPost' FROM `post` WHERE category = ? GROUP BY location";

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(querySelect);

            st.setString(1, category    );
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                listResults.put(rs.getString("location"), rs.getInt("TotalPost"));
            }
            return  listResults;
        }catch (Exception e){
            return null;
        }

    }
}
