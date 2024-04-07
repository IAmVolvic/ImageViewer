package BLL.Models;

import GUI.ImageUploadComponent.UploadedImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class ImageDetails {
    public String getImageName(File object){
        return object.getName().replaceFirst("[.][^.]+$", "");
    }

    public long getImageSize(File object){
        return object.length();
    }

    public String getImageFileType(File object){
        String fileName = object.getName();
        int lastIndexOfDot = fileName.lastIndexOf('.');

        if (lastIndexOfDot == -1){
            return "NoExtension";
        }

        // AKA file[.] > file extension <
        return fileName.substring(lastIndexOfDot + 1);
    }


    public long[] getImageRGB(File object){
        MultiThread threadTask = new MultiThread();
        return threadTask.getNumberOfPixels(object, Runtime.getRuntime().availableProcessors());
    }

    public long[] getImageRGB(File object, UploadedImage uploadedImage){
        MultiThread threadTask = new MultiThread();
        return threadTask.getNumberOfPixels(object, 1, uploadedImage);
    }

    public long[] getImageXnYSize(File object){
        MultiThread threadTask = new MultiThread();
        return threadTask.getImageDimensions(object, Runtime.getRuntime().availableProcessors());
    }
}
