package GUI;

import BLL.Services.ImageService;
import DAL.DALService;
import GUI.Classes.ServiceFactory;
import GUI.DetailsComponent.Details;
import GUI.ImageComponent.Image;
import GUI.ImageUploadComponent.ImageUpload;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class UIController {

    // Image Stats
    @FXML
    private Label imageIndex;
    @FXML
    private Label imageName;
    @FXML
    private Label imageDescription;
    @FXML
    private Label sizeLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label dimensionLabel;
    @FXML
    private Label colorLabelR;
    @FXML
    private Label colorLabelG;
    @FXML
    private Label colorLabelB;
    @FXML
    private Label colorLabelMIX;


    // Image Containers
    public Pane largeImage;
    public HBox smallImageContainer;
    @FXML
    private ScrollPane smallImageScrollContainer;

    // Buttons Labels
    @FXML
    private Label playButtonLabel;


    // Components
    private Image imageComponent = new Image();
    private Details detailsComponent = new Details();


    // States
    private boolean isInPrompt = false;


    public void initialize() {
        detailsComponent.initialize(
                imageIndex,
                imageName,
                imageDescription,
                sizeLabel,
                typeLabel,
                dimensionLabel,
                colorLabelR,
                colorLabelG,
                colorLabelB,
                colorLabelMIX
        );
        imageComponent.initialize(largeImage, smallImageScrollContainer, smallImageContainer, detailsComponent);
    }


    public boolean isInPrompt() {
        return isInPrompt;
    }


    public void setInPrompt(boolean inPrompt) {
        isInPrompt = inPrompt;
    }


    @FXML
    private void nextButton(ActionEvent aE) throws IOException {
        imageComponent.nextImage();
        togglePlayCycleImages(false);
    }


    @FXML
    private void previousButton(ActionEvent aE) throws IOException {
        imageComponent.previousImage();
        togglePlayCycleImages(false);
    }


    @FXML
    private void addImage(ActionEvent aE) throws IOException {
        if ( isInPrompt() ) { return; }
        new ImageUpload().setParentController(this).promptImageUpload();
    }


    @FXML
    private void deleteImage(ActionEvent aE) throws IOException {
        // Delete Image
    }


    @FXML
    private void playButton(ActionEvent aE) throws IOException {
        togglePlayCycleImages(!ServiceFactory.imageService.isPlaying());
    }


    private void togglePlayCycleImages(boolean changePlaying){
        if( changePlaying ) {
            if (ServiceFactory.imageService.isPlaying()) { return; }

            // Turn on
            ServiceFactory.imageService.setPlaying(true);
            playButtonLabel.setText("Stop");
            imageComponent.cycleImages(true);
        }else{
            if (!ServiceFactory.imageService.isPlaying()) { return; }

            // Turn off
            ServiceFactory.imageService.setPlaying(false);
            playButtonLabel.setText("Play");
            imageComponent.cycleImages(false);
        }
    }


    public void updateImageList(){
        imageComponent.updateList();
    }
}