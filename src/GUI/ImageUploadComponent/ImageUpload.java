package GUI.ImageUploadComponent;

import GUI.UIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ImageUpload {

    private ArrayList<FXMLLoader> uploadedImages = new ArrayList<>();
    private UIController parentController;



    @FXML
    private VBox testInject;

    public ImageUpload setParentController(UIController parentController) {
        this.parentController = parentController;
        return this;
    }

    public ImageUpload promptImageUpload() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddImage.fxml"));
        Parent root = loader.load();

        // Set the minimum width and height
        double Width = 900;
        double Height = 500;


        // Set the stage properties
        stage.setScene(new Scene(root, Width, Height));
        stage.setMinWidth(Width);
        stage.setMinHeight(Height);
        stage.setMaxWidth(Width);
        stage.setMaxHeight(Height);


        stage.setTitle("Image Upload");
        stage.show();

        stage.setOnCloseRequest(e -> {
            uploadedImages.clear();
            parentController.setInPrompt(false);
        });


        parentController.setInPrompt(true);

        return this;
    }


    @FXML
    private void imageSelector(ActionEvent aE) throws IOException {
        System.out.println("Select Images");

        FXMLLoader imageUploadFXML = new FXMLLoader(getClass().getResource("/ImageUploaded.fxml"));
        Parent includedRoot = imageUploadFXML.load();
        uploadedImages.add(imageUploadFXML);
        testInject.getChildren().add(includedRoot);
    }


    @FXML
    private void uploadImages(ActionEvent aE) throws IOException {
        for(FXMLLoader data : uploadedImages){
            // Get the controller associated with the FXML file
            UploadedImage controller = data.getController();
            System.out.println(controller.getTitle());
        }
    }
}
