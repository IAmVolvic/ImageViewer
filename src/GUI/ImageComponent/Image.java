package GUI.ImageComponent;

import GUI.Classes.ServiceFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Image {
    // Bll Layer
    private ArrayList<BLL.Models.Image> ImageArray = ServiceFactory.imageService.getAllImages();

    // Component State
    private ArrayList<ImagePane> smallImageArray = new ArrayList<>();
    private int currentIndex = 0;
    private ImagePane currentlyActive;

    // JavaFX Components
    private Pane largeImage;
    private HBox smallImageContainer;


    public void initialize(Pane Img, HBox SmallImgContainer){
        this.largeImage = Img;
        this.smallImageContainer = SmallImgContainer;
        createSmallImages();
        changeImage();
    }

    private void createSmallImages() {
        for(BLL.Models.Image data: ImageArray){
            ImagePane newPane = new ImagePane().setBackgroundImage(data.getImagePath());
            smallImageContainer.getChildren().add(newPane.getPane());
            smallImageArray.add(newPane);
        }
    }


    private void Next(){
        this.currentIndex++;
        if (this.currentIndex >= this.ImageArray.size()) this.currentIndex = 0;
    }


    private void Previous(){
        --this.currentIndex;
        if (this.currentIndex < 0) this.currentIndex = this.ImageArray.size() - 1;
    }

    private void changeImage(){
        if (currentlyActive != null){
            currentlyActive.setIsActive(false);
        }
        String path = getClass().getResource(this.ImageArray.get(this.currentIndex).getImagePath()).toExternalForm();
        smallImageArray.get(this.currentIndex).setIsActive(true);
        currentlyActive = smallImageArray.get(this.currentIndex);
        largeImage.setStyle("-fx-background-image: url('" + path + "');");
    }


    public void nextImage() {
        Next();
        changeImage();
    }

    public void previousImage() {
        Previous();
        changeImage();
    }

}
