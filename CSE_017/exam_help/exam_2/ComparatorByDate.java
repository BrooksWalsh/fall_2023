import java.util.Comparator;

public class ComparatorByDate implements Comparator<Message> {
    
    public int compare(Message m1, Message m2) {
        Date m1Date = m1.getDate();
        Date m2Date = m2.getDate();

        return m1Date.compareTo(m2Date);
    }
}
