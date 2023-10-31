import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        List<Message>[] messages = new List[2];
        messages[0] = new ArrayList<Message>();
        messages[1] = new LinkedList<Message>();

        readMessages(messages[0], "list1.txt");
        readMessages(messages[1], "list2.txt");

        Date d = new Date("01/26/2022");
        List<Message> list = findMessages(messages[0], d);
        if (list == null) {
            System.out.println("No messages found for the date " + d);
        } else {
            System.out.println(list.size() + " messages found for the date " + d + ":");
            print(list);
            System.out.println();
        }
        d = new Date("01/22/2022");
        list = findMessages(messages[1], d);
        if (list == null) {
            System.out.println("No messages found for the date " + d);
        } else {
            System.out.println(list.size() + " messages found for the date " + d + ":");
            print(list);
            System.out.println();
        }

        List<Message> unionList = new ArrayList<>();
        union(messages[0], messages[1], unionList);
        System.out.println("Union of the two lists:");
        print(unionList);
        System.out.println();

        List<Message> intersectionList = new LinkedList<>();
        intersection(messages[0], messages[1], intersectionList);
        System.out.println("Intersection of the two lists:");
        print(intersectionList);
        System.out.println();

        List<Message> unionNoDuplicatesList = new ArrayList<>();
        unionNoDuplicates(messages[0], messages[1], unionNoDuplicatesList);
        System.out.println("Union of the two lists without duplicates:");
        print(unionNoDuplicatesList);
        System.out.println();

        unionList.sort(null);
        System.out.println("Union List sorted by sender:");
        print(unionList);
        System.out.println();
        unionList.sort(new ComparatorByRecipient());
        System.out.println("Union List sorted by recipient:");
        print(unionList);
        System.out.println();
        unionList.sort(new ComparatorByDate());
        System.out.println("Union List sorted by date:");
        print(unionList);
        System.out.println();
    }

    public static void readMessages(List<Message> list, String filename) {
        try {
            Scanner read = new Scanner(new File(filename));
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] tokens = line.split(",");
                Date date = new Date(tokens[0]);
                list.add(new Message(date, tokens[1], tokens[2], tokens[3]));
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File \"" + filename + "\" not found");
        }
    }

    // Time Complexity: O(n * m)
    // n := iterations for list1
    // m := iterations for list2
    // The time complexity of this method is determined by the two while loops. Each
    // while loop iterates through every item in its given list (n & m), but it
    // cannot be assumed that the two lists have the same amount of items.

    // combines the elements from list1 and list 2 into list
    public static <E> void union(List<E> list1, List<E> list2, List<E> list) {
        Iterator<E> iter1 = list1.iterator();
        Iterator<E> iter2 = list2.iterator();
        while (iter1.hasNext()) {
            list.add(iter1.next());
        }
        while (iter2.hasNext()) {
            list.add(iter2.next());
        }
    }

    // Time Complexity: O(n * m * p)
    // n := iterations for list1
    // m := iterations for list2
    // p := iterations for list searching for duplicates
    // The time complexity of this method is determined by the 3 while loops. Each
    // while loop runs over a different list, so the amount of iterations cannot be
    // simplified in the big-O notation.

    // the intersection of list1 and list2 in list
    public static <E> void intersection(List<E> list1, List<E> list2, List<E> list) {
        Iterator<E> iter1 = list1.iterator();

        // adds items in list 1 that are also in list2
        while (iter1.hasNext()) {
            Iterator<E> iter2 = list2.iterator();
            E list1Comp = iter1.next();
            while (iter2.hasNext()) {
                E list2Comp = iter2.next();
                Iterator<E> iter3 = list.iterator();
                boolean notDuplicate = true;
                // ensure to not add duplicates (similar to .contains())
                while (iter3.hasNext()) {
                    if (list1Comp.equals(iter3.next())) {
                        notDuplicate = false;
                        break;
                    }
                }

                if (list1Comp.equals(list2Comp) && notDuplicate) {
                    list.add(list1Comp);
                }
            }
        }
    }

    // Time Complexity: O(n * m * p)
    // n := iterations for list1
    // m := iterations for list2
    // p := iterations for list1 searching for duplicates
    // The time complexity of this method is determined by the 3 while loops. 2 of
    // the while loops iterate through different lists; however, the one duplicate
    // list iteration is truncated because it is only searching until a duplicate is
    // found. Therefore the big-O notation cannot be simplified.

    // combines list1 and list2 in list without duplicates
    public static <E> void unionNoDuplicates(List<E> list1, List<E> list2, List<E> list) {
        Iterator<E> iter1 = list1.iterator();
        Iterator<E> iter2 = list2.iterator();

        // add all of list 1
        while (iter1.hasNext()) {
            list.add(iter1.next());
        }

        // add list 2 but only non-duplicates
        while (iter2.hasNext()) {
            iter1 = list1.iterator();
            E itemToAdd = iter2.next();
            boolean addingItem = true;
            // compare with all elements of list1
            while (iter1.hasNext()) {
                E compareItem = iter1.next();
                if (itemToAdd.equals(compareItem)) {
                    addingItem = false;
                    break;
                }
            }
            if (addingItem) {
                list.add(itemToAdd);
            }
        }
    }

    // Time Complexity: O(n)
    // n := number of elements in the list
    // This time complexity is determined by the amount of recursive calls within
    // the method. The worst case complexity (big-O) is linear because it will
    // iterate through each item in the given list once.

    // returns the list of messages sent at the date d, null if no messages were
    // found
    public static ArrayList<Message> findMessages(List<Message> list, Date d) {
        Iterator<Message> iter = list.iterator(); // create iterator
        ArrayList<Message> output = new ArrayList<>(); // create blank output
        return findMessages(list, d, iter, output);
    }

    // helper method
    public static ArrayList<Message> findMessages(List<Message> list, Date d, Iterator<Message> iter,
            ArrayList<Message> output) {
        // recursive case
        if (iter.hasNext()) {
            Message currentMessage = iter.next();
            if (currentMessage.getDate().equals(d)) {
                output.add(currentMessage);
            }
            return findMessages(list, d, iter, output);
        } else { // base case: if list is empty
            if (output.size() == 0) {
                return null;
            } else {
                return output;
            }
        }
    }

    public static <E> void print(List<E> list) {
        Iterator<E> iter = list.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}