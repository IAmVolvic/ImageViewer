package GUI.ImageComponent;

import javafx.scene.layout.Pane;

public class ImagePane {
    private Pane Image = new Pane();

    public ImagePane() {
        this.Image.setMaxWidth(100);
        this.Image.setMaxHeight(71);
        this.Image.setPrefWidth(100);
        this.Image.setPrefHeight(71);
        this.Image.getStyleClass().add("ImageView");
    }

    public ImagePane setBackgroundImage(String ImagePath){
        String path = getClass().getResource(ImagePath).toExternalForm();
        this.Image.setStyle("-fx-background-image: url('" + path + "');");
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
