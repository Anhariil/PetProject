import Connectors.Services.Tinkoff.MarketDataService.GetCandles;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

//        GetAssets.main();
        GetCandles.main();
//        Parent root = FXMLLoader.load(getClass().getResource("/UI/Welcome.fxml")); // имя файла - в данном случае типа fxml - который будет открываться при вызове
//        stage.setTitle("Welcome to the new brave World"); // заголовок
//        Scene scene = new Scene(root,700,400);
//        stage.setScene(scene);
//        stage.show(); // запуск окна
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}