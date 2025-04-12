package org.example.passapplanding;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {


    @FXML
    private TableView<users> table_users;

    @FXML
    private TableColumn<users, Integer> col_id;

    @FXML
    private TableColumn<users, String> col_application;

    @FXML
    private TableColumn<users, String> col_username;

    @FXML
    private TableColumn<users, String> col_password;

    @FXML
    private TableColumn<users, String> col_comment;

    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_application;

    @FXML
    private TextField txt_comment;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField filterField;


    ObservableList<users> listM;
    ObservableList<users> dataList;



    int index = -1;

    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void Add_users (){
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into users (application,username,password,comment)values(?,?,?,? )";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_application.getText());
            pst.setString(2, txt_username.getText());
            String encryptedPassword = EncryptUtils.encrypt(txt_password.getText(), 10);
            //System.out.println("Encrypted Password: " + encryptedPassword);
            pst.setString(3, encryptedPassword);
            pst.setString(4, txt_comment.getText());
            pst.execute();

            JOptionPane.showMessageDialog(null, "Users added successfully");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    //////// methode select users ///////
    @FXML
    void getSelected (MouseEvent event){
        index = table_users.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        txt_id.setText(col_id.getCellData(index).toString());
        txt_application.setText(col_application.getCellData(index).toString());
        txt_username.setText(col_username.getCellData(index).toString());
        txt_password.setText(col_password.getCellData(index).toString());
        txt_comment.setText(col_comment.getCellData(index).toString());

    }

    public void Edit (){
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_id.getText();
            String value2 = txt_application.getText();
            String value3 = txt_username.getText();
            String value4 = txt_password.getText();
            String value5 = txt_comment.getText();
            String sql = "update users set user_id= '"+value1+"',application= '"+value2+"',username= '"+
                    value3+"',password= '"+value4+"',comment= '"+value5+"' where user_id='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void Delete(){
        conn = mysqlconnect.ConnectDb();
        String sql = "delete from users where user_id = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Delete");
            UpdateTable();
            search_user();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }


    public void UpdateTable(){
        col_id.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
        col_application.setCellValueFactory(new PropertyValueFactory<users,String>("application"));
        col_username.setCellValueFactory(new PropertyValueFactory<users,String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<users,String>("password"));
        col_comment.setCellValueFactory(new PropertyValueFactory<users,String>("comment"));

        listM = mysqlconnect.getDatausers();
        table_users.setItems(listM);
    }




    @FXML
    void search_user() {
        col_id.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
        col_application.setCellValueFactory(new PropertyValueFactory<users,String>("application"));
        col_username.setCellValueFactory(new PropertyValueFactory<users,String>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<users,String>("password"));
        col_comment.setCellValueFactory(new PropertyValueFactory<users,String>("comment"));

        dataList = mysqlconnect.getDatausers();
        table_users.setItems(dataList);
        FilteredList<users> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getApplication().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (person.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (person.getComment().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (String.valueOf(person.getPassword()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<users> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table_users.comparatorProperty());
        table_users.setItems(sortedData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable();
        search_user();
        // Code Source in description
    }
}