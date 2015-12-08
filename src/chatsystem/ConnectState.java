package chatsystem;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ConnectState implements State {

    private String id = null;
    private ChatNIController controllerNI;

    public ConnectState(String nickname, ChatNIController controller) throws IOException {
        this.id = nickname;
        controllerNI = controller;
        controllerNI.getChat().initRootLayout();
        showUserListView();
    }

    private void showUserListView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ConnectState.class.getResource("UserListView.fxml"));
            AnchorPane userListView = (AnchorPane) loader.load();

            controllerNI.getChat().getLayout().getItems().set(0, userListView);

            // Give the controller access to the main app.
            UserListViewController controller = loader.getController();
            controller.setMainChat(controllerNI.getChat());

        } catch (IOException e) {
        }
    }

    public void updateContext(Context context, State newState) {
        context.setState(newState);
    }

    public String getId() {
        return this.id;
    }
    
    public ChatNIController getController() {
        return this.controllerNI;
    }

    @Override
    public String toString() {
        return "Stop State";
    }

    @Override
    public void doAction(Context context) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
