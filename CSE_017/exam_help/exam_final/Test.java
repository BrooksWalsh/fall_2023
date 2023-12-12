public class Test {
    public static void main(String[] args) {
        LinkedList<Integer> test = new LinkedList<>();
        for (int i = 20; i > 0; i--) {
            test.add(i);
        }
        System.out.println(test);
        test.sort(null);
        System.out.println(test);
    }
}
