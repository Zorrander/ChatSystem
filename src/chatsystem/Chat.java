package chatsystem;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;


public class Chat {

    private final int numPort = 2042;

    private static Chat instance = null;
    public static Context currentContext ;
    public static Stage window ;
    private UserList chatModel = null ;

    private Chat(Stage primaryStage) throws SocketException {
        //Création d'un socket pour envoyer ET recevoir 
        this.window = primaryStage ;
        DatagramSocket socket = new DatagramSocket(numPort);
        
        //initialisation du controleur
        ChatNIController controller = new ChatNIController(this);
        
        //initialisation du contexte        
        currentContext = new Context();
        DisconnectState currentState = new DisconnectState(controller);
        currentState.updateContext(currentContext, currentState);        
        
        //initialisation du modèle
        this.chatModel = UserList.getInstance();   
    }

    public static Chat getInstance(Stage primaryStage) throws SocketException {

        if (instance == null) {
            instance = new Chat(primaryStage);
        }

        return instance;

    }

    /**
     * @return le numéro de port
     */
    public int getNumPort() {
        return this.numPort;
    }

    
    public ObservableList<User> getUserData() {
        return this.chatModel.getUserData() ;
    }
    


}
