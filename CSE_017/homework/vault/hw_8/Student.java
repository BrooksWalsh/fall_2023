/**
 * Student objects have an ID number, Name, and GPA. The natural ordering of
 * students is by ID number.
 * 
 * @since 2023-11-17
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Student implements Comparable<Student> {

    // data members
    int id;
    String name;
    double gpa;

    /**
     * No-arg constructor of Student object creates a "null" student with default
     * data members.
     */
    public Student() {
        this(0, "n/a", 0);
    }

    /**
     * 3- arg constructor of Student object creates a student with a specified ID
     * number, Name, and gpa.
     * 
     * @param id
     * @param name
     * @param gpa
     */
    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    /**
     * Getter method for student ID number data member.
     * 
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Getter method for student name data member.
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for student GPA data member.
     * 
     * @return double
     */
    public double getGPA() {
        return gpa;
    }

    /**
     * Setter method for student ID number data member.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter method for student name data member.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for student gpa data member.
     * 
     * @param gpa
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    /**
     * Returns a formatted string with ID number, Name, and GPA of Student object in
     * a single line.
     */
    @Override
    public String toString() {
        return String.format("%-13d\t%-26s\t%1.2f", id, name, gpa);
    }

    /**
     * Defines natural ordering for student object by comparing ID numbers.
     * 
     * @param argStudent
     * @return int
     */
    @Override
    public int compareTo(Student argStudent) {
        return ((Integer) id).compareTo(((Integer) argStudent.getId()));
    }
}
