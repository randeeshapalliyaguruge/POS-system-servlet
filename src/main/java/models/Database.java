package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private Connection connection;

    private Database() {

        try {

            Class.forName("org.sqlite.JDBC"); //

            String url = "jdbc:sqlite:C:\\Users\\Randeesha\\IdeaProjects\\CB009991_CCCP_2\\database\\database.sqlite";
            this.connection = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    public static synchronized Database getInstance() {

        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                reestablishConnection();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    private void reestablishConnection() throws SQLException {
        String url = "jdbc:sqlite:C:\\Users\\Randeesha\\IdeaProjects\\CB009991_CCCP_2\\database\\database.sqlite";
        this.connection = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite has been reestablished.");
    }
}