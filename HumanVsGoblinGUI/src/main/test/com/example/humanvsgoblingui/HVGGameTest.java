package com.example.humanvsgoblingui;

//import org.junit.Test;
import com.sun.tools.javac.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import org.junit.After;
import org.junit.Before;
import org.testfx.api.FxToolkit;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit.ApplicationTest;
import org.junit.jupiter.api.Test;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


class HVGGameTest extends ApplicationTest  {
    private Button button;

    @Before
    void before() throws Exception{
        ApplicationTest.launch(HelloApplication.class);
    }

    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HVG.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        HVGGame GameControl = fxmlLoader.getController();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @After
    void after() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
}