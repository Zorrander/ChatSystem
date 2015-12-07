package chatsystem;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class Chat {

    private final int numPort = 2042;

    private static Chat instance = null;
    private static Context currentContext ;
    private DisconnectView chatView = null;
    private UserList chatModel = null ;

    private Chat() throws SocketException {
        //Création d'un socket pour envoyer ET recevoir          
        DatagramSocket socket = new DatagramSocket(numPort);
        
        //initialisation du contexte        
        Context currentContext = new Context();
        DisconnectState currentState = new DisconnectState();
        currentState.doAction(currentContext);        
        
        //initialisation du modèle
        this.chatModel = UserList.getInstance();
        
        //initialisation du controleur
        ChatNIController controller = new ChatNIController(this);
        
        
        //initialisation de la vue
        this.chatView = new DisconnectView(controller);
        
    }

    public static Chat getInstance() throws SocketException {

        if (instance == null) {
            instance = new Chat();
        }

        return instance;

    }

    /**
     * @return le numéro de port
     */
    public int getNumPort() {
        return this.numPort;
    }
    
    /**
     * @return la vue du chat
     */
    public DisconnectView getCurrentView() {
        return this.chatView;
    }
    
    public ObservableList<User> getUserData() {
        return this.chatModel.getUserData() ;
    }
    
    /**
 * Shows the person overview inside the root layout.
 */
public void showUserListView() {
    try {
        // Load person overview.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Chat.class.getResource("UserListView.fxml"));
        AnchorPane userListView = (AnchorPane) loader.load();

        // Set person overview into the center of root layout.
        ConnectView.setCenter(userListView);

        // Give the controller access to the main app.
        UserListViewController controller = loader.getController();
        controller.setMainChat(this);

    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
