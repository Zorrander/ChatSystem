package chatsystem;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
            return selectedUser ;
        } else {
         // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(Chat.window);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Person Selected");
        alert.setContentText("Please select a person in the table.");

        alert.showAndWait();
        return null ;
        }
    }
     
     public void setState(ConnectState state) {
        this.state=state;         
    }
     
     public void setInteraction() {
          userTable.setItems(state.getUserData());
        // Listen for selection changes and show the person details when changed.
        userTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> state.showMessageBox(newValue));
        
        
     }

}
