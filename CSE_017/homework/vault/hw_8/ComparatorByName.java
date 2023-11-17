import java.util.Comparator;

/**
 * ComparatorByName defines compare to order Student objects based on the name
 * data member.
 * 
 * @since 2023-11-17
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class ComparatorByName implements Comparator<Student> {
    /**
     * Uses compareTo() for Strings to order Student objects by name.
     * 
     * @param s1
     * @param s2
     * @return
     */
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
}
