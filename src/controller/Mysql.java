package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysql {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public ResultSet getUserCredentials(String query) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Parking", "martin", "qarani");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
        return resultSet;
    }

    public String insertData(String sql) throws ClassNotFoundException, SQLException {
        String msg=null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Parking", "martin", "qarani");
        try {
            statement = connection.createStatement();
            int result = statement.executeUpdate(sql);
            if (result > 0) {
                 msg = "200";
            }
        } catch (SQLException e) {
             msg="Error connecting to database: " + e.getMessage();
            System.out.println(msg);
        } finally {
            close();
        }
            return msg;

    }












    public void close(){
            try {
                resultSet = null;
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }

}
