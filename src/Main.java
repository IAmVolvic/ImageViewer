import BLL.Services.ImageService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        Application.launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        Parent root = loader.load();

        // Set the minimum width and height
        double minWidth = 1280;
        double minHeight = 750;

        // Set the initial width and height
        double initialWidth = 1280;
        double initialHeight = 750;

        // Set the stage properties
        primaryStage.setScene(new Scene(root, initialWidth, initialHeight));
        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);

        // Show the stage
        primaryStage.show();
    }
}
