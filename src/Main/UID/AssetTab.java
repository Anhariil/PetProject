package Main.UID;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;

public class AssetTab extends Tab {
    
    @FXML
    private ChoiceBox<String> choiceBoxInterval;

    public AssetTab(String name, Node node) {
        super(name, node); // default constructor
    }
}
