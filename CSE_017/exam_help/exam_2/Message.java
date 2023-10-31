public class Message implements Comparable<Message> {
    private Date date;
    private String text;
    private String sender;
    private String recipient;

    public Message(Date d, String t, String s, String r) {
        date = d;
        text = t;
        sender = s;
        recipient = r;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setDate(Date d) {
        date = d;
    }

    public void setText(String t) {
        text = t;
    }

    public void setSender(String s) {
        sender = s;
    }

    public void setRecipient(String r) {
        recipient = r;
    }

    public String toString() {
        return String.format("%s\t%-30s\t%-10s\t%-10s", date.toString(), text, sender, recipient);
    }

    public int compareTo(Message m) {
        return this.sender.compareTo(m.sender);
    }

    public boolean equals(Object o) {
        if (o instanceof Message) {
            Message m = (Message) o;
            return this.sender.equals(m.sender);
        }
        return false;
    }

}