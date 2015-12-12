package chatsystem;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DisconnectViewController {
    
    private DisconnectState state;
    @FXML
    private Button connectButton;
    
    @FXML
    private TextField nicknameBox;
    
    public DisconnectViewController() {
    }
    
    @FXML
    private void initialize() {
    }
    
    @FXML
    public void connect() throws IOException {
        String name = nicknameBox.getCharacters().toString();
        //Update context state 
        if (!(name.isEmpty())) {
            //Update context state 
            state.updateContext(name);            
        } else {
            connectButton.requestFocus();
            nicknameBox.setPromptText("Enter a nickname");
        }
    }
    
    public void setEnterAction() {
        nicknameBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        connect();
                    } catch (IOException ex) {
                        Logger.getLogger(DisconnectViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    public void setState(DisconnectState state) {
        this.state = state;
    }
}
