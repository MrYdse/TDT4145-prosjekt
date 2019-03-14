package project;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Sql {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    
    public Sql() throws SQLException {
        connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/prosjekt?autoReconnect=true&useSSL=false", "root", "root");
        statement = connection.createStatement();
    }

}