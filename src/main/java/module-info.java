module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires java.security.jgss;
    requires org.xerial.sqlitejdbc;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.model;
    exports com.example.demo.controller;
    opens com.example.demo.model to javafx.fxml;
    opens com.example.demo.controller to javafx.fxml;

}