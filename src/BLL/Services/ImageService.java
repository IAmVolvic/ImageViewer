package BLL.Services;

import BLL.Interfaces.IImageRepository;
import BLL.Models.Image;
import DAL.Repositories.ImageRepository;

public class ImageService {

    private final IImageRepository imageRepository;

    public ImageService() {
        imageRepository = new ImageRepository();
    }

    public ImageService(IImageRepository imageRepo) {
        imageRepository = imageRepo;
    }

    public Image getImageById(int imageId){
        return imageRepository.findById(imageId);
    }
}
