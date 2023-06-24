import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class VideoPlayerController {

    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider timeSlider;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Button player;

    private boolean status = true;

    @FXML
    public void play(ActionEvent event) {
        mediaPlayer = mediaView.getMediaPlayer();
        if (status) {
            player.setText("play");
            mediaPlayer.pause();
        } else {
            player.setText("pause");
            mediaPlayer.play();
        }
        status = !status;
    }

    @FXML
    public void slower(ActionEvent event) {
        mediaPlayer = mediaView.getMediaPlayer();
        mediaPlayer.setRate(0.5);
    }

    @FXML
    public void faster(ActionEvent event) {
        mediaPlayer = mediaView.getMediaPlayer();
        mediaPlayer.setRate(1.5);
    }

    @FXML
    public void normal(ActionEvent event) {
        mediaPlayer = mediaView.getMediaPlayer();
        mediaPlayer.setRate(1);
    }

    @FXML
    public void mousePressed() {
        mediaPlayer = mediaView.getMediaPlayer();
        mediaPlayer.seek(Duration.seconds(timeSlider.getValue()));
    }

}
