package com.example.login_page;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class users {

    int id ;
    String username, password, application , comment;

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String application) {
        this.application = application;
    }

    public void setPassword(String username) {
        this.username = username;
    }

    public void setEmail(String password) {
        this.password = password;
    }

    public void setType(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getApplication() {
        return application;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getComment() {
        return comment;
    }

    public users(int id, String application, String username, String password, String comment) {
        this.id = id;
        this.application = application;
        this.username = username;
        this.password = password;
        this.comment = comment;
    }
}