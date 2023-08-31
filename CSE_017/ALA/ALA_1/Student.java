public class Student extends Person {
    private String major;

    public Student() {
        super();
        major = "Undeclared";
    }

    public Student(int id, String name, String address, String phone, String email, String major) {
        super(id, name, address, phone, email);
        this.major = major;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Major: %s\n", major);
    }
}
