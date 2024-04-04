package DAL.Repositories;

import BLL.Interfaces.IImageRepository;
import BLL.Models.Image;

import java.util.ArrayList;

public class ImageRepositoryTest implements IImageRepository {

    @Override
    public Image findById(int imageId) {
        return new Image()
                .setImageType("jpg")
                .setImagePath("/Images/C.jpg");
    }

    @Override
    public ArrayList<Image> getAll() {
        ArrayList<Image> Images = new ArrayList<>();
        Images.add(new Image().setImageType("jpg").setImagePath("/Images/A.jpg"));
        Images.add(new Image().setImageType("jpg").setImagePath("/Images/B.jpg"));
        Images.add(new Image().setImageType("jpg").setImagePath("/Images/C.jpg"));
        Images.add(new Image().setImageType("jpg").setImagePath("/Images/D.jpg"));
        return Images;
    }

    @Override
    public void save(Image newImage) {}

    @Override
    public void update(Image selectedImage) {

    }

    @Override
    public void delete(Image selectedImage) {

    }
}
