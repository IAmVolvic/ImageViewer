package DAL.Repositories;

import BLL.Interfaces.IImageRepository;
import BLL.Models.Image;
import BLL.Models.ImageDetails;
import DAL.DALService;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class ImageRepositoryTest implements IImageRepository {
    @Override
    public Image findById(int imageId) {
        return new Image()
                .setImageType("jpg")
                .setImagePath("/Images/C.jpg");
    }

    @Override
    public ArrayList<Image> getAll() {
        return null;
    }

    @Override
    public void save(Image newImage) {}

    @Override
    public void delete(int imageId) {

    }
}
