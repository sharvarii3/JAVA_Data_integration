// DBConnection.java
import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "#NandN%100";

    public static Connection getConnection() throws SQLException {
        System.out.println("Inside getConnection");
        Connection dbConnection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
        dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);

        if (dbConnection != null) {
            System.out.println("Successfully connected to MySQL database test");
        } else {
            System.out.println("Error connecting to MySQL database test");
        }
        return dbConnection;
    }
}
