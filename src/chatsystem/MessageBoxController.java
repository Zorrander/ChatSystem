package chatsystem;

import static chatsystem.Chat.window;
import common.Message;
import static common.Message.MsgType.TEXT_MESSAGE;
import common.MessageEnvoye;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import static javafx.stage.StageStyle.UTILITY;

public class MessageBoxController {

    @FXML
    private ListView<Message> messageTable;

    @FXML
    private TextArea prompter;
    @FXML
    private Button sendButton;
    @FXML
    private Button disconnectButton;
    @FXML
    private Button fileButton;
    @FXML
    private CheckBox checkFile;

    Message newMessage;

    private User user;

    private ConnectState state;

    public MessageBoxController() {
    }

    @FXML
    private void initialize() {
    }

    @FXML
    public void send() throws IOException {
        String textToSend = prompter.getText();
        newMessage = new Message(TEXT_MESSAGE, textToSend, state.getId());
        prompter.clear();
        user.addMessage(new MessageEnvoye(TEXT_MESSAGE, textToSend, state.getId()));
        state.getController().send(newMessage, user.getAdress());

        if (checkFile.isSelected()){
           state.getController().sendFileRequest(user.getAdress());
           //réinitialisation checkBox
           checkFile.setSelected(false);
           checkFile.setText("Aucun fichier");         

        }
    }

    @FXML
    public void chooseFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("File to send");
        final File selectedFile = fileChooser.showOpenDialog(window);
        this.memorizeFile(selectedFile);
    }

    public void memorizeFile(File file) {
        checkFile.setSelected(true);
        checkFile.setText(file.getName());
        state.getController().setCurrentFile(file);
    }

    @FXML
    public void areYouSure() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.initStyle(UTILITY);
        alert.setTitle("Disconnect");       
        alert.setHeaderText(null);
        alert.setContentText("Are you OK with disconnecting ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            this.disconnect() ;
        } else {
            // ... user chose CANCEL or closed the dialog
            // Do nothing
        }
    }

    public void disconnect() {
        state.disconnect();
    }

    public void setEnterAction() {
        prompter.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    try {
                        send();
                    } catch (IOException ex) {
                        Logger.getLogger(DisconnectViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public void setState(ConnectState state) {
        this.state = state;
    }

    void setCurrentUser(User user) {
        this.user = user;
        messageTable.setItems(user.getMessageList());
    }

}
