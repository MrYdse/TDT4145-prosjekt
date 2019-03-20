package app;

import java.sql.*;
import java.util.Properties;

public abstract class Sql {

    /**
     *
     */

    private static final String connectionString = "jdbc:mysql://localhost:3306/database?autoReconnect=true&allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    protected Connection connection;
    private Statement statement;

    public Sql() {
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties props = new Properties();
            props.put("user", "fitboi");
            props.put("password", "123");
            this.connection = DriverManager.getConnection(connectionString, props);
            this.statement = this.connection.createStatement();
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect", e);
        }
    }

    private Object executeReturnQuery(String query) {
        try {
            return this.statement.executeQuery(query);
        } catch (Exception err) {
            return "Return query failed with error:\n" + err.getMessage();
        }
    }

    private Object executeUpdateQuery(String query) {
        try {
            this.statement.executeUpdate(query);
            return "Successfully executed update query";
        } catch (Exception err) {
            return "Update query failed with error:\n" + err.getMessage();
        }
    }

    private Object executeInsertQuery(String query) {
        try {
            this.statement.executeUpdate(query);
            return "Successfully executed insert query";
        } catch (Exception err) {
            return "Insert query failed with error:\n" + err.getMessage();
        }
    }

}
