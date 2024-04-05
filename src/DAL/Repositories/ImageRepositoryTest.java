package DAL.Repositories;

import BLL.Interfaces.IImageRepository;
import BLL.Models.Image;
import BLL.Models.ImageDetails;

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
        ArrayList<Image> Images = new ArrayList<>();

        // Making my life a little easy
        File directory = new File("resources/Images");
        for (File file : directory.listFiles()){
            ImageDetails imageDetails = new ImageDetails();
            long [] imageRGB = imageDetails.getImageRGB(file);
            long [] imageXnY = imageDetails.getImageXnYSize(file);

            Images.add(
                    new Image()
                            .setImageName(imageDetails.getImageName(file))
                            .setImageType(imageDetails.getImageFileType(file))
                            .setImagePath("/Images/"+file.getName())
                            .setImageSize(imageDetails.getImageSize(file))
                            .setImageDimensionsX(imageXnY[0])
                            .setImageDimensionsY(imageXnY[1])
                            .setImageColorR(imageRGB[0])
                            .setImageColorG(imageRGB[1])
                            .setImageColorB(imageRGB[2])
                            .setImageColorMIX(imageRGB[3])
            );
        }
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
