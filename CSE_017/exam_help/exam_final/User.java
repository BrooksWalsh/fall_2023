/**
 * Class User to model a user of the Gym
 */
public class User {
	// Data members
	private String id;
	private String username;
	private String password;

	/**
	 * Constructor
	 * 
	 * @param id of the user
	 * @param un username of the user
	 * @param pw password of the user
	 */
	public User(String id, String un, String pw) {
		this.id = id;
		username = un;
		password = pw;
	}

	/**
	 * Method getID
	 * 
	 * @return the id of this user
	 */
	public String getID() {
		return id;
	}

	/**
	 * Method getUsername
	 * 
	 * @return the username of this user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Method getPassword
	 * 
	 * @return the password of this user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method setID
	 * 
	 * @param id the new id of this user
	 */
	public void setID(String id) {
		this.id = id;
	}

	/**
	 * Method setUsername
	 * 
	 * @param un the new username of this user
	 */
	public void setUsername(String un) {
		username = un;
	}

	/**
	 * Method setPassword
	 * 
	 * @param pw the new password of this user
	 */
	public void setPassword(String pw) {
		password = pw;
	}

	/**
	 * Method toString
	 * 
	 * @return a formatted string with the attributes of this user
	 */
	public String toString() {
		return id + "\t" + username + "\t" + password;
	}

	/**
	 * Method equals
	 * 
	 * @param o object to be compare to this user
	 * @return true if this user and o have identical usernames
	 */
	public boolean equals(Object o) {
		if (o instanceof User) {
			User u = (User) o;
			return u.username.equals(username);
		}
		return false;
	}
}
