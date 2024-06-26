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

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class Image {
    // Bll Layer
    private ArrayList<BLL.Models.Image> ImageArray = new ArrayList<>();

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

    public Image() {
    }

    public void initialize(Pane Img, ScrollPane smallImageScrollContainer, HBox SmallImgContainer, Details detailsComponent){
        this.largeImage = Img;
        this.smallImageScrollContainer = smallImageScrollContainer;
        this.smallImageContainer = SmallImgContainer;
        this.detailsComponent = detailsComponent;

        ImageArray = ServiceFactory.imageService.getAllImages();

        if (ImageArray.isEmpty()) { return; }
        createSmallImages();
        changeImage();
    }

    public void updateList(){
        ImageArray = ServiceFactory.imageService.getAllImages();
        currentIndex = 0;

        smallImageContainer.getChildren().clear();
        smallImageArray.clear();

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
        File Image = new File(this.ImageArray.get(this.currentIndex).getImagePath());
        largeImage.setStyle("-fx-background-image: url('" + Image.toURI() + "');");
        // Set image detials
        detailsComponent.setImageIndex(this.currentIndex);
        detailsComponent.setImageSize(this.ImageArray.get(this.currentIndex).getImageSize());
        detailsComponent.setImageName(this.ImageArray.get(this.currentIndex).getImageName());
        detailsComponent.setImageDescription(this.ImageArray.get(this.currentIndex).getImageDescription());
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


    private void clearImage(){
        largeImage.setStyle("-fx-background-image: none;");
        // Set image detials
        detailsComponent.setImageIndex(0);
        detailsComponent.setImageSize(0);
        detailsComponent.setImageName("NA");
        detailsComponent.setImageDescription("NA");
        detailsComponent.setImageType("NA");
        detailsComponent.setImageDimensions(0, 0);

        detailsComponent.setImageColorR(0);
        detailsComponent.setImageColorG(0);
        detailsComponent.setImageColorB(0);
        detailsComponent.setImageColorMIX(0);
    }


    public void deleteSelectedImage() {
        if(ImageArray.isEmpty()){return;}

        ServiceFactory.imageService.deleteImage(ImageArray.get(currentIndex));
        smallImageContainer.getChildren().remove(smallImageArray.get(currentIndex).getPane());
        smallImageArray.remove(currentIndex);
        ImageArray.remove(currentIndex);


        if (currentIndex == this.ImageArray.size()) {
            Previous();
        }

        if (!ImageArray.isEmpty()){
            changeImage();
        }else{
            clearImage();
        }
    }


    public void nextImage() {
        if (ImageArray.isEmpty()) { return; }
        Next();
        changeImage();
    }


    public void previousImage() {
        if (ImageArray.isEmpty()) { return; }
        Previous();
        changeImage();
    }


    // Cycle Image
    public void cycleImages(boolean togglePlay){
        if (ImageArray.isEmpty()) { return; }
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
