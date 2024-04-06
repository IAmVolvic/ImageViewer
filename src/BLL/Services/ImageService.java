package BLL.Services;

import BLL.Interfaces.IImageRepository;
import BLL.Models.Image;
import DAL.Repositories.ImageRepositoryTest;

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
}
