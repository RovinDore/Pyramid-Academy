module com.example.humanvsgoblingui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.humanvsgoblingui to javafx.fxml;
    exports com.example.humanvsgoblingui;
}