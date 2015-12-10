package chatsystem;

import static chatsystem.Chat.window;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class ConnectState implements State {

    private String id = null;
    private Context context = null;
    
    private ChatNIController controllerNI = null;
    private UserListViewController userListController = null;
    private MessageBoxController messageController = null;
    
    private SplitPane rootLayout;

    public ConnectState(Context context, String nickname) throws IOException {
        this.id = nickname;
        this.context = context;
        controllerNI = new ChatNIController(this);
        this.initRootLayout();
        this.showUserListView();
    }
   
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ConnectState.class.getResource("ConnectView.fxml"));
            rootLayout = (SplitPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            
            window.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    
     public void showUserListView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ConnectState.class.getResource("UserListView.fxml"));
            AnchorPane userListView = (AnchorPane) loader.load();
            
            this.userListController = loader.getController() ;
            userListController.setState(this);

            rootLayout.getItems().set(0, userListView);

        } catch (IOException e) {
        }
    }

    @Override
    public void updateContext(String nickname) {
        context.setState(new DisconnectState(context));
    }

    public String getId() {
        return this.id;
    }
    
    public SplitPane getLayout() {
        return this.rootLayout;
    }
    
    public Context getContext() {
        return this.context;
    }

    public ChatNIController getController() {
        return this.controllerNI;
    }

    public MessageBoxController getMessageBoxController() {
        return this.messageController;                
    }
    
    public UserListViewController getUserListViewController() {
        return this.userListController;
    }

}
