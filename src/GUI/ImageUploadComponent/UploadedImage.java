package GUI.ImageUploadComponent;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UploadedImage {

    @FXML
    private TextField ImageTitle;
    @FXML
    private TextField ImageDescription;


    public String getTitle(){
        return this.ImageTitle.getText();
    }
}
