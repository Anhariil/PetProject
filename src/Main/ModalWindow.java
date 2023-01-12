package Main;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModalWindow {
    public static void openModalWindow(Scene scene) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setScene(scene);
        window.showAndWait();
    }
}
