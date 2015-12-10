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
    
       
    private ConnectState state;

    public MessageBoxController() {
    }

    @FXML
    private void initialize() {
    }
    
    @FXML
    public void send() throws IOException {
        
             Message newMessage = new Message(TEXT_MESSAGE, prompter.getText()) ;
             
            state.getController().send(newMessage, user.toString());
         
    }
    
        public void showMessageBox(User user) {
        if (user != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ConnectState.class.getResource("MessageBox.fxml"));
                AnchorPane messageBox = (AnchorPane) loader.load();

                state.getLayout().getItems().set(1, messageBox);
                
                MessageBoxController controller = loader.getController();
                controller.setCurrentUser(user) ;

            } catch (IOException e) {
            }
        } 
    }
   
       
    public void setState(ConnectState state) {
        this.state=state;
    }
    
    void setCurrentUser(User user) {
         this.user = user ;
    }

    

}
