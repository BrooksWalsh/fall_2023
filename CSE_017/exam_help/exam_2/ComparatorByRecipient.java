import java.util.Comparator;

public class ComparatorByRecipient implements Comparator<Message> {
    
    public int compare(Message m1, Message m2) {
        String m1Recipient = m1.getRecipient();
        String m2Recipient = m2.getRecipient();

        return m1Recipient.compareTo(m2Recipient);
    }
}
