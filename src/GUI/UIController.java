package GUI;

import BLL.Services.ImageService;
import DAL.DALService;
import GUI.Classes.ServiceFactory;
import GUI.DetailsComponent.Details;
import GUI.ImageComponent.Image;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class UIController {

//    Image Stats
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

//    Image Containers
    public Pane largeImage;
    public HBox smallImageContainer;

//    Buttons
    @FXML
    private Button addImageButton;
    @FXML
    private Button playButton;

    // Components
    private Image imageComponent = new Image();
    private Details detailsComponent = new Details();

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
                colorLabelB
        );
        imageComponent.initialize(largeImage, smallImageContainer, detailsComponent);
    }


    @FXML
    private void nextButton(ActionEvent aE) throws IOException {
        imageComponent.nextImage();
    }

    @FXML
    private void previousButton(ActionEvent aE) throws IOException {
        imageComponent.previousImage();
    }

    @FXML
    private void debug(ActionEvent aE) throws IOException {
        imageComponent.deleteSelectedImage();
    }

}