package chatsystem;

import common.Message;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {

    private final StringProperty name;
    private final StringProperty ipAdress;
    private ObservableList<Message> messageList = null;

    public User(String pseudo, String ipAdress) {
        this.name = new SimpleStringProperty(pseudo);
        this.ipAdress = new SimpleStringProperty(ipAdress);
        this.messageList = FXCollections.observableArrayList();
    }

    public ObservableList<Message> getMessageList() {
        return messageList;
    }

    public void addMessage(Message newMessage) {

        this.messageList.add(newMessage);
    }

    public void clearMessage() {
        this.messageList.clear();
    }

    // Property getters
    public StringProperty getNameProperty() {
        return this.name;
    }

    public StringProperty getAdressProperty() {
        return this.ipAdress;
    }

    // String getters
    public String getName() {
        return this.name.get();
    }

    public String getAdress() {
        return this.ipAdress.get();
    }

    // String setters
    public void setName(String newName) {
        this.name.set(newName);
    }

    public void setAdress(String newIpAdress) {
        this.name.set(newIpAdress);
    }

    @Override
    public String toString() {
        return this.name.get() + " " + this.ipAdress.get();
    }
}
