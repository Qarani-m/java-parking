package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class signup {
    @FXML
    private TextField c_password;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField phone;
    @FXML
    private Button signup_btn;
    @FXML
    private Label signup_login;
    @FXML
    private TextField username;
    @FXML
    void signup(ActionEvent event) {
        System.out.println("helo");

    }

}
