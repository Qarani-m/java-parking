package controller;

import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Login {
    Mysql em = new Mysql();
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
    public void login(ActionEvent e){
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

        loginAction(username,"qarani");
    }
    private void loginAction(String username_, String password)  {
        String sql = "select password from users where username ='"+username_+"';";
        String []args={"password"};
        String []results=em.getUserCredentials(sql,args);
        String uname = (String)results[0];
        System.out.println(uname);
        if(results[0].equals(uname)){
            setText.setText("logged in");
        }else {
            setText.setText("Wrong username or password");
            return;
        }
    }

    public void goToSignup(ActionEvent actionEvent) {

    }
}
