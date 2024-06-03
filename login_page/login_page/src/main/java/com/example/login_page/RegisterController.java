package com.example.login_page;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterController {
    @FXML
    private Button closeButton;
    @FXML
    private Label registrationMessageLabel;
    @FXML
    private PasswordField setPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField securitykeyTextField;

    public void registerButtonOnAction(ActionEvent event) {
        if(setPasswordField.getText().equals(confirmPasswordField.getText())) {
            registerUser();
            registrationMessageLabel.setText("");
        } else {
            confirmPasswordLabel.setText("Password doesn't match");
        }
    }

    public void closeButtonOnAction(ActionEvent event){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void registerUser() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String password = setPasswordField.getText();
        String securityKeyText = securitykeyTextField.getText();


        String insertQuery = "INSERT INTO user_account (firstname, lastname, username, password, `key`) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(insertQuery);
            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, lastname);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, securityKeyText);

            preparedStatement.executeUpdate();
            registrationMessageLabel.setText("User has been registered successfully");

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
