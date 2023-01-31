import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Parking");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            System.out.println(e);
        }

    }
}