package GUI.ImageUploadComponent;

import GUI.Classes.ServiceFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

public class UploadedImage {

    @FXML
    private TextField ImageTitle;
    @FXML
    private TextField ImageDescription;
    @FXML
    private ProgressBar progressBar;

    public String getTitle(){
        return this.ImageTitle.getText();
    }

    public void updateProgressBar(double newValue){
        System.out.println(newValue);
        this.progressBar.setProgress(newValue);
    }

    public void initiateUpload() {
        ServiceFactory.imageService.uploadImage("resources/test/A.jpg", this);
    }
}
