package chatsystem;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

public class Chat {

    private final int numPort = 2042;

    private static Chat instance = null;
    public static Context currentContext;
    public static Stage window;
    private SplitPane rootLayout;
    private UserList chatModel = null;
    private ChatNIController controller = null ;

    private Chat(Stage primaryStage) throws SocketException {
        //Création d'un socket pour envoyer ET recevoir 
        this.window = primaryStage;
        DatagramSocket socket = new DatagramSocket(numPort);

        //initialisation du controleur
        this.controller = new ChatNIController(this);

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
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ConnectState.class.getResource("ConnectView.fxml"));
            rootLayout = (SplitPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumPort() {
        return this.numPort;
    }

    public SplitPane getLayout() {
        return this.rootLayout;
    }

    public ObservableList<User> getUserData() {
        return this.chatModel.getUserData();
    }
    
    public ChatNIController getChatNIController() {
        return this.controller ;
    }
    public String getNickname() {
    return getChatNIController().getUserNickname() ;
    }
}
