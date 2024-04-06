package DAL;

import java.sql.*;

public class DALService {
    private Connection connection;

    public DALService () {
        try {
            // Connect to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
        }catch (SQLException e){ e.printStackTrace(); }

        printImageName();
    }


    public void printImageName(){
        try {
            // Create a prepared statement to fetch the ImageName for ID 1
            String sql = "SELECT imageName FROM images WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1); // Set the ID parameter to 1

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a result exists
            if (resultSet.next()) {
                // Retrieve the ImageName from the result set
                String imageName = resultSet.getString("imageName");
                System.out.println("Image Name: " + imageName);
            } else {
                System.out.println("No image found with ID 1");
            }

            // Close the resources
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
