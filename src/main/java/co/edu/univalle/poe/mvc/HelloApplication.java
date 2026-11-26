package co.edu.univalle.poe.mvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;
import java.util.Objects;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 480);
        stage.getIcons().add(new Image(Objects.requireNonNull(
                HelloApplication.class.getResourceAsStream("images/icon.png")
        )));
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
