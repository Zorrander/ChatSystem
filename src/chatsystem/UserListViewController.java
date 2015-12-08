package chatsystem;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserListViewController extends ViewComponent {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> ipAdressColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label ipAdressLabel;

    // Reference to the main application. (Chat or UserList...?)
    private Chat mainChat;

    public UserListViewController() {
    }

    @FXML
    private void initialize() {
        // Initialize the userList with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        ipAdressColumn.setCellValueFactory(cellData -> cellData.getValue().getAdressProperty());
        // Clear person details.
        showMessageBox(null);

        // Listen for selection changes and show the person details when changed.
        userTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMessageBox(newValue));
    }

    /**
     * Shows the person overview inside the root layout.
     */
    @FXML
    private void showMessageBox(User user) {
        if (user != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ConnectState.class.getResource("MessageBox.fxml"));
                AnchorPane messageBox = (AnchorPane) loader.load();

                mainChat.getLayout().getItems().set(1, messageBox);

            } catch (IOException e) {
            }
        } else {

        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainChat
     */
    public void setMainChat(Chat mainChat) {
        this.mainChat = mainChat;
        // Add observable list data to the table
        userTable.setItems(mainChat.getUserData());
    }

}
