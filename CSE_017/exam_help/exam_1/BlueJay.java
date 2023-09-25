public class BlueJay extends Bird{
    public BlueJay(String t, String n, double w, int inc, int fs){
        super(t, n, w, inc, fs);
    }
    public String toString(){
        return String.format("%-15s\t%s", "Blue Jay", super.toString());
    }
    public Object clone(){
        return new BlueJay(this.getTag(), this.getName(), this.getWeight(), this.getIncubation(), this.getFlyingSpeed());
    }
    public String flies(){
        return String.format("%-15s\t%-20s\t%-10d", "Blue Jay", getName(), getFlyingSpeed());
    }
}