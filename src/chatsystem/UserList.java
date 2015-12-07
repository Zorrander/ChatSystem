package chatsystem;

import java.util.ArrayList;
import java.util.Observable ;

/** Implementation du pattern observer, avec l'extension de Observable */
public class UserList extends Observable {

		/** On garde le nombre d'utilisateur en ligne en permanence	*/
		public static int nbUser ;
		/** On crée une userlist null, on l'initialisera dans le constructeur */
		private static ArrayList<User> usersList = null ;
		
	
		
		/** Constructeur privé */
		private UserList() {
			nbUser = 0 ;
			usersList = new ArrayList<User>() ;
			//addObserver(ViewComponent) ;
			//setChanged() ;			
		}
		
		/** Implementation du pattern singleton pour éviter qu'il n'y ait plusieur listes	*/
		private static UserList instanceUserList = new UserList() ;
		
		/** Point d'accès sur l'unique instance UserList*/
		public static UserList getInstance() {	
			return instanceUserList;
		}
		
		public void addViewListener(User newUser) {
			usersList.add(newUser) ;
			nbUser++ ; 
			notifyObservers(instanceUserList) ;
		}
		
		public void deleteViewListener(User oldUser) {
			usersList.remove(oldUser) ;
			nbUser-- ;
			notifyObservers(instanceUserList) ;
		}
		
		
}

