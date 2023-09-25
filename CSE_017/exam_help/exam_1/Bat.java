public class Bat extends Mammal implements CanFly{
    private int flyingSpeed;
    public Bat(String t, String n, double w, int g, int fs){
        super(t, n, w, g);
        flyingSpeed = fs;
    }
    public int getFlyingSpeed(){ return flyingSpeed;}
    public void setFlyingSpeed(int fs){ flyingSpeed = fs;}

    public String toString(){
        return String.format("%-15s\t%s\t%-10d","Bat", super.toString(), flyingSpeed);
    }
    // deep-copy clone
    public Object clone(){
        return new Bat(this.getTag(), this.getName(), this.getWeight(), this.getGestation(), this.flyingSpeed);
    }
    public String flies(){
        return String.format("%-15s\t%-20s\t%-10d", "Bat", getName(), getFlyingSpeed());
    }
}