package controller;
//
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.*;
//
//public class Mysql {
//    Connection connection = null;
//    public String mysql(String query, String[] args) {
//        String password = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "martin", "qarani");
//            Statement statement;
//            ResultSet resultSet;
//
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(query);
//            String username;
//
//            int i = 0;
//            while (resultSet.next()) {
//                password = resultSet.getString(args[0]);
//            }
//            resultSet.close();
//            statement.close();
//            connection.close();
//            return password;
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return"";
//
//    }
//
//}




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysql {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static String[] getUserCredentials(String query, String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "martin", "qarani");
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                args[0] = resultSet.getString(args[0]);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        } finally {
            try {
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
        return args;
    }
}
