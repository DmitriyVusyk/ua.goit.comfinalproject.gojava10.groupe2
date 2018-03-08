package view;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane documentPane;

    private void loadSplashScreen() {
        try {

            NavigationDrawer.isSplashLoaded=true;

            Pane splashPane = FXMLLoader.load(getClass().getResource("/FXMLSplashWindow.fxml"));
            documentPane.getChildren().setAll(splashPane);

            FadeTransition fadeTransitionIn = new FadeTransition(Duration.seconds(3), splashPane);
            fadeTransitionIn.setFromValue(0);
            fadeTransitionIn.setToValue(1);


            FadeTransition fadeTransitionOut = new FadeTransition(Duration.seconds(3), splashPane);
            fadeTransitionOut.setFromValue(1);
            fadeTransitionOut.setToValue(0);


            fadeTransitionIn.play();

            fadeTransitionIn.setOnFinished((e) -> {
                fadeTransitionOut.play();

            });

            fadeTransitionOut.setOnFinished((e) -> {
                try {
                    HBox parentContent = FXMLLoader.load(getClass().getResource("/FXMLMainScreen.fxml"));
                    documentPane.getChildren().setAll(parentContent);


                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!NavigationDrawer.isSplashLoaded)
            loadSplashScreen();

    }
}