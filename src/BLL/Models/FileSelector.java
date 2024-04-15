package BLL.Models;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class FileSelector {
    public List<File> promptFilePicker(ActionEvent event){
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Image Selection");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif"));

        return fileChooser.showOpenMultipleDialog(stage);
    }
}
