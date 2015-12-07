
package chatsystem;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MessageBox extends ViewComponent{
    private Button sndButton ;
    private TextField msg ;
    private ConnectView parentView ;
    private Group grp ;
    
    public MessageBox(ConnectView view){
        this.parentView = view;
        sndButton = new Button();
        sndButton.setText("Send");
        msg = new TextField();
    }
    
    public Group getGroupe(){
        return this.grp;
    }
    
}
