package chatsystem;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Chat {

    private static Chat instance = null;
    public static Context currentContext;
    
    public static Stage window;

    private Chat(Stage primaryStage) throws SocketException {
        
        this.window = primaryStage;

        //initialisation du contexte        
        currentContext = new Context(this);
        DisconnectState currentState = new DisconnectState(currentContext);
        currentState.updateContext("");
        
         primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public static Chat getInstance(Stage primaryStage) throws SocketException {

        if (instance == null) {
            instance = new Chat(primaryStage);
      
        }
        return instance;
        
        

    }

    /*public ObservableList<User> getUserData() {
        return this.chatModel.getUserData();
    }*/
    
}
