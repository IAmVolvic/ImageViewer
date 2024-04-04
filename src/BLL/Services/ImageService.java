package BLL.Services;

import BLL.Interfaces.IImageRepository;
import BLL.Models.Image;
import DAL.Repositories.ImageRepositoryTest;

import java.util.ArrayList;

public class ImageService {

    private final IImageRepository imageRepository;

    public ImageService() {
        imageRepository = new ImageRepositoryTest();
    }

    public ImageService(IImageRepository imageRepo) {
        imageRepository = imageRepo;
    }

    public Image getImageById(int imageId){
        return imageRepository.findById(imageId);
    }

    public ArrayList<Image> getAllImages(){
        return imageRepository.getAll();
    }
}
