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

    private Object excecuteReturnQuery(String query) {
        try {
            return this.statement.executeQuery(query);
        } catch (Exception e) {
            return "Unsuccesful";
        }
    }

    private Object executeUpdateQuery(String query) {
        try {
            this.statement.executeUpdate(query);
            return "Successful";
        } catch (Exception e) {
            return "Unsuccsessful";
        }
    }

    private Object executeInsertQuery(String query) {
        try {
            this.statement.executeUpdate(query);
            return "Successful";
        } catch (Exception e) {
            return "Unsuccessful";
        }
    }

}