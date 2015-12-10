package chatsystem;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DisconnectViewController {

  
    private DisconnectState state ;
    @FXML
    private Button connectButton ;
    
    @FXML
    private TextField nicknameBox ;
    
    public DisconnectViewController() {
    }
    
    @FXML
    private void initialize() {
    }
    
    @FXML
    public void connect() throws IOException{
         String name = nicknameBox.getCharacters().toString();
        //Update context state 
        state.updateContext(name); 
        
    }
    
    public void setState(DisconnectState state) {
        this.state=state;
    }
}
