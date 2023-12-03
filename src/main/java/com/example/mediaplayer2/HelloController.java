package com.example.mediaplayer2;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.File;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private MediaView mediaview;
    MediaPlayer player;
    @FXML
    private Button plybtn;
    @FXML
    private Slider slider;
    @FXML
    private void startApplication() {
        // Handle the button click action (start your application)
        System.out.println("Starting the application...");
    }
    private void addBounceAndRotateAnimation(Button button) {
        // Bounce animation
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), button);
        translateTransition.setByY(20);
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setAutoReverse(true);

        // Rotate animation
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), button);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(RotateTransition.INDEFINITE);
        rotateTransition.setAutoReverse(true);

        // Play both animations
        translateTransition.play();
        rotateTransition.play();
    }
    @FXML

    // Your other controller code

    // Method to change the color of all buttons
    public void changeButtonColors() {
        // Apply style to the buttons container (VBox in this case)
        plybtn.setStyle("-fx-background-color: #008000;");

        // You can also iterate over each button and set its style individually


    }

    @FXML
    void SelectvedioClicked(ActionEvent event) {
        try {
            // Create a FileChooser for selecting a video file
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.flv", "*.avi"));

            // Show open file dialog
            File selectedFile = fileChooser.showOpenDialog(new Stage());

            if (selectedFile != null) {
                // Convert file URI to string
                String fileUri = selectedFile.toURI().toString();
                if(player!=null)
                {
                    player.dispose();
                }
                // Create a Media object with the selected file URI
                Media media = new Media(fileUri);

                // Create a MediaPlayer with the Media object
                player = new MediaPlayer(media);

                // Use mediaPlayer as needed (e.g., set it to a MediaView or play it)
                mediaview.setMediaPlayer(player);

                player.setOnReady(()-> {
                    slider.setMin(0);
                    slider.setMax(player.getMedia().getDuration().toMinutes());
                    slider.setValue(0);
                });
                player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                    @Override
                    public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                        Duration d = player.getCurrentTime();
                        slider.setValue(d.toMinutes());
                    }
                });
                slider.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        if(slider.isPressed()){ double value=slider.getValue();
                            player.seek(new Duration(value*60*1000));}
                    }
                });
            } else {
                System.out.println("No file selected.");
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur during the process
            e.printStackTrace();
        }
    }
    @FXML
    void exitApplication(ActionEvent event) {
        if (player != null) {
            // Stop the video playback
            player.stop();

            // Release the MediaPlayer resources
            player.dispose();

            // Set player to null to indicate no video is playing
            player = null;
        }
    }
    @FXML
    void previous(ActionEvent event)
    {
        double d=player.getCurrentTime().toSeconds();
        d=d-10;
        player.seek(new Duration(d*1000));
    }
    @FXML
    void forwarded(ActionEvent event)
    {
        double d=player.getCurrentTime().toSeconds();
        d=d+10;
        player.seek(new Duration(d*1000));
    }
    @FXML
    void play(ActionEvent event) {
        player.play();
        MediaPlayer.Status status =player.getStatus();
        if(status== MediaPlayer.Status.PLAYING)
        {
            player.pause();
            plybtn.setText("Play");
        }else
        {
            player.play();
            plybtn.setText("Pause");
        }
    }
}