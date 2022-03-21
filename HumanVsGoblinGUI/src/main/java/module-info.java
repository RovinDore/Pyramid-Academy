module com.example.humanvsgoblingui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.example.humanvsgoblingui to javafx.fxml;
    exports com.example.humanvsgoblingui;
}