import Connectors.Connectors;
import Connectors.Services.Tinkoff.InstrumentService.GetCountries;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/UI/Welcome.fxml")); // имя файла - в данном случае типа fxml - который будет открываться при вызове
        stage.setTitle("Welcome to the new brave World"); // заголовок
        stage.setScene(new Scene(root,700,400));
        stage.show(); // запуск окна

        Connectors getCountries = new GetCountries("sb","POST");
        getCountries.getConnection();
        System.out.println(getCountries.getResponse());
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}