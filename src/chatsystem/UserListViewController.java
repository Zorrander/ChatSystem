package chatsystem;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    private ConnectState state = null;

    public UserListViewController() {
    }

    @FXML
    private void initialize() {
        // Initialize the userList with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        ipAdressColumn.setCellValueFactory(cellData -> cellData.getValue().getAdressProperty());
        // Clear person details.

    }

    /**
     * Shows the person overview inside the root layout.
     */
    public User getSelectedUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            return selectedUser;
        } else {
            // Nothing selected.
            return null;
        }
    }

    public void setState(ConnectState state) {
        this.state = state;
    }

    public void setInteraction() {
        userTable.setItems(state.getUserData());
        // Listen for selection changes and show the person details when changed.
        userTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> state.showMessageBox(newValue));

    }

    public void deleteUser(User user) {
        userTable.getItems().remove(user);
    }

}
