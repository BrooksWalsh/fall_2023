import java.util.Comparator;

/**
 * ComparatorByGPA defines compare to order Student objects based on the GPA
 * data member.
 * 
 * @since 2023-11-17
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class ComparatorByGPA implements Comparator<Student> {

    /**
     * Uses compareTo() for Doubles to order students by GPA.
     * 
     * @param s1
     * @param s2
     * @return
     */
    @Override
    public int compare(Student s1, Student s2) {
        return ((Double) s1.getGPA()).compareTo(((Double) s2.getGPA()));
    }
}
