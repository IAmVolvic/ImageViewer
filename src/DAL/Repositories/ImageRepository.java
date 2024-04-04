package DAL.Repositories;

import BLL.Interfaces.IImageRepository;
import BLL.Models.Image;

import java.util.ArrayList;

public class ImageRepository implements IImageRepository {

    @Override
    public Image findById(int imageId) {
        return new Image()
                .setImageType("PNG")
                .setImagePath("/abc/egf/img.png");
    }

    @Override
    public ArrayList<Image> getAll() {
        return null;
    }

    @Override
    public void save(Image newImage) {

    }

    @Override
    public void update(Image selectedImage) {

    }

    @Override
    public void delete(Image selectedImage) {

    }
}
