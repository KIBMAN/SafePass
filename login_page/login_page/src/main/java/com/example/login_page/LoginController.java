package com.example.login_page;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class LoginController implements Initializable {

    private Instance id_data = Instance.getInstance();

    @FXML
    private PasswordField EnterPasswordField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button createaccountButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField user_name_TextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void validateLogin() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT account_id FROM user_account WHERE username = ? AND password = ?";
        String theKey = null;

        try {
            PreparedStatement preparedStatement = connectDB.prepareStatement(verifyLogin);
            preparedStatement.setString(1, user_name_TextField.getText());
            preparedStatement.setString(2, EnterPasswordField.getText());
            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next()) {
                int accountId = queryResult.getInt("account_id");

                // Fetch the key for the logged-in account
                String the_key_query = "SELECT `key` FROM user_account WHERE account_id = ?";
                PreparedStatement prepared_Statement = connectDB.prepareStatement(the_key_query);
                prepared_Statement.setInt(1, accountId);
                ResultSet query_Result = prepared_Statement.executeQuery();

                if (query_Result.next()) {
                    theKey = query_Result.getString("key");

                    // Load the main form and pass values
                    loadMainForm(accountId, theKey);



//                    System.out.println(accountId);
//                    String application; String password; String cmd;
//                    String ac_id = "SELECT `acc_id` FROM users WHERE account_Id = ?";
//                    PreparedStatement prepared_Statement_ac_id = connectDB.prepareStatement(ac_id);
//                    prepared_Statement_ac_id.setInt(1, accountId);
//                    ResultSet query_Result_ac_id = prepared_Statement_ac_id.executeQuery();
//                    INSERT INTO temp()
                }
            } else {
                loginMessageLabel.setText("Invalid login. Please try again");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connectDB != null) connectDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // accountId variable holds the updated value
    }

    public void loginButtonOnAction(ActionEvent event) {
        if (!user_name_TextField.getText().isBlank() && !EnterPasswordField.getText().isBlank()) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Please enter User Name and Password");
        }
    }

    @FXML
    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void createAccountForm() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register.fxml"));
            Stage registerStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600, 554);
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(scene);
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void loadMainForm(int accountId, String theKey)
    {
        id_data.setId_data(accountId);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root = loader.load();


            FXMLDocumentController controller = loader.getController();
            controller.init(theKey, accountId);

            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createaccountButtonOnAction(ActionEvent event) {
    }
}
