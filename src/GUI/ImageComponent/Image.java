package GUI.ImageComponent;

import GUI.Classes.ServiceFactory;
import GUI.DetailsComponent.Details;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Objects;

public class Image {
    // Bll Layer
    private final ArrayList<BLL.Models.Image> ImageArray = ServiceFactory.imageService.getAllImages();

    // Component State
    private final double cycleSpeed = 2;
    private final ArrayList<ImagePane> smallImageArray = new ArrayList<>();
    private int currentIndex = 0;
    private ImagePane currentlyActive;

    // JavaFX Components
    private Pane largeImage;
    private ScrollPane smallImageScrollContainer;
    private HBox smallImageContainer;
    private Timeline timeline;

    // UI Components
    private Details detailsComponent;

    public void initialize(Pane Img, ScrollPane smallImageScrollContainer, HBox SmallImgContainer, Details detailsComponent){
        this.largeImage = Img;
        this.smallImageScrollContainer = smallImageScrollContainer;
        this.smallImageContainer = SmallImgContainer;
        this.detailsComponent = detailsComponent;

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
        smallImageArray.get(this.currentIndex).setIsActive(true);
        currentlyActive = smallImageArray.get(this.currentIndex);
        largeImage.setStyle("-fx-background-image: url('" + this.ImageArray.get(this.currentIndex).getImagePath() + "');");

        // Set image detials
        detailsComponent.setImageIndex(this.currentIndex);
        detailsComponent.setImageName(this.ImageArray.get(this.currentIndex).getImageName());
        detailsComponent.setImageSize(this.ImageArray.get(this.currentIndex).getImageSize());
        detailsComponent.setImageType(this.ImageArray.get(this.currentIndex).getImageType());
        detailsComponent.setImageDimensions(this.ImageArray.get(this.currentIndex).getImageDimensionsX(), this.ImageArray.get(this.currentIndex).getImageDimensionsY());

        detailsComponent.setImageColorR(this.ImageArray.get(this.currentIndex).getImageColorR());
        detailsComponent.setImageColorG(this.ImageArray.get(this.currentIndex).getImageColorG());
        detailsComponent.setImageColorB(this.ImageArray.get(this.currentIndex).getImageColorB());
        detailsComponent.setImageColorMIX(this.ImageArray.get(this.currentIndex).getImageColorMIX());

        // Change the H Bar for the scroll pane
        Bounds selectedContentBounds = smallImageArray.get(this.currentIndex).getPane().getBoundsInParent();

        // Calculate the position of the selected content relative to the content width
        double contentWidth = smallImageContainer.getWidth();
        double selectedContentX = selectedContentBounds.getMinX();
        double hValue = selectedContentX / contentWidth;

        smallImageScrollContainer.setHvalue(hValue);
    }


    public void deleteSelectedImage() {
        smallImageContainer.getChildren().remove(smallImageArray.get(currentIndex).getPane());
        smallImageArray.remove(currentIndex);
        ImageArray.remove(currentIndex);

        if (currentIndex == this.ImageArray.size()) {
            Previous();
        }
        changeImage();
    }


    public void nextImage() {
        Next();
        changeImage();
    }


    public void previousImage() {
        Previous();
        changeImage();
    }


    // Cycle Image
    public void cycleImages(boolean togglePlay){
        if (togglePlay){
            timeline = new Timeline(
                    new KeyFrame(Duration.seconds(cycleSpeed), e -> nextImage())
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }else{
            timeline.stop();
            timeline = null;
        }
    }
}
