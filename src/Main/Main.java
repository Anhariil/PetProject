package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

//        GetAssets.main();
//        GetCandles.main();
        Parent root = FXMLLoader.load(getClass().getResource("/Main/UID/UI/Welcome.fxml")); // имя файла - в данном случае типа fxml - который будет открываться при вызове
        stage.setTitle("Welcome to the new brave World"); // заголовок
        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.show(); // запуск окна
    }
}