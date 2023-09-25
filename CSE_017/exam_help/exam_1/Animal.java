public abstract class Animal implements Cloneable, Comparable<Animal> {

    private String tag;
    private String name;
    private double weight;

    protected Animal(String t, String n, double w) {
        tag = t;
        name = n;
        weight = w;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public String getTag() {
        return tag;
    }

    public void setName(String n) {
        name = n;
    }

    public void setWeight(double w) {
        weight = w;
    }

    public void setTag(String t) {
        tag = t;
    }

    public abstract Object clone();

    public String toString() {
        return String.format("%-10s\t%-20s\t%-10s", tag, name, formatWeight());
    }

    private String formatWeight() {
        String s = "";
        if (weight < 1000) {
            s = String.format("%10.2fg", weight);
        } else {
            s = String.format("%10.2fKg", weight / 1000);
        }
        return s;
    }

    public int compareTo(Animal animal) {
        if (weight == animal.weight) {
            return 0;
        } else if (weight > animal.weight) {
            return 1;
        } else {
            return -1;
        }
    }
}