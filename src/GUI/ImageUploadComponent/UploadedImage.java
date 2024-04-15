package GUI.ImageUploadComponent;

import BLL.Models.Image;
import GUI.Classes.ServiceFactory;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;

import java.io.File;

public class UploadedImage {

    @FXML
    private TextField ImageTitle;
    @FXML
    private TextField ImageDescription;
    @FXML
    private ProgressBar progressBar;

    private Parent view;

    public void setView(Parent view) {
        this.view = view;
    }

    public Parent getView() {
        return view;
    }


    public void updateProgressBar(double newValue){
        this.progressBar.setProgress(newValue);
    }

    public void initiateProcessImage(File image) {
        this.ImageTitle.setPromptText(image.getName());
        ServiceFactory.imageService.processImage(image.getPath(), this);
    }

    public boolean uploadImage(int thisIndex){
        Image thisImage = ServiceFactory.imageService.getProcessedImageByIndex(thisIndex);
        if(thisImage == null){return false;}

        if (!ImageTitle.getText().trim().isEmpty()) {
            thisImage.setImageName(ImageTitle.getText());
        }

        if (!ImageDescription.getText().trim().isEmpty()) {
            thisImage.setImageDescription(ImageDescription.getText());
        }

        if (thisImage.getImageDimensionsX() == 0 || thisImage.getImageDimensionsY() == 0 || this.progressBar.getProgress() != 1.0) { return false; }

        return ServiceFactory.imageService.uploadImage(thisIndex);
    }
}
