/**
 * The Patient class is meant to simulate a hospital Patient, perhaps in an
 * emergency room. The Patient object holds a name, age and type. The "type"
 * data member allows for ordering and can be thought of as a "priority value".
 * 
 * @since 2023-11-9
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Patient implements Comparable<Patient> {
    private String name;
    private int age, type;

    /**
     * 3-arg constructor of the Patient class creates a Patient object with a
     * specific name, age, and type.
     * 
     * @param name
     * @param age
     * @param type
     */
    public Patient(String name, int age, int type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    /**
     * Getter method for the name data member.
     * 
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the age data member.
     * 
     * @return int age
     */
    public int getAge() {
        return age;
    }

    /**
     * Getter method for the priority type data member.
     * 
     * @return int type
     */
    public int getType() {
        return type;
    }

    /**
     * Setter method for the name data member.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter method for the age data member.
     * 
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Setter method for the type data member.
     * 
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Returns a formatted String that represents the Patient object within
     * parentheses.
     * 
     * @return formatted String
     */
    @Override
    public String toString() {
        return String.format("(%-20s %-3d %-1d)", name, age, type);
    }

    /**
     * Returns true if the compared Patient object has the same name data member as
     * the object this method is called on.
     * 
     * @param o Object for comparison
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Patient) {
            Patient p = (Patient) o;
            return this.name.equals(p.name);
        } else {
            return false;
        }
    }

    /**
     * Returns a positive number if "this" Patient has a greater value for their
     * type variable than the argument's. Returns a negative number if "this"
     * Patient's type data member is less than the argument's. Returns 0 if they
     * both have the same value for the type data member. (Type 0 has highest
     * priority)
     * 
     * @param p Patient for comparison
     * @return int
     */
    @Override
    public int compareTo(Patient p) {
        return this.type - p.type;
    }
}
