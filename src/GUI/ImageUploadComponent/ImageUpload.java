package GUI.ImageUploadComponent;

import GUI.Classes.ServiceFactory;
import GUI.UIController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImageUpload {

    private ArrayList<FXMLLoader> uploadedImages = new ArrayList<>();
    private UIController parentController;

    @FXML
    private VBox fxInject;


    public ImageUpload setParentController(UIController parentController) {
        this.parentController = parentController;
        return this;
    }

    public ImageUpload promptImageUpload() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddImage.fxml"));
        Parent root = loader.load();

        ImageUpload controller = loader.getController();
        controller.setParentController(parentController);

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
            ServiceFactory.imageService.shutdownThreads();
            uploadedImages.clear();
            parentController.setInPrompt(false);
        });


        parentController.setInPrompt(true);

        return this;
    }


    @FXML
    private void imageSelector(ActionEvent aE) throws IOException {
        List<File> selectedFiles = ServiceFactory.imageService.selectImages(aE);

        if (selectedFiles != null) {
            // Handle selected files
            for (File file : selectedFiles) {
                // Process each selected file
                FXMLLoader imageUploadFXML = new FXMLLoader(getClass().getResource("/ImageUploaded.fxml"));
                Parent includedRoot = imageUploadFXML.load();
                UploadedImage controller = imageUploadFXML.getController();
                controller.setView(includedRoot);
                uploadedImages.add(imageUploadFXML);
                fxInject.getChildren().add(includedRoot);
                Platform.runLater(() -> controller.initiateProcessImage(file));
            }
        }
    }


    @FXML
    private void uploadImages(ActionEvent aE) throws IOException {
        if (uploadedImages.isEmpty()) {
            return;
        }

        Iterator<FXMLLoader> iterator = uploadedImages.iterator();
        while (iterator.hasNext()) {
            FXMLLoader data = iterator.next();
            UploadedImage controller = data.getController();
            int index = fxInject.getChildren().indexOf(controller.getView());
            if (index != -1 && controller.uploadImage(index)) {
                fxInject.getChildren().remove(index);
                iterator.remove();
            }
        }

        parentController.updateImageList();
    }
}
