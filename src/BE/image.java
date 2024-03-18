package BE;

public class image {
    private double imageSize;
    private String imageType;
    private double imageDimensionsX;
    private double imageDimensionsY;
    private double imageColorR;
    private double imageColorG;
    private double imageColorB;
    private String imagePath;


    public void setImageSize(double imageSize) {
        this.imageSize = imageSize;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public void setImageDimensionsX(double imageDimensionsX) {
        this.imageDimensionsX = imageDimensionsX;
    }

    public void setImageDimensionsY(double imageDimensionsY) {
        this.imageDimensionsY = imageDimensionsY;
    }

    public void setImageColorR(double imageColorR) {
        this.imageColorR = imageColorR;
    }

    public void setImageColorG(double imageColorG) {
        this.imageColorG = imageColorG;
    }

    public void setImageColorB(double imageColorB) {
        this.imageColorB = imageColorB;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


//    -----------------------


    public double getImageSize() {
        return this.imageSize;
    }

    public String getImageType() {
        return this.imageType;
    }

    public double getImageDimensionsX() {
        return this.imageDimensionsX;
    }

    public double getImageDimensionsY() {
        return imageDimensionsY;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public double getImageColorR() {
        return this.imageColorR;
    }

    public double getImageColorG() {
        return this.imageColorG;
    }

    public double getImageColorB() {
        return this.imageColorB;
    }
}
