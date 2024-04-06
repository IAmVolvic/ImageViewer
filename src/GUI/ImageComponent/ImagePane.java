package GUI.ImageComponent;

import javafx.scene.layout.Pane;

public class ImagePane {
    private Pane Image = new Pane();

    public ImagePane() {
        this.Image.setMinWidth(100);
        this.Image.setMinHeight(71);
        this.Image.setPrefWidth(100);
        this.Image.setPrefHeight(71);
        this.Image.getStyleClass().add("ImageView");
    }

    public ImagePane setBackgroundImage(String ImagePath){
        this.Image.setStyle("-fx-background-image: url('" + ImagePath + "');");
        return this;
    }

    public Pane getPane(){
        return this.Image;
    }

    public void setIsActive(Boolean toggle){
        if (toggle){
            this.Image.getStyleClass().add("selectedImage");
        }else{
            this.Image.getStyleClass().remove("selectedImage");
        }
    }
}
