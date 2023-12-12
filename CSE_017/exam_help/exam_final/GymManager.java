import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GymManager {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		// Read the list of classes in an array list
		LinkedList<Class> classes = new LinkedList<>();
		readClasses(classes, "classes.txt");
		// Read the list of users in a hash map
		// username is the key and the User object is the value
		HashMap<String, User> users = new HashMap<>(500);
		readUsers(users, "users.txt");

		// Read the list of members in a BST
		BST<Member> members = new BST<>();
		readMembers(members, "members.txt");
		// login interface (username and password)
		User user = null;
		try {
			user = login(keyboard, users);
		} catch (InputMismatchException e) {
			System.out.println("Invalid Username (must be 3 letters followed by 3 digits)");
		}
		if (user != null) {
			if (user.getID().startsWith("M")) {
				// Member menu (add/drop sessions)
				memberOperations(keyboard, user.getID(), members, classes);
			} else {
				// Administrator menu (popular classes/income)
				adminOperations(classes, members);
			}
		}
	}

	/**
	 * Method readClasses
	 * 
	 * @param list     linked list where the data read will be stored
	 * @param filename name of the text file to read from
	 */
	public static void readClasses(LinkedList<Class> list, String filename) {
		File file = new File(filename);
		Scanner readFile = null;
		try {
			readFile = new Scanner(file);
			while (readFile.hasNextLine()) {
				String line = readFile.nextLine();
				String[] tokens = line.split(" ");
				Class c = new Class(tokens[0], tokens[1],
						Integer.parseInt(tokens[2]),
						Double.parseDouble(tokens[3]),
						tokens[4]);
				list.add(c);
			}
			readFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File " + filename + " not found");
		}
	}

	/**
	 * Method readUsers
	 * 
	 * @param ht       the hashtable where the read data will be stored
	 * @param filename name of the text file to read from
	 */
	public static void readUsers(HashMap<String, User> hm, String filename) {
		try {
			Scanner read = new Scanner(new File(filename));
			while (read.hasNext()) {
				String userString = read.nextLine();
				String[] tokens = userString.split(" ");
				User input = new User(tokens[0], tokens[1], tokens[2]);
				hm.put(tokens[1], input);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

	/**
	 * Method readMembers
	 * 
	 * @param bst      the binary search tree where the data read will be stored
	 * @param filename name of the text file to read from
	 */
	public static void readMembers(BST<Member> bst, String filename) {
		File file = new File(filename);
		Scanner readFile = null;
		try {
			readFile = new Scanner(file);
			while (readFile.hasNextLine()) {
				String line = readFile.nextLine();
				String[] tokens = line.split(" ");
				String id = tokens[0];
				String name = tokens[1];
				Member m = new Member(id, name);
				for (int i = 2; i < tokens.length; i++) {
					m.addClass(tokens[i]);
				}
				bst.add(m);
			}
			readFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("File " + filename + " not found");
		}
	}

	/**
	 * Method to check the user credentials
	 * 
	 * @param scan  the scanner used to interact with the user
	 * @param users the hashtable with all the users credentials
	 */
	public static User login(Scanner scan, HashMap<String, User> users) {
		int count = 0;
		while (count < 3) {
			System.out.print("Enter username: ");
			String username = scan.next();
			if (!username.matches("[a-z]{3}[0-9]{3}")) {
				throw new InputMismatchException();
			}
			User u = users.get(username);
			if (u == null) {
				System.out.println("Username not found. Try again.");
			} else {
				System.out.print("\nEnter password: ");
				String password = scan.next();
				if (u.getPassword().equals(password)) {
					return u;
				} else {
					System.out.println("Password incorrect. Try again.");
				}
			}
			count++;
		}
		System.out.println("Incorrect username or password more than 3 times.");
		System.out.println("Terminating the program...");
		return null;
	}

	/**
	 * Method memberOperations
	 * 
	 * @param scan    the Scanner object used to interact with the user
	 * @param id      the id of the member for whom the operations are performed
	 * @param members the BST of the Gym members
	 * @param classes the linked list of the Gym classes
	 */
	public static void memberOperations(Scanner scan, String id,
			BST<Member> members, LinkedList<Class> classes) {
		int operation;
		Member m = members.find(new Member(id, ""));
		if (m == null) {
			System.out.println("ID: " + id + " not found.");
			System.exit(0);
		}
		do {
			ArrayList<String> myClasses = m.getClasses();
			System.out.println("Select an operation:");
			System.out.println("1: View My Classes");
			System.out.println("2: View All Classes");
			System.out.println("3: Add a Class");
			System.out.println("4: Drop a Class");
			System.out.println("5: Logout");
			operation = scan.nextInt();
			String userChoice = null;
			Class c = null;
			switch (operation) {
				case 1: // view my classes
					myClasses = m.getClasses();
					printMyClasses(classes, myClasses);
					System.out.println();
					break;

				case 2: // print all classes
					printAll(classes);
					System.out.println();
					break;

				case 5:// logout
					System.out.println("Thank you for your visit!");
					break;

				case 3:// add class
					printAll(classes);
					System.out.println("\nEnter the code of the class you want to add");
					scan.nextLine(); // clear junk
					userChoice = scan.nextLine();
					c = classes.find(new Class(userChoice, "", 0, 0.0, ""));
					if (classes.contains(c) && !m.getClasses().contains(userChoice)) {
						// if class is in Classes list and not already added. add class
						m.addClass(userChoice);
						System.out.println("Class added successfully.");
					} else {
						System.out.println("Class code invalid or already signed up for class");
					}
					System.out.println();
					break;

				case 4: // drop class
					printAll(classes);
					System.out.println("\nEnter the code of the class you want to drop");
					scan.nextLine(); // clear junk
					userChoice = scan.nextLine();
					c = classes.find(new Class(userChoice, "", 0, 0.0, ""));
					if (classes.contains(c) && m.getClasses().contains(userChoice)) {
						// if class is in Classes list and in members classes. remove class
						m.removeClass(userChoice);
						System.out.println("Class dropped successfully.");
					} else {
						System.out.println("Class code invalid or not signed up for class");
					}
					System.out.println();
					break;

			}
		} while (operation != 5);
	}

	/**
	 * Method printAll the classes
	 * 
	 * @param classes the list of classes
	 */
	public static void printAll(LinkedList<Class> classes) {
		System.out.printf("%-5s\t%-20s\t%-20s\t$%s\n", "Code", "Name", "Duration(minutes)", "Fees");
		Iterator<Class> iter = classes.iterator();
		while (iter.hasNext()) {
			Class c = iter.next();
			System.out.printf("%-5s\t%-20s\t%-20d\t$%-5.2f\n",
					c.getCode(), c.getName(), c.getTime(), c.getFees());
		}
	}

	/**
	 * Method printMyClasses
	 * 
	 * @param classes   the list of classes
	 * @param myClasses the list of the member classes to print
	 */
	public static void printMyClasses(LinkedList<Class> classes,
			ArrayList<String> myClasses) {
		System.out.printf("%-5s\t%-20s\t%-20s\t$%s\n", "Code", "Name", "Duration(minutes)", "Fees");
		Iterator<String> it = myClasses.iterator();
		while (it.hasNext()) {
			String code = it.next();
			Class c = classes.find(new Class(code, "", 0, 0.0, ""));
			System.out.printf("%-5s\t%-20s\t%-20d\t$%-5.2f\n",
					c.getCode(), c.getName(), c.getTime(), c.getFees());
		}
	}

	/**
	 * Method adminOperations to print the Gym income and the classes ranked by
	 * popularity
	 * 
	 * @param classes the list classes
	 * @param members the bst with all the Gym members
	 */
	public static void adminOperations(LinkedList<Class> classes, BST<Member> members) {
		printIncome(classes, members);
		sortClasses(classes, members);
	}

	/**
	 * Method printIncome to print the income from each member and the total income
	 * 
	 * @param classes the list of classes
	 * @param bst     the bst with all the Gym members
	 */
	// Time Complexity: O(n * m)
	// - Each member in the BST (n) must be iterated through
	// - - For each member, iterate through each class (m)
	// -> approaches linear time as member list can grow much larger than class list
	// for each member.
	public static void printIncome(LinkedList<Class> classes, BST<Member> bst) {
		ArrayList<Member> list = bst.toList();
		int totalIncome = 0;
		System.out.println();
		System.out.printf("%-10s\t%-15s\t%-20s\t%s\n", "Member ID", "Member name", "Classes", "Amount");
		for (int i = 0; i < list.size(); i++) {
			Member temp = list.get(i);
			// find member income
			int memberIncome = 0;
			for (String courseString : temp.getClasses()) {
				Class c = classes.find(new Class(courseString, "", 0, 0.0, ""));
				memberIncome += c.getFees();
			}
			totalIncome += memberIncome;
			System.out.printf("%-5s\t%-20s\t%-20s\t$%-5d\n", temp.getID(), temp.getName(), temp.getClasses(),
					memberIncome);
		}
		System.out.printf("%-40s\t\t$%d\n", "Total Income:", totalIncome);
	}

	/**
	 * Method sortClasses to print the list of classes ranked by popularity
	 * 
	 * @param classes the list fo classes
	 * @param bst     the BST with all the Gym members
	 * 
	 */
	// Time Complexity: O((n * m) + p^2 + k)
	// - for each member in BST list of members (n)
	// - - iterate through each members class list (m)
	// - bubble sort method has average time complexity on random elements of p^2
	// - for each original class we print it (k)
	public static void sortClasses(LinkedList<Class> classes, BST<Member> bst) {
		class ComparatorByMember implements Comparator<Class> {
			public int compare(Class c1, Class c2) {
				return c2.getMembers() - c1.getMembers();
			}
		}
		/********* Write your code for sortClasses here *********/
		ArrayList<Member> list = bst.toList();
		for (Member m : list) { // iterate through each member
			ArrayList<String> memberClasses = m.getClasses();
			for (String courseCode : memberClasses) { // for each course given a member
				Class c = classes.find(new Class(courseCode, "", 0, 0.0, ""));
				c = classes.find(c); // find value in Classes list
				c.addMember(); // increment member count
			}
		}

		classes.sort(new ComparatorByMember());

		/********* Print the sorted list here *********/
		Iterator<Class> iter = classes.iterator();
		System.out.printf("%-20s\t%s", "Class", "# Enrolled Members\n");
		while (iter.hasNext()) {
			Class c = iter.next();
			System.out.printf("%-20s\t%d\n", c.getName(), c.getMembers());
		}
	}
}
