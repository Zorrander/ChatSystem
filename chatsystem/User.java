
public class User {

	private String name ;
	private String ipAdress ;
	
	public User(String pseudo, String ipAdress) {
		this.name=pseudo ;
		this.ipAdress=ipAdress;
	}
	
	public String getName() {
		return this.name ;
	}
	
	public String getAdress() {
		return this.ipAdress ;
	}
}
