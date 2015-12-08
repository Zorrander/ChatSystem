package chatsystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

    private final StringProperty name;
    private final StringProperty ipAdress;

    public User(String pseudo, String ipAdress) {
        this.name = new SimpleStringProperty(pseudo);
        this.ipAdress = new SimpleStringProperty(ipAdress);
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
}
