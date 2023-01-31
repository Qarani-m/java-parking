package controller;
import com.mysql.cj.jdbc.MysqlSQLXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import java.lang.Math;
import java.rmi.server.ExportException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class signup {
    Mysql mysql = new Mysql();

    private Scene scene;
    private Stage stage;
    private Parent root;

    private String email_;
    private String phone_;
    private String username_;
    private String password_;
    private String user_id;

    @FXML
    private Label err_text;
    @FXML
    private PasswordField c_password;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField phone;
    @FXML
    private Button signup_btn;
    @FXML
    private TextField username;
    @FXML
    private Button signup_login;


    public Boolean checkLengthAndType(String email_,String pass,String cpass,String phone_){
        Boolean toReturn=false;
        String pattern = ".*@.*";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(email_);
        if (pass.equals(cpass) && phone_.length()==10 && m.matches()){

            try{
                Integer.parseInt(phone_);
                toReturn=true;
            }catch (NumberFormatException e){
                err_text.setText("Invalid phone number");
                err_text.setTextFill(Color.RED);
                System.out.println(e);
                phone.setStyle("-fx-border-color:red;");
                toReturn =false;

            }
        }else{
            if (pass.equals(cpass)==false){
                password.setStyle("-fx-border-color:red;");
                c_password.setStyle("-fx-border-color:red;");
                err_text.setText("Password Mismatch");
                err_text.setTextFill(Color.RED);

                toReturn=false;
            }
            if (phone_.length()!=10){
                phone.setStyle("-fx-border-color:red;");
                err_text.setText("Phone number length invalid");
                err_text.setTextFill(Color.RED);
                toReturn=false;
            }
            if (m.matches()==false){
                email.setStyle("-fx-border-color:red;");
                err_text.setText("Invalid Email");
                err_text.setTextFill(Color.RED);
                toReturn=false;
            }

            toReturn =false;
        }
        return toReturn;
    }
//    check empty fields
    public Boolean checkEmpty(String email_,String pass_,String cpass_,String phone_,String username_){
        Boolean toReturn =true;
        if(email_.length()<1){email.setStyle("-fx-border-color:red;");toReturn =false;}else{email.setStyle("-fx-border-color:green;");}
        if(username_.length()<1){username.setStyle("-fx-border-color:red;");toReturn =false;}else{username.setStyle("-fx-border-color:green;");}
        if(phone_.length()<1){phone.setStyle("-fx-border-color:red;");toReturn =false;}else{phone.setStyle("-fx-border-color:green;");}
        if(pass_.length()<1){password.setStyle("-fx-border-color:red;");toReturn =false;}else{password.setStyle("-fx-border-color:green;");}
        if(cpass_.length()<1){c_password.setStyle("-fx-border-color:red;");toReturn =false;}else{c_password.setStyle("-fx-border-color:green;");}
        err_text.setText("Higlighted fields cant be empty");
        err_text.setTextFill(Color.RED);
        return toReturn;
    }
    @FXML
    void signup(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
         email_ = email.getText();
         username_ = username.getText();
         phone_ = phone.getText();
         password_ = password.getText();
         user_id = String.valueOf(Math.random());
        Boolean empty = checkEmpty(email_,password_,c_password.getText(),phone_,username_);
        Boolean length = checkLengthAndType(email_,password_,c_password.getText(),phone_);
        if(empty==true) {
         if(length==true){
             String sql =String.format("insert into users(user_id,email,username,phone,password)values('%s','%s','%s','%s','%s');",user_id,email_,username_,phone_,password_);
             if(mysql.insertData(sql).equals("200")){
                 err_text.setText("Sign Up sucessfull");
                 email.setStyle("-fx-border-color:green;");
                 password.setStyle("-fx-border-color:green;");
                 c_password.setStyle("-fx-border-color:green;");
                 err_text.setTextFill(Color.GREEN);
             }else {
                 err_text.setText("[Database:]Something Went Wrong Come again Latter");
                 err_text.setTextFill(Color.RED);
             }
             mysql.close();
         }else{
             err_text.setText("Something Went Wrong Come again Latter");
             err_text.setTextFill(Color.RED);
         }
        }else {
            System.out.println("Stuff");

        }
    }




































    public void goToLogin(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void checkLengthAndType(KeyEvent keyEvent) {

    }
}
