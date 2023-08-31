public class Employee extends Person {

    private String position;
    private double salary;

    public Employee() {
        super();
        position = "none";
        salary = 0.0;
    }

    public Employee(int id, String name, String address, String phone, String email, String position, double salary) {
        super(id, name, address, phone, email);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Position: %s\nAnnual Salary: $%.2f\n", position, salary);
    }
}
