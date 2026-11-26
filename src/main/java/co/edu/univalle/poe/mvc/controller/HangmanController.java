package co.edu.univalle.poe.mvc.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import javafx.util.Duration;

public class HangmanController {
    @FXML
    private Label welcomeText;

    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        iniciarMusicaFondo();
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
