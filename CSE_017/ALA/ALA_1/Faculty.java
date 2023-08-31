public class Faculty extends Employee{
    
    private String rank;

    public Faculty() {
        super();
        rank = "Assistant Professor";
    }

    public Faculty(int id, String name, String address, String phone, String email, String position, double salary, String rank) {
        super(id, name, address, phone, email, position, salary);
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return super.toString() + "Rank: " + rank + "\n";
    }
}
