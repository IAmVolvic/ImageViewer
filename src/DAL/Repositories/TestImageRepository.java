package DAL.Repositories;

import BLL.Interfaces.IImageRepository;
import BLL.Models.Image;

import java.util.ArrayList;

public class TestImageRepository implements IImageRepository {

    @Override
    public Image findById(int imageId) {
        return null;
    }

    @Override
    public ArrayList<Image> findAll() {
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
