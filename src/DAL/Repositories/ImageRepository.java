package DAL.Repositories;

import BLL.Interfaces.IImageRepository;
import BLL.Models.Image;
import DAL.DALService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ImageRepository implements IImageRepository {

    private DALService DALService = new DALService();

    @Override
    public Image findById(int imageId) {
        try {
            String sql = "SELECT * FROM images WHERE id = ?";
            PreparedStatement preparedStatement = DALService.thisConnection().prepareStatement(sql);
            preparedStatement.setInt(1, 1); // Set the ID parameter to 1
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Image()
                        .setImageId(resultSet.getInt("id"))
                        .setImagePath(resultSet.getString("imagePath"))
                        .setImageName(resultSet.getString("imageName"))
                        .setImageDescription(resultSet.getString("imageDescription"))
                        .setImageSize(resultSet.getLong("imageSize"))
                        .setImageType(resultSet.getString("imageType"))
                        .setImageDimensionsX(resultSet.getLong("imageDimensionsX"))
                        .setImageDimensionsY(resultSet.getLong("imageDimensionsY"))
                        .setImageColorR(resultSet.getLong("imageColorR"))
                        .setImageColorG(resultSet.getLong("imageColorG"))
                        .setImageColorB(resultSet.getLong("imageColorB"))
                        .setImageColorMIX(resultSet.getLong("imageColorMix"));
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Image> getAll() {
        ArrayList<Image> newList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM images";
            PreparedStatement preparedStatement = DALService.thisConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Image pl = new Image()
                        .setImageId(resultSet.getInt("id"))
                        .setImagePath(resultSet.getString("imagePath"))
                        .setImageName(resultSet.getString("imageName"))
                        .setImageDescription(resultSet.getString("imageDescription"))
                        .setImageSize(resultSet.getLong("imageSize"))
                        .setImageType(resultSet.getString("imageType"))
                        .setImageDimensionsX(resultSet.getLong("imageDimensionsX"))
                        .setImageDimensionsY(resultSet.getLong("imageDimensionsY"))
                        .setImageColorR(resultSet.getLong("imageColorR"))
                        .setImageColorG(resultSet.getLong("imageColorG"))
                        .setImageColorB(resultSet.getLong("imageColorB"))
                        .setImageColorMIX(resultSet.getLong("imageColorMix"));
                newList.add(pl);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newList;
    }

    @Override
    public void save(Image newImage) {
        try {
            String sql = "INSERT INTO images(imagePath, imageName, imageDescription, imageSize, imageType, imageDimensionsX, imageDimensionsY, imageColorR, imageColorG, imageColorB, imageColorMix) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DALService.thisConnection().prepareStatement(sql);
            ps.setString(1, newImage.getImagePath());
            ps.setString(2, newImage.getImageName());
            ps.setString(3, newImage.getImageDescription());
            ps.setLong(4, newImage.getImageSize());
            ps.setString(5, newImage.getImageType());
            ps.setLong(6, newImage.getImageDimensionsX());
            ps.setLong(7, newImage.getImageDimensionsY());
            ps.setLong(8, newImage.getImageColorR());
            ps.setLong(9, newImage.getImageColorG());
            ps.setLong(10, newImage.getImageColorB());
            ps.setLong(11, newImage.getImageColorMIX());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int imageId) {
        try {
            String sql = "DELETE FROM images WHERE id = ?";
            PreparedStatement ps = DALService.thisConnection().prepareStatement(sql);
            ps.setInt(1, imageId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
