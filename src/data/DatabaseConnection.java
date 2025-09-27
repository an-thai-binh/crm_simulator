package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:h2:mem:crm;INIT=RUNSCRIPT FROM 'classpath:data.sql'";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connect successfully: " + c.getMetaData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    public static void closeConnection(Connection c) {
        if(c != null) {
            try {
                c.close();
            } catch (SQLException e) {
                System.out.println("Close connection failed: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Connection c = DatabaseConnection.getConnection();
    }
}
