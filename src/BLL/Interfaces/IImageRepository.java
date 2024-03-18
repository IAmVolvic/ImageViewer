package BLL.Interfaces;

import BLL.Models.Image;
import java.util.ArrayList;

public interface IImageRepository {
    Image findById(int imageId);

    ArrayList<Image> findAll();

    void save(Image newImage);

    void update(Image selectedImage);

    void delete(Image selectedImage);
}
