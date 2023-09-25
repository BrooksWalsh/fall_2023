import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) {
        Animal[] animals = new Animal[30];
        int count = readAnimals(animals, "animals.txt");
        if (count == 0) {
            System.out.println("No data read from the file animals.txt");
        } else {
            System.out.println("File animals.txt read successfully");
        }
        // cloning some animals
        animals[count++] = cloneAnimal(animals, count);
        animals[count++] = cloneAnimal(animals, count);
        animals[count++] = cloneAnimal(animals, count);
        animals[count++] = cloneAnimal(animals, count);

        // Display the list of animals
        System.out.println("List of Animals");
        System.out.printf("%-15s\t%-10s\t%-20s\t%10s\n", "Type", "Tag", "Name", "Weight");
        for (int i = 0; i < count; i++) {
            System.out.println(animals[i]);
        }

        // Display the animals that can fly
        System.out.println("\nList of Flying Animals");
        printFlying(animals, count);

        // Display the list of animals sorted by weight
        System.out.println("\nList of Animals sorted by weight");
        sortAnimals(animals, count);

        // Display the list of mammmals
        System.out.println("\nList of Mammals");
        printMammals(animals, count);

        // find an animal with a specific tag
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a tag:");
        String tag = keyboard.next();
        if (checkTag(tag)) {
            int index = findAnimal(animals, count, tag);
            if (index == -1) {
                System.out.println("No animal found with tag: " + tag);
            } else {
                System.out.println("Animal found: " + animals[index]);
            }
        }

    }

    public static int findAnimal(Animal[] list, int size, String tag) {
        for (int i = 0; i < size; i++) {
            if (list[i].getTag().equals(tag)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean checkTag(String tag) {
        try {
            if (tag.matches("[A-z]{2}-\\d{4}")) {
                return true;
            } else {
                throw new Exception(tag);
            }
        } catch (Exception e) {
            System.out.println("Invalid TAG: " + e.getMessage() + ". Must have 2 letters followed by 4 digits");
            return false;
        }
    }

    public static int readAnimals(Animal[] list, String filename) {
        File file = new File(filename);
        int inputCount = 0;
        Scanner fileInput = null;

        try {
            fileInput = new Scanner(file);
            while (fileInput.hasNextLine()) {
                String tempInput = fileInput.nextLine();
                String[] tempArgs = tempInput.split(",");
                Animal tempAnimal = null;

                if (tempArgs[0].equals("Bear")) {
                    tempAnimal = new Bear(tempArgs[1], tempArgs[2], Double.parseDouble(tempArgs[3]),
                            Integer.parseInt(tempArgs[4]), Integer.parseInt(tempArgs[5]));
                } else if (tempArgs[0].equals("Bat")) {
                    tempAnimal = new Bat(tempArgs[1], tempArgs[2], Double.parseDouble(tempArgs[3]),
                            Integer.parseInt(tempArgs[4]), Integer.parseInt(tempArgs[5]));
                } else if (tempArgs[0].equals("Blue Jay")) {
                    tempAnimal = new BlueJay(tempArgs[1], tempArgs[2], Double.parseDouble(tempArgs[3]),
                            Integer.parseInt(tempArgs[4]), Integer.parseInt(tempArgs[5]));
                } else if (tempArgs[0].equals("Humming Bird")) {
                    tempAnimal = new HummingBird(tempArgs[1], tempArgs[2], Double.parseDouble(tempArgs[3]),
                            Integer.parseInt(tempArgs[4]), Integer.parseInt(tempArgs[5]));
                }

                if (tempAnimal != null) {
                    list[inputCount] = tempAnimal;
                    inputCount++;
                }
            }
        } catch (FileNotFoundException e) {
            // if file isn't found, array stays empty
            System.out.println("File not found. Animal array cannot be initialized.\n");
        } finally {
            // fileInput ~should~ never be null here but I wanted to be sure
            if (fileInput != null) {
                fileInput.close();
            }
        }
        return inputCount;
    }

    public static void sortAnimals(Animal[] list, int size) {
        System.out.printf("%-15s\t%-10s\t%-20s\t%10s\n", "Type", "Tag", "Name", "Weight");
        java.util.Arrays.sort(list, 0, size);
        for (int i = 0; i < size; i++) {
            System.out.println(list[i]);
        }
    }

    public static void printMammals(Animal[] list, int size) {
        System.out.printf("%-15s\t%-10s\t%-20s\t%10s\t%-10s\n", "Type", "Tag", "Name", "Weight", "Gestation");
        for (int i = 0; i < size; i++) {
            Animal tempAnimal = list[i];
            if (tempAnimal instanceof Bear) {
                Bear tempBear = (Bear) tempAnimal;
                System.out.println(tempBear.toString());
            } else if (tempAnimal instanceof Bat) {
                Bat tempBat = (Bat) tempAnimal;
                System.out.println(tempBat.toString());
            }
        }
    }

    public static void printFlying(Animal[] list, int size) {
        System.out.printf("%-15s\t%-20s\t%s\n", "Type", "Name", "Flying Speed (mph)");
        for (int i = 0; i < size; i++) {
            Animal tempAnimal = list[i];
            if (tempAnimal instanceof BlueJay) {
                BlueJay tempBlueJ = (BlueJay) tempAnimal;
                System.out.println(tempBlueJ.flies());
            } else if (tempAnimal instanceof Bat) {
                Bat tempBat = (Bat) tempAnimal;
                System.out.println(tempBat.flies());
            } else if (tempAnimal instanceof HummingBird) {
                HummingBird tempHum = (HummingBird) tempAnimal;
                System.out.println(tempHum.flies());
            }
        }
    }

    public static Animal cloneAnimal(Animal[] list, int size) {
        int index = (int) (Math.random() * (size - 1));
        Animal a = null;
        if (list[index] instanceof BlueJay) {
            a = (BlueJay) (list[index].clone());
            ((BlueJay) a).setFlyingSpeed(((Bird) a).getFlyingSpeed() * 2);
        } else if (list[index] instanceof Bat) {
            a = (Bat) (list[index].clone());
            a.setName("Natalidae");
        } else if (list[index] instanceof Bear) {
            a = (Bear) (list[index].clone());
            ((Bear) a).setHibernation(6);
        } else {
            a = (Animal) (list[index].clone());
            a.setWeight(a.getWeight() + 2);
        }
        return a;
    }
}