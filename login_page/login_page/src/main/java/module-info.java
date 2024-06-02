module login_page{

    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;

    opens com.example.login_page;
    exports com.example.login_page;
}