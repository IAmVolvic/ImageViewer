package BLL.Interfaces;

import BLL.Models.Image;
import java.util.ArrayList;

public interface IImageRepository {
    Image findById(int imageId);

    ArrayList<Image> getAll();

    void save(Image newImage);

    void delete(int imageId);
}
