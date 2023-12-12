/**
 * Class to model a Gym class
 */
public class Class {
	// Data members
	private String code;
	private String name;
	private int time;
	private double fees;
	private String instructorID;
	private int members;

	/**
	 * Default constructor
	 * 
	 * @param c  the initial code of the class
	 * @param n  the name of the class
	 * @param t  the duration of the class in minutes
	 * @param f  the fees of the class in dollars
	 * @param id of the instructor of the class
	 */
	public Class(String c, String n, int t, double f, String id) {
		code = c;
		name = n;
		time = t;
		fees = f;
		instructorID = id;
		members = 0;
	}

	/**
	 * Method getCode
	 * 
	 * @return the code of the class
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Method getName
	 * 
	 * @return the name of the class
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method getTime
	 * 
	 * @return the duration, in minutes, of the class
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Method getFees
	 * 
	 * @return the cost of the class
	 */
	public double getFees() {
		return fees;
	}

	/**
	 * Method getInstructor
	 * 
	 * @return the if of the instructor
	 */
	public String getInstructor() {
		return instructorID;
	}

	/**
	 * Method getMembers
	 * 
	 * @return the number of people enrolled in the class
	 */
	public int getMembers() {
		return members;
	}

	/**
	 * Method setCode
	 * 
	 * @param c the new code of the class
	 */
	public void setCode(String c) {
		code = c;
	}

	/**
	 * Method setName
	 * 
	 * @param n the new name of the class
	 */
	public void setName(String n) {
		name = n;
	}

	/**
	 * Method setTime
	 * 
	 * @param t the new duration of the class
	 */
	public void setTime(int t) {
		time = t;
	}

	/**
	 * Method setFees
	 * 
	 * @param f the new fees of the class
	 */
	public void setFees(double f) {
		fees = f;
	}

	/**
	 * Method setInstructor
	 * 
	 * @param id of the new instructor of the class
	 */
	public void setInstructor(String id) {
		instructorID = id;
	}

	/**
	 * Method addMember
	 * increments the number of people enrolled in the class
	 */
	public void addMember() {
		members++;
	}

	/**
	 * Method toString
	 * 
	 * @return a formatted string with the attributes of the class
	 */
	public String toString() {
		String out;
		out = String.format("%-5s\t%-15s\t%-5d\t%-5.2f\t%-5d",
				code, name, time, fees, members);
		return out;
	}

	/**
	 * Method equals
	 * 
	 * @param o object to be compared to this class
	 * @return true if o has the same class id as this class
	 * @overrides equals from class Object
	 */
	public boolean equals(Object o) {
		if (o instanceof Class) {
			Class c = (Class) o;
			return code.equals(c.code);
		}
		return false;
	}
}
