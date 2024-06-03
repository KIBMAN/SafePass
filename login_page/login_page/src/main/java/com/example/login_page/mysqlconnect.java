package com.example.login_page;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author amir
 */
public class mysqlconnect {

    Connection conn = null;
    public static Connection ConnectDb(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","IUT2345678");
            // JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            return null;
        }

    }
    /// This is the updated part
//    public static ObservableList<users> getDatausers_selected(){
//        Connection conn = ConnectDb();
//        ObservableList<users> list = FXCollections.observableArrayList();
//        try {
//            PreparedStatement ps = conn.prepareStatement("select * from users");
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()){
//                list.add(new users(Integer.parseInt(rs.getString("user_id")), rs.getString("application"), rs.getString("username"), rs.getString("password"), rs.getString("comment")));
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
    /// This is the updated part
    public static ObservableList<users> getDatausers(){
        Connection conn = ConnectDb();
        ObservableList<users> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new users(Integer.parseInt(rs.getString("user_id")), rs.getString("application"), rs.getString("username"), rs.getString("password"), rs.getString("comment")));
            }
        } catch (Exception e) {
        }
        return list;
    }

}

