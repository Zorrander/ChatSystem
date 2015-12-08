package chatsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class UserListViewController extends ViewComponent{
    
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
    private Chat mainChat ;
    
       /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public UserListViewController() {
    }
    
    private void showMessageBox(User user) {
    if (user != null) {
        
    } else {
        
    }
}
    
     /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    
    @FXML
    private void initialize() {
        // Initialize the userList with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        ipAdressColumn.setCellValueFactory(cellData -> cellData.getValue().getAdressProperty());
        
        // Listen for selection changes and show a chat box.
          userTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showMessageBox(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * @param mainChat
     */
    public void setMainChat(Chat mainChat) {
        this.mainChat = mainChat;

        // Add observable list data to the table
        userTable.setItems(mainChat.getUserData());
    }
    
}
