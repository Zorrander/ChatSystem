package chatsystem;

import common.Message;
import static common.Message.MsgType.TEXT_MESSAGE;
import common.MessageEnvoye;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class MessageBoxController {

    @FXML
    private ListView<Message> messageTable;
    
    @FXML
    private TextArea prompter;
    @FXML
    private Button sendButton;
    @FXML
    private Button disconnectButton;

    Message newMessage;

    private User user;

    private ConnectState state;

    public MessageBoxController() {
    }

    @FXML
    private void initialize() {
    }

    @FXML
    public void send() throws IOException {
        String textToSend = prompter.getText();
        newMessage = new Message(TEXT_MESSAGE, textToSend, state.getId());
        prompter.clear();
        user.addMessage(new MessageEnvoye(TEXT_MESSAGE, textToSend, state.getId()));
        state.getController().send(newMessage, user.getAdress());

    }

    @FXML
    public void disconnect() {
        state.disconnect();
    }

    public void setEnterAction() {
        prompter.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        send();
                    } catch (IOException ex) {
                        Logger.getLogger(DisconnectViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public void setState(ConnectState state) {
        this.state = state;
    }

    void setCurrentUser(User user) {
        this.user = user;
        messageTable.setItems(user.getMessageList());
    }

}
