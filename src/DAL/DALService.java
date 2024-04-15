package DAL;

import java.io.File;
import java.sql.*;

public class DALService {
    private Connection connection;

    public DALService() {
        try {
            File dbFile = new File("ProjectDB.db");
            if (!dbFile.exists()) {
                createDatabase();
            } else {
                connection = DriverManager.getConnection("jdbc:sqlite:ProjectDB.db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection thisConnection(){
        return connection;
    }


    private void createDatabase() {
        System.out.println("Creating the DB");
        try {
            // Connect to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:ProjectDB.db");

            // SQL query to create the Images table
            String sql = "CREATE TABLE IF NOT EXISTS Images (\n"
                    + "    id INTEGER PRIMARY KEY,\n"
                    + "    imagePath VARCHAR(50) NOT NULL,\n"
                    + "    imageName VARCHAR(50) NOT NULL,\n"
                    + "    imageDescription VARCHAR(50) NOT NULL,\n"
                    + "    imageSize BIGINT NOT NULL,\n"
                    + "    imageType VARCHAR(50) NOT NULL,\n"
                    + "    imageDimensionsX BIGINT NOT NULL,\n"
                    + "    imageDimensionsY BIGINT NOT NULL,\n"
                    + "    imageColorR BIGINT NOT NULL,\n"
                    + "    imageColorG BIGINT NOT NULL,\n"
                    + "    imageColorB BIGINT NOT NULL,\n"
                    + "    imageColorMix BIGINT NOT NULL\n"
                    + ");";

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the SQL statement
            statement.execute(sql);

            // Close the statement
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
