import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class FXApp extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/UI/Welcome.fxml")); // имя файла - в данном случае типа fxml - который будет открываться при вызове
        stage.setTitle("Welcome to the new brave World"); // заголовок
        stage.setScene(new Scene(root,700,400));
        stage.show(); // запуск окна

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}