package BLL.Services;

import BLL.BLLService;
import BLL.Interfaces.IImageRepository;
import BLL.Models.FileSelector;
import BLL.Models.Image;
import BLL.Models.MultiThread;
import DAL.Repositories.ImageRepository;
import GUI.Classes.ServiceFactory;
import GUI.ImageUploadComponent.UploadedImage;
import javafx.event.ActionEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ImageService {

    // Repos
    private final IImageRepository imageRepository;

    // States
    private boolean isPlaying = false;

    // ProcessingArray
    private ArrayList<Image> processingArray = new ArrayList<>();


    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }


    public ImageService() {
        imageRepository = new ImageRepository();
    }

    public ArrayList<Image> getAllImages(){
        return imageRepository.getAll();
    }

    public List<File> selectImages(ActionEvent event){
        return new FileSelector().promptFilePicker(event);
    }


    public void processImage(String imgPath, UploadedImage uploadedImageComponent){
        File file = new File(imgPath);
        Image newImage = new Image().setImageDescription("No Description Given").setImagePath("/Images/"+file.getName());
        processingArray.add(newImage);

        Thread imageProcessingThread = new Thread(() -> {
            long [] imageRGB = BLLService.imageDetails.getImageRGB(file, uploadedImageComponent);
            long [] imageXnY = BLLService.imageDetails.getImageXnYSize(file);

            if (imageRGB[0] < 1 && imageRGB[1] < 1 && imageRGB[2] < 1 && imageRGB[3] < 1) {
                System.out.println("Failed");
            }

            newImage.setImageName(BLLService.imageDetails.getImageName(file))
                    .setImageType(BLLService.imageDetails.getImageFileType(file))
                    .setImageSize(BLLService.imageDetails.getImageSize(file))
                    .setImageDimensionsX(imageXnY[0])
                    .setImageDimensionsY(imageXnY[1])
                    .setImageColorR(imageRGB[0])
                    .setImageColorG(imageRGB[1])
                    .setImageColorB(imageRGB[2])
                    .setImageColorMIX(imageRGB[3]);
        });

        imageProcessingThread.start();
    }


    public Image getProcessedImageByIndex(int Index){
        if (Index >= 0 && Index < processingArray.size()) {
            return processingArray.get(Index);
        } else {
            return null;
        }
    }


    public boolean uploadImage(int ProcessedImageIndex){
        imageRepository.save(processingArray.get(ProcessedImageIndex));
        processingArray.remove(ProcessedImageIndex);
        return true;
    }


    public void shutdownThreads(){
        BLLService.imageDetails.killThreads();
        processingArray.clear();
    }
}
