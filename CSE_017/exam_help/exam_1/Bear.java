public class Bear extends Mammal{
    private int hibernation;

    public Bear(String t, String n, double w, int g, int h){
        super(t, n, w, g);
        hibernation = h;
    }
    public int getHibernation(){ return hibernation;}
    public void setHibernation(int h){ hibernation = h;}

    public String toString(){
        return String.format("%-15s\t%s\t%-10d", "Bear", super.toString(), hibernation);
    }
    public Object clone(){
        return new Bear(this.getTag(), this.getName(), this.getWeight(), this.getGestation(), this.hibernation);
    }
}