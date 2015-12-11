/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatsystem;

import static chatsystem.Chat.window;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class DisconnectState implements State {

    private Context context = null;
    private DisconnectViewController viewController;
    private AnchorPane rootLayout;

    public DisconnectState(Context context) {
        this.context = context;
       
        this.initRootLayout();

        //initialisation de la vue
        window.setTitle("FakeBook");
        Scene scene = new Scene(rootLayout);
        window.setScene(scene);
        window.show();
    }

    public void initRootLayout()  {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ConnectState.class.getResource("DisconnectView.fxml"));
        try {
            rootLayout = (AnchorPane) loader.load();
            this.viewController = loader.getController() ;
            viewController.setState(this);
            viewController.setEnterAction();
        } catch (IOException ex) {
            Logger.getLogger(DisconnectState.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void updateContext(String nickname) {
        State currentState = context.getState();
        if (currentState == null) {
            context.setState(this);
        } else if (currentState == (this)) {
            try {
                context.setState(new ConnectState(context, nickname));
            } catch (IOException ex) {
                Logger.getLogger(DisconnectState.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Context getContext() {
        return this.context;
    }
}
