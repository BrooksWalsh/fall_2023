public abstract class Mammal extends Animal{
    private int gestation;
    protected Mammal(String t, String n, double w, int g){
        super(t, n, w);
        gestation = g;
    }
    public int getGestation(){
        return gestation;
    }
    public void setGestation(int g){
        gestation = g;
    }
    public String toString(){
        return String.format("%s\t%-10d", super.toString(), gestation);
    }
}