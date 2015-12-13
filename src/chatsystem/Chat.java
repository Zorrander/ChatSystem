package chatsystem;


import java.net.SocketException;

import javafx.event.EventHandler;
import javafx.scene.image.Image;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Chat {

    private static Chat instance = null;
    public static Context currentContext;

    public static Stage window;

    private Chat(Stage primaryStage) throws SocketException {

        this.window = primaryStage;
        window.getIcons().add(new Image(Chat.class.getResourceAsStream("icon_final.png")));

        //initialisation du contexte        
        currentContext = new Context(this);
        DisconnectState currentState = new DisconnectState(currentContext);
        currentState.updateContext("");

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                currentContext.disconnect();
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
