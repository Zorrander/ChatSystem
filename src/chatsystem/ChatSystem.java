package chatsystem;

import java.net.SocketException;
import javafx.application.Application;
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
