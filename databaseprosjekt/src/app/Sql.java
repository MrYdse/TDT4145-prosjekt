package app;

import java.sql.*;
import java.util.Properties;

public abstract class Sql {

    protected Connection connection;
    
    public Sql() {
    }

    public void connect() {
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties props = new Properties();
            props.put("user", "root");
            props.put("password", "root");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database?autoReconnect=true&useSSL=false", props);
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect", e);
        }
    }

}