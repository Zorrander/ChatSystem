package chatsystem;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Chat {

    private int numPort = 2042;

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

}
