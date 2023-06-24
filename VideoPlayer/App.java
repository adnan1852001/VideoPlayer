import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App extends Application {
    MediaPlayer mdPlayer;
    MediaView md;
    Media media;

    @Override
    public void start(Stage primaryStage) throws Exception {
        File f = new File("./design.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(f.toURI().toURL());
        Parent fxmlParent = fxmlLoader.load();
        FileChooser file = new FileChooser();
        File choosedFile = file.showOpenDialog(primaryStage);
        media = new Media(choosedFile.toURI().toURL().toExternalForm());
        AnchorPane d = (AnchorPane) (fxmlParent.getChildrenUnmodifiable().get(1));
        md = (MediaView) (d.getChildren().get(0));
        mdPlayer = new MediaPlayer(media);
        md.setMediaPlayer(mdPlayer);

        HBox hbox = (HBox) (fxmlParent.getChildrenUnmodifiable().get(0));
        Slider timeSlider = (Slider) (hbox.getChildren().get(1));
        Slider volumSlider = (Slider) (hbox.getChildren().get(5));
        mdPlayer.currentTimeProperty().addListener((l, oldValue, newValue) -> {
            timeSlider.setMax(mdPlayer.getTotalDuration().toSeconds());
            timeSlider.setValue(newValue.toSeconds());
        });

        volumSlider.setMax(1);
        volumSlider.valueProperty().bindBidirectional(mdPlayer.volumeProperty());

        Scene scene = new Scene(fxmlParent, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
        mdPlayer.play();
        md.fitWidthProperty().bind(d.widthProperty());
        md.fitHeightProperty().bind(d.heightProperty());
    }

}
