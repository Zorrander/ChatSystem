package chatsystem;

import common.Message;
import static common.Message.MsgType.TEXT_MESSAGE;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class MessageBoxController {

    @FXML
    private TextArea messageView;
    @FXML
    private TextArea prompter;
    @FXML
    private Button sendButton;
    
    private User user ;
    
    // Reference to the main application.    
    private Chat mainChat;

    public MessageBoxController() {

    }

    @FXML
    private void initialize() {
    }
    
    @FXML
    private void send() throws IOException {
        /** On verifie qu'on est dans le bon état, c-à-d connect. */
        if ( "Connected".equals(Chat.currentContext.getState().toString())) {
            /** Si c'est le cas (il n'y a pas de raisons que ça ne le soit pas, on peut envoyer notre message
             *  qui est dans le "prompter"
             *  Pour le second param on doit le recup dans UserListViewController
             *  Accessible seulement par Chat -> ConnectState -> UserListViewController
             */
            
            /** Creation du TEXT_MESSAGE a envoyer */
             Message newMessage = new Message(TEXT_MESSAGE, prompter.getText()) ;
             
             mainChat.getChatNIController().send(newMessage, user.toString());
        }      
    }
    
    public void setMainChat(Chat mainChat) {
        this.mainChat = mainChat;
        // Add observable list data to the table        
    }

    void setCurrentUser(User user) {
         this.user = user ;
    }

    

}
