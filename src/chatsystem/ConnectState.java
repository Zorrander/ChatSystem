package chatsystem;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ConnectState implements State {
   private String id = null;
   private BorderPane rootLayout;
   ChatNIController controllerNI ;
   
   public ConnectState(String nickname, ChatNIController controller) throws IOException{
       this.id = nickname ;
       controllerNI = controller ;
       initRootLayout();
       showUserListView();       
   }
   
   /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ConnectState.class.getResource("ConnectView.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            Chat.window.setScene(scene);
            Chat.window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
 * Shows the person overview inside the root layout.
 */
public void showUserListView() {
    try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ConnectState.class.getResource("UserListView.fxml"));
        AnchorPane userListView = (AnchorPane) loader.load();

        // Set person overview into the center of root layout.
        rootLayout.setCenter(userListView);

        // Give the controller access to the main app.
        UserListViewController controller = loader.getController();
        controller.setMainChat(controllerNI.getChat());

    } catch (IOException e) {
        e.printStackTrace();
    }
}
   public String toString(){
      return "Stop State";
   }  
   public void updateContext(Context context,State newState){
       context.setState(newState);
   }

   public String getId(){
       return this.id;
   }
   
    @Override
    public void doAction(Context context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
