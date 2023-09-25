public abstract class Bird extends Animal implements CanFly{
    private int incubation;
    private int flyingSpeed;
    

    protected Bird(String t, String n, double w, int inc, int fs){
        super(t, n, w);
        flyingSpeed = fs;
        incubation = inc;
    }
    public int getFlyingSpeed(){
        return flyingSpeed;
    }
    public int getIncubation(){
        return incubation;
    }
    public void setFlyingSpeed(int fs){ flyingSpeed = fs;}
    public void setIncubation(int inc) { incubation = inc;}

    public String toString(){
        return String.format("%s\t%-10d\t%-10d", super.toString(),  incubation, flyingSpeed);
    }
}