package co.edu.univalle.poe.mvc.controller;

import co.edu.univalle.poe.mvc.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import javafx.stage.Stage;
import javafx.util.Duration;

public class HangmanController {
    @FXML
    private Label welcomeText;

    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        iniciarMusicaFondo();
    }

    @FXML
    private void onAbrirAyuda() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/univalle/poe/mvc/view/help-view.fxml"));
        Stage stage = new Stage();
        stage.getIcons().add(new Image(Objects.requireNonNull(
                HelloApplication.class.getResourceAsStream("images/icon.png")
        )));
        stage.setTitle("Cómo jugar");
        stage.setScene(new Scene(loader.load()));
        stage.setResizable(false);
        stage.show();
    }

    private void iniciarMusicaFondo() {
        URL recurso = getClass().getResource("/co/edu/univalle/poe/mvc/music/backgroundmusic.mp3");
        if (recurso != null) {
            Media media = new Media(recurso.toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(0.4);

            // Fuerza el reinicio desde el segundo 0 cada vez que termina la pista
            mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
            });

            mediaPlayer.play();
        }
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
