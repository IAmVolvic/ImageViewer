package BLL.Services;

import BLL.Interfaces.IImageRepository;
import BLL.Models.Image;
import BLL.Models.ImageDetails;
import DAL.Repositories.ImageRepositoryTest;
import GUI.ImageUploadComponent.UploadedImage;

import java.io.File;
import java.util.ArrayList;

public class ImageService {

    // Repos
    private final IImageRepository imageRepository;

    // States
    private boolean isPlaying = false;


    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }


    public ImageService() {
        imageRepository = new ImageRepositoryTest();
    }

    public ArrayList<Image> getAllImages(){
        return imageRepository.getAll();
    }

    public void uploadImage(String imgPath, UploadedImage uploadedImageComponent){
        File image = new File(imgPath);
        ImageDetails imageDetails = new ImageDetails();

        Thread imageProcessingThread = new Thread(() -> {
            long [] imageRGB = imageDetails.getImageRGB(image, uploadedImageComponent);
            System.out.println(imageRGB[0]);
        });

        imageProcessingThread.start();
    }
}
