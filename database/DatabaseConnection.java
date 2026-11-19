
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection()
            throws SQLException {

        String host = System.getenv("DB_HOST");
        String port = System.getenv("DB_PORT");
        String database = System.getenv("DB_NAME");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (host == null || port == null ||
            database == null || username == null ||
            password == null) {

            throw new SQLException(
                "Database environment variables are missing!"
            );
        }

        String url = "jdbc:mysql://" + host + ":" + port
                + "/" + database
                + "?sslMode=REQUIRED"
                + "&serverTimezone=UTC";

        return DriverManager.getConnection(
            url, username, password
        );
    }
}
