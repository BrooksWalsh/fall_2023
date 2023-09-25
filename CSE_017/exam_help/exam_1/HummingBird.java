public class HummingBird extends Bird{
    public HummingBird(String t, String n, double w, int inc, int fs){
        super(t, n, w, inc, fs);
    }
    public String toString(){
        return String.format("%-15s\t%s","HummingBird", super.toString());
    }
    public Object clone(){
        return new HummingBird(this.getTag(), this.getName(), this.getWeight(), this.getIncubation(), this.getFlyingSpeed());
    }
    public String flies(){
        return String.format("%-15s\t%-20s\t%-10d", "HummingBird", getName(), getFlyingSpeed());
    }
}