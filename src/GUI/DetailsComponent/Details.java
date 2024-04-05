package GUI.DetailsComponent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.text.DecimalFormat;

public class Details {
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
    private Label colorLabelG;
    private Label colorLabelB;


    public void initialize(
        Label imageIndex,
        Label imageName,
        Label imageDescription,
        Label sizeLabel,
        Label typeLabel,
        Label dimensionLabel,
        Label colorLabelR,
        Label colorLabelG,
        Label colorLabelB
    ) {
        this.imageIndex = imageIndex;
        this.imageName = imageName;
        this.imageDescription = imageDescription;
        this.sizeLabel = sizeLabel;
        this.typeLabel = typeLabel;
        this.dimensionLabel = dimensionLabel;
        this.colorLabelR = colorLabelR;
        this.colorLabelG = colorLabelG;
        this.colorLabelB = colorLabelB;
    }



    public void setImageIndex(int ImageIndex){
        this.imageIndex.setText("#"+ImageIndex);
    }

    public void setImageName(String ImageName){
        this.imageName.setText(ImageName);
    }

    public void setImageSize(long ImageSize){
        this.sizeLabel.setText(formatFileSize(ImageSize));
    }

    public void setImageType(String ImageType){
        this.typeLabel.setText(ImageType.toUpperCase());
    }

    public void setImageDimensions(long X, long Y){
        this.dimensionLabel.setText(X+"x"+Y);
    }

    public void setImageColorR(long ColorR){
        this.colorLabelR.setText("R: " + ColorR + " - " + formatNumberWithSuffix(ColorR));
    }

    public void setImageColorG(long ColorG){
        this.colorLabelG.setText("G: " + ColorG + " - " + formatNumberWithSuffix(ColorG));
    }

    public void setImageColorB(long ColorB){
        this.colorLabelB.setText("B: " + ColorB + " - " + formatNumberWithSuffix(ColorB));
    }

    private String formatFileSize(long size) {
        String hrSize;
        double bytes = size;
        double kilobytes = bytes / 1024;
        double megabytes = kilobytes / 1024;
        double gigabytes = megabytes / 1024;
        double terabytes = gigabytes / 1024;
        DecimalFormat dec = new DecimalFormat("0.00");
        if (terabytes > 1) {
            hrSize = dec.format(terabytes).concat(" TB");
        } else if (gigabytes > 1) {
            hrSize = dec.format(gigabytes).concat(" GB");
        } else if (megabytes > 1) {
            hrSize = dec.format(megabytes).concat(" MB");
        } else if (kilobytes > 1) {
            hrSize = dec.format(kilobytes).concat(" KB");
        } else {
            hrSize = dec.format(bytes).concat(" Bytes");
        }
        return hrSize;
    }

    public static String formatNumberWithSuffix(long number) {
        if (number < 1000) {
            return String.valueOf(number);
        }

        char[] suffix = new char[]{' ', 'K', 'M', 'B', 'T'}; // You can extend this array as needed

        int exp = (int) (Math.log(number) / Math.log(1000));

        DecimalFormat format = new DecimalFormat("0.#");
        return format.format(number / Math.pow(1000, exp)) + suffix[exp];
    }
}
