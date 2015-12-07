package chatsystem;

import java.net.SocketException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChatSystem extends Application {
    
    @Override
    public void start(Stage primaryStage) throws SocketException {
       
        Chat monChat ;       
        monChat = Chat.getInstance(primaryStage) ;   
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
