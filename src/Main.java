import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;

/**
 * Restaurant management systems using custom ADT
 *
 *
 * References:
 * Java LinkedList Assignment Implementation, https://www.youtube.com/watch?v=VR363sIAvr4
 * How to implement a LinkedList Class from scratch in Java, https://crunchify.com/how-to-implement-a-linkedlist-class-from-scratch-in-java/
 *
 * @version 1.0
 * @author  Gianluca Zuccarelli <20079110>
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/Dashboard.fxml"));
        primaryStage.setTitle("Booking.io");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
