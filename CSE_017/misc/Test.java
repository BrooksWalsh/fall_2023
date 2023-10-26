import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        // /**
        // * Testing ArrayList
        // */
        // System.out.println("\nArrayList:");
        // ArrayList<String> cityAL = new ArrayList<>();
        // cityAL.add("New York");
        // cityAL.add("San Diego");
        // cityAL.add("Atlanta");
        // cityAL.add(0, "Baltimore");
        // cityAL.add(2, "Pittsburg");

        // // display the content of the list
        // System.out.println(cityAL.toString());
        // // iterator to display the elements of the list
        // Iterator<String> cityIterator = cityAL.iterator();
        // while (cityIterator.hasNext()) {
        // System.out.print(cityIterator.next() + " ");
        // }
        // System.out.println();
        // // get(index) to display the elements of the list
        // for (int i = 0; i < cityAL.size(); i++) {
        // System.out.print(cityAL.get(i) + " ");
        // }
        // System.out.println();
        // // remove(int)
        // cityAL.remove(1);
        // System.out.println(cityAL.toString());

        // Testing LinkedList
        System.out.println("\nLinkedList:");
        LinkedList<String> cityLL = new LinkedList<>();
        cityLL.add("Boston");
        cityLL.add("Philadelphia");
        cityLL.addFirst("San Francisco");
        cityLL.addFirst("Washington");
        cityLL.addFirst("Portland");

        System.out.println(cityLL.toString());

        Iterator<String> cityIterator = cityLL.iterator();
        System.out.print("LinkedList (iterator): ");
        while (cityIterator.hasNext()) {
            System.out.print(cityIterator.next() + " ");
        }
        System.out.println();
        cityLL.removeFirst();
        System.out.println(cityLL.toString());
        cityLL.removeLast();
        System.out.println(cityLL.toString());

        // Testing Stack
        Stack<String> cityStack = new Stack<>();
        cityStack.push("New York");
        cityStack.push("San Diego");
        cityStack.push("Atlanta");
        cityStack.push("Baltimore");
        cityStack.push("Pittsburg");
        System.out.println("City Stack (toString): " +
                cityStack.toString());
        System.out.print("City Stack (pop): ");
        while (!cityStack.isEmpty())
            System.out.print(cityStack.pop() + " ");
        System.out.println();

        // Testing Queue
        Queue<String> cityQueue = new Queue<>();
        cityQueue.offer("New York");
        cityQueue.offer("San Diego");
        cityQueue.offer("Atlanta");
        cityQueue.offer("Baltimore");
        cityQueue.offer("Pittsburg");
        System.out.println("City Queue (toString): " +
                cityQueue.toString());
        System.out.print("City Queue (poll): ");
        while (!cityQueue.isEmpty())
            System.out.print(cityQueue.poll() + " ");
        System.out.println();

        // Testing PriorityQueue
        PriorityQueue<String> cityPriorityQueue = new PriorityQueue<>();
        cityPriorityQueue.offer("New York");
        cityPriorityQueue.offer("San Diego");
        cityPriorityQueue.offer("Atlanta");
        cityPriorityQueue.offer("Baltimore");
        cityPriorityQueue.offer("Pittsburg");
        System.out.println("\nCity Priority Queue: " +
                cityPriorityQueue.toString());
        System.out.print("City Priority Queue (poll): ");
        while (!cityPriorityQueue.isEmpty()) {
            System.out.print(cityPriorityQueue.poll() + " ");
        }
        System.out.println();

    }

}
