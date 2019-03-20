package app;

import java.sql.*;
import java.util.Properties;

public abstract class Sql {

    /**
     *
     */

    private static final String connectionString = "jdbc:mysql://localhost:3306/database?autoReconnect=true&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    protected Connection connection;

    public Sql() {
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties props = new Properties();
            props.put("user", "fitboi");
            props.put("password", "123");
            connection = DriverManager.getConnection(connectionString, props);
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect", e);
        }
    }

}
