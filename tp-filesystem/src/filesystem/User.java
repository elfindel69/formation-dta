package filesystem;

/**
 * 
 */
public class User {

	/**
	 * Default constructor
	 */
	public User() {
	}

	public User(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * 
	 */
	private String login;

}