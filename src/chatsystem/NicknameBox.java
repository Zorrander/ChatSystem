package chatsystem;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NicknameBox extends ViewComponent {
    private Button button ;
    private TextField nicknameBox ;
    private DisconnectView parentView ; 
    
    public NicknameBox(DisconnectView view){
        this.parentView = view;
        button = new Button();
        nicknameBox = new TextField(); 
        button.setText("Connect");
        button.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {                
                String name = nicknameBox.getCharacters().toString();
                try {    
                    parentView.connect(name);
                } catch (IOException ex) {
                    Logger.getLogger(NicknameBox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    /**
     * @return le boutton de connexion
     */
    public Button getButtonConnect() {
        return this.button;
    }
    /**
     * @return le textField avec l'id
     */
    public TextField getNicknameField() {        
        return this.nicknameBox;
    }
       
}
