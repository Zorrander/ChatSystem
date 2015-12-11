package chatsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Implementation du pattern observer, avec l'extension de Observable
 */
public class UserList {

    /**
     * On garde le nombre d'utilisateur en ligne en permanence
     */
    public static int nbUser;
    /**
     * On crée une userlist null, on l'initialisera dans le constructeur
     */
    private static ObservableList<User> usersList = null;

    /**
     * Constructeur privé
     */
    private UserList() {
        nbUser = 0;
        usersList = FXCollections.observableArrayList();

        usersList.add(new User("Kévin", "10.10.255.255"));
        usersList.add(new User("Alexis", "10.20.255.255"));
        usersList.add(new User("Alexandre", "10.10.255.255"));
    }

    /**
     * Implementation du pattern singleton pour éviter qu'il n'y ait plusieur
     * listes
     */
    private static UserList instanceUserList = new UserList();

    /**
     * Point d'accès sur l'unique instance UserList
     */
    public static UserList getInstance() {
        return instanceUserList;
    }

    public void addViewListener(User newUser) {
        usersList.add(newUser);
        nbUser++;
    }

    public void deleteViewListener(User oldUser) {
        usersList.remove(oldUser);
        nbUser--;
    }
    
    public User getUser(User newUser) {
        for (User iterator : usersList) {
            if (iterator.toString() == newUser.toString()) {
                return iterator;
            }
        }
        // If we haven't found anything
        return null ;
    }

    /**
     * Returns the data as an observable list of Persons.
     *
     * @return
     */
    public ObservableList<User> getUserData() {
        return usersList;
    }

}
