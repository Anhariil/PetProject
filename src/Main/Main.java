package Main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // over the course of 5 seconds // TODO check and remove
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

//        GetAssets.main();
//        GetCandles.main();
//        Parent root = FXMLLoader.load(getClass().getResource("/Main/UID/UI/Welcome.fxml")); // имя файла - в данном случае типа fxml - который будет открываться при вызове
        Parent root = FXMLLoader.load(getClass().getResource("/Main/UID/UI/Second.fxml"));  // TODO delete after test?
        stage.setTitle("Welcome to the new brave World"); // заголовок
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.show(); // запуск окна

        timeline.play();
    }
}