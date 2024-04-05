package BLL.Models;

public class Image {
    private String imageName;
    private long imageSize;
    private String imageType;
    private long imageDimensionsX;
    private long imageDimensionsY;
    private long imageColorR;
    private long imageColorG;
    private long imageColorB;
    private long imageColorMIX;
    private String imagePath;



    public Image setImageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

    public Image setImageSize(long imageSize) {
        this.imageSize = imageSize;
        return this;
    }

    public Image setImageType(String imageType) {
        this.imageType = imageType;
        return this;
    }

    public Image setImageDimensionsX(long imageDimensionsX) {
        this.imageDimensionsX = imageDimensionsX;
        return this;
    }

    public Image setImageDimensionsY(long imageDimensionsY) {
        this.imageDimensionsY = imageDimensionsY;
        return this;
    }

    public Image setImageColorR(long imageColorR) {
        this.imageColorR = imageColorR;
        return this;
    }

    public Image setImageColorG(long imageColorG) {
        this.imageColorG = imageColorG;
        return this;
    }

    public Image setImageColorB(long imageColorB) {
        this.imageColorB = imageColorB;
        return this;
    }

    public Image setImageColorMIX(long imageColorMIX) {
        this.imageColorMIX = imageColorMIX;
        return this;
    }

    public Image setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }


//    -----------------------

    public String getImageName() {
        return this.imageName;
    }

    public long getImageSize() {
        return this.imageSize;
    }

    public String getImageType() {
        return this.imageType;
    }

    public long getImageDimensionsX() {
        return this.imageDimensionsX;
    }

    public long getImageDimensionsY() {
        return imageDimensionsY;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public long getImageColorR() {
        return this.imageColorR;
    }

    public long getImageColorG() {
        return this.imageColorG;
    }

    public long getImageColorB() {
        return this.imageColorB;
    }

    public long getImageColorMIX() {
        return this.imageColorMIX;
    }
}
