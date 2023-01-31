package controller;

import java.io.IOException;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Login {
    Mysql mysql = new Mysql();
    private Scene scene;
    private Stage stage;
    private Parent root;




    private String username;
    private String password;

    @FXML
    private Button login_btn;
    @FXML
    private PasswordField login_pwd;
    @FXML
    private Button login_signup;
    @FXML
    private TextField login_usr;
    @FXML
    private Label setText;

    Connection connection =null;
    public void login(ActionEvent e) throws SQLException {
        username = login_usr.getText();
        password = login_pwd.getText();

        if(username.length() <1 || password.length()<1) {
            if(username.length()<1){
                setText.setText("No Username");
            }
            if(password.length()<1){
                setText.setText("No password");
            }
            return;
        }
        loginAction(username,password);
    }
    private void loginAction(String username_, String password) throws SQLException {
        String sql = "select password from users where username ='" + username_ + "';";
        ResultSet resultSet = mysql.getUserCredentials(sql);
        String pass = null;
        while (resultSet.next()) {
            pass = resultSet.getString("password");
        }
        if (pass.equals(password)) {
            setText.setText("logged in");
        } else {
            setText.setText("Wrong username or password");
            return;
        }
        mysql.close();
    }
    public void goToSignup(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/signup.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
