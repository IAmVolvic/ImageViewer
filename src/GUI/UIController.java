package GUI;

import DAL.DALService;
import GUI.Classes.ServiceFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

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
    private Label colorLabel;

//    Image Containers
    @FXML
    public Pane largeImage;
    @FXML
    public HBox smallImageContainer;

//    Buttons
    @FXML
    private Button previousButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button addImageButton;
    @FXML
    private Button playButton;


    public void initialize() {
        System.out.println(ServiceFactory.imageService.getImageById(1));
    }

}
