package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DALService {


    public DALService () {
        Connection connection = null;
        Statement statement = null;

        try {
            // Connect to SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            statement = connection.createStatement();

            // Create a sample table (optional)
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (\n"
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " name TEXT NOT NULL,\n"
                    + " email TEXT NOT NULL UNIQUE\n"
                    + ");";

            // Execute SQL query to create table
            statement.execute(createTableSQL);

            System.out.println("Database and table created successfully.");


        }catch (SQLException e){ e.printStackTrace(); }
    }


}
