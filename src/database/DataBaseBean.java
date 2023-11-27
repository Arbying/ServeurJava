package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseBean {
    private Connection connection;

    public DataBaseBean(String databaseName, String user, String password) throws SQLException {
        String url = "jdbc:mysql://192.168.100.12:3306/" + databaseName;
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public synchronized void executeUpdate(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }


    public synchronized void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public synchronized Connection getConnection() {
        return connection;
    }
}
