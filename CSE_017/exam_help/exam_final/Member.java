import java.util.ArrayList;

/**
 * Class Member to model a member of the Gym
 */
public class Member implements Comparable<Member> {
	// Data members
	private String id;
	private String name;
	private ArrayList<String> classes;

	/**
	 * Constructor
	 * 
	 * @param id   the id of the member
	 * @param name the name of the member
	 *             creates an empty list of the class codes the member is enrolled
	 *             in
	 */
	public Member(String id, String name) {
		this.id = id;
		this.name = name;
		classes = new ArrayList<>();
	}

	/**
	 * Method getID
	 * 
	 * @return the if od this member
	 */
	public String getID() {
		return id;
	}

	/**
	 * Method getName
	 * 
	 * @return the name of this member
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method setID
	 * 
	 * @param id the new id of this member
	 */
	public void setID(String id) {
		this.id = id;
	}

	/**
	 * Method setName
	 * 
	 * @param n the new name of this member
	 */
	public void setName(String n) {
		name = n;
	}

	/**
	 * Method findClass
	 * 
	 * @param c the code of the class being searched in this member's list of class
	 *          codes
	 * @return true if a class with code c is found, false otherwise
	 */
	public boolean findClass(String c) {
		return classes.contains(c);
	}

	/**
	 * Method addClass
	 * 
	 * @param c the code of the class to be added to this member's list of class
	 *          codes
	 */
	public void addClass(String c) {
		classes.add(c);
	}

	/**
	 * Method removeClass
	 * 
	 * @param c the code of the class to be removed from this member's list of class
	 *          codes
	 */
	public void removeClass(String c) {
		classes.remove(c);
	}

	/**
	 * Method getClasses
	 * 
	 * @return the list of class codes this member is enrolled in
	 */
	public ArrayList<String> getClasses() {
		return classes;
	}

	/**
	 * Method toString
	 * 
	 * @return a formatted string with the id, name, and list of class code of this
	 *         member
	 */
	public String toString() {
		String out = String.format("%-10s\t%-20s\t%-20s",
				id, name, classes);
		return out;
	}

	/**
	 * Method compareTo
	 * 
	 * @param m the member being compared to this member
	 * @return 0 if this member has the same id as the member m
	 *         >0 if this member's id is after m's id
	 *         <0 if this member's is if before m's id
	 */
	public int compareTo(Member m) {
		return id.compareTo(m.id);
	}

	/**
	 * Method equals
	 * 
	 * @param o the object being compared to this member
	 * @return true if this member and o have identical ids, false otherwise
	 */
	public boolean equals(Object o) {
		if (o instanceof Member) {
			Member m = (Member) o;
			return id.equals(m.id);
		}
		return false;
	}
}
