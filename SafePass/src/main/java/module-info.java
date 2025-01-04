module org.example.passapplanding {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens org.example.passapplanding to javafx.fxml;
    exports org.example.passapplanding;
}