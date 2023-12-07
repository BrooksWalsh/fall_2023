import java.util.ArrayList;

public class Sort {

    // data member for tracking iterations
    public static int iterations;

    /**
     * Selection Sort Method
     * 
     * @param list to be sorted
     *             Time complexity: O(n^2)
     */
    public static <E extends Comparable<E>> void selectionSort(ArrayList<E> list) {
        iterations = 0;
        int minIndex;
        for (int i = 0; i < list.size() - 1; i++) {
            iterations++;
            // Find the smallest element from i+1 to N
            E min = list.get(i);
            minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                iterations++;
                if (list.get(j).compareTo(min) < 0) {
                    min = list.get(j);
                    minIndex = j;
                }
            }
            // Swap the smallest element with element i
            swap(list, i, minIndex);
        }
    }

    /**
     * swap method
     * 
     * @param list   where two elements will be swapped
     * @param index1 index of one element to be swapped
     * @param index2 index of the other element to be swapped
     * @throws an exception if index1 or index2 are invalid
     */
    public static <E> void swap(ArrayList<E> list, int index1, int index2) {
        if (index1 < 0 || index1 >= list.size() || index2 < 0 || index2 >= list.size())
            throw new ArrayIndexOutOfBoundsException();
        E temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }

    /**
     * Insertion Sort Method
     * 
     * @param list to be sorted
     *             Time complexity: O(n^2)
     */
    public static <E extends Comparable<E>> void insertionSort(ArrayList<E> list) {
        iterations = 0;
        for (int i = 1; i < list.size(); i++) {
            iterations++;
            // Insert element i in the sorted sub-list
            E currentVal = list.get(i);
            int j = i;
            while (j > 0 && currentVal.compareTo(list.get(j - 1)) < 0) {
                iterations++;
                // Shift element (j-1) into element (j)
                list.set(j, list.get(j - 1));
                j--;
            }
            // Insert currentVal at index j
            list.set(j, currentVal);
        }
    }

    /**
     * Bubble Sort Method
     * 
     * @param list to be sorted
     *             Time complexity: O(n^2)
     */
    public static <E extends Comparable<E>> void bubbleSort(ArrayList<E> list) {
        iterations = 0;
        boolean sorted = false;
        for (int k = 1; k < list.size() && !sorted; k++) {
            iterations++;
            sorted = true;
            for (int i = 0; i < list.size() - k; i++) {
                iterations++;
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    swap(list, i, i + 1);
                    sorted = false;
                }
            }
        }
    }

    /**
     * Merge Sort Method
     * 
     * @param list to be sorted
     *             Time complexity: O(nlogn)
     *             Space Complexity: O(n)
     */
    public static <E extends Comparable<E>> void mergeSort(ArrayList<E> list) {
        iterations++; // count recursive calls
        if (list.size() > 1) {
            ArrayList<E> firstHalf = sublist(list, 0, list.size() / 2);
            ArrayList<E> secondHalf = sublist(list, list.size() / 2, list.size());

            mergeSort(firstHalf);
            mergeSort(secondHalf);
            merge(firstHalf, secondHalf, list);
        }
    }

    /**
     * Sublist method as a helper to the generic mergeSort method.
     * 
     * @param <E>
     * @param list
     * @param start
     * @param end
     * @return new ArrayList from index start to index (end - 1)
     */
    public static <E> ArrayList<E> sublist(ArrayList<E> list, int start, int end) {
        if (start < 0 || start >= list.size() || end < 0 || end > list.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (start > end) {
            return null;
        }

        ArrayList<E> newList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            iterations++;
            newList.add(list.get(i));
        }
        return newList;
    }

    /**
     * Method merge used by the merge sort method
     * 
     * @param list1 the first sorted list
     * @param list2 the second sorted list
     * @param list  where list1 and list2 are merged
     *              * Time complexity: O(n)
     */
    public static <E extends Comparable<E>> void merge(ArrayList<E> list1, ArrayList<E> list2, ArrayList<E> list) {
        int list1Index = 0, list2Index = 0, listIndex = 0;
        while (list1Index < list1.size() && list2Index < list2.size()) {
            iterations++;
            if (list1.get(list1Index).compareTo(list2.get(list2Index)) < 0)
                list.set(listIndex++, list1.get(list1Index++));
            else
                list.set(listIndex++, list2.get(list2Index++));
        }
        // copy the remaining elements from list1 to list if any
        while (list1Index < list1.size()) {
            iterations++;
            list.set(listIndex++, list1.get(list1Index++));
        }
        // copy the remaining elements from list2 to list if any
        while (list2Index < list2.size()) {
            iterations++;
            list.set(listIndex++, list2.get(list2Index++));
        }
    }

    /**
     * QuickSort Method
     * 
     * @param list to be sorted
     *             Time complexity: O(nlogn) to O(n^2)
     */
    public static <E extends Comparable<E>> void quickSort(ArrayList<E> list) {
        iterations = 0;
        quickSort(list, 0, list.size() - 1);
    }

    /**
     * QuickSort Recursive Helper Method
     * 
     * @param list  to be sorted
     * @param first the index where to start quicksorting
     * @param last  the index where to stop quicksorting
     *              Time complexity: O(nlogn) to O(n^2)
     */
    public static <E extends Comparable<E>> void quickSort(ArrayList<E> list, int first, int last) {
        iterations++;
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    /**
     * Partition method used by quicksort
     * 
     * @param list  to be sorted
     * @param first the index where to start the partitioning
     * @param last  the index where to stop partitioning
     * @return the index where the pivot is placed
     *         Time complexity: O(n)
     */
    public static <E extends Comparable<E>> int partition(ArrayList<E> list, int first, int last) {
        E pivot;
        int index, pivotIndex;
        pivot = list.get(first);// the first element
        pivotIndex = first;
        for (index = first + 1; index <= last; index++) {
            iterations++;
            if (list.get(index).compareTo(pivot) < 0) {
                pivotIndex++;
                swap(list, pivotIndex, index);
            }
        }
        swap(list, first, pivotIndex);
        return pivotIndex;
    }

    /**
     * Heap Sort Method
     * 
     * @param list to be sorted
     *             Time complexity: O(nlogn)
     *             Space complexity: O(n)
     */
    public static <E extends Comparable<E>> void heapSort(ArrayList<E> list) {
        iterations = 0;
        // Create a max heap
        Heap<E> heap = new Heap<>();
        // Add the elements of list to the heap
        for (int i = 0; i < list.size(); i++) {
            iterations++;
            heap.add(list.get(i));
            iterations += Heap.iterations;
        }
        // Move the data from the heap back to list
        for (int i = list.size() - 1; i >= 0; i--) {
            iterations++;
            list.set(i, heap.remove());
            iterations += Heap.iterations;
        }
    }

    /**
     * BucketSort Method
     * 
     * @param list to be sorted
     *             Time complexity: O(n+t)
     *             Space complexity: O(n+t)
     */
    public static void bucketSort(ArrayList<Integer> list) {
        iterations = 0;
        int t = max(list);
        ArrayList<ArrayList<Integer>> buckets;
        buckets = new ArrayList<>(t + 1);
        // create t+1 buckets
        for (int i = 0; i < t + 1; i++) {
            iterations++;
            buckets.add(new ArrayList<>());
        }

        // Distribute the data on the buckets
        for (int i = 0; i < list.size(); i++) {
            iterations++;
            ArrayList<Integer> bucket = buckets.get(list.get(i));
            bucket.add(list.get(i));
        }
        // Move the data from the buckets back to the list
        int k = 0;
        for (int i = 0; i < buckets.size(); i++) {
            iterations++;
            ArrayList<Integer> bucket = buckets.get(i);
            for (int j = 0; j < bucket.size(); j++) {
                iterations++;
                list.set(k++, bucket.get(j));
            }
        }
    }

    /**
     * max Method used by Bucket sort and Radix sort
     * 
     * @param list to be sorted
     * @return the maximum value in the array list
     *         Time complexity: O(n)
     */
    public static int max(ArrayList<Integer> list) {
        int maximum = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            iterations++;
            if (list.get(i) > maximum)
                maximum = list.get(i);
        }
        return maximum;
    }

    /**
     * RadixSort Method
     * 
     * @param list to be sorted
     *             Time complexity: O(n.d)
     *             Space complexity: O(n)
     */
    public static void radixSort(ArrayList<Integer> list) {
        iterations = 0;
        ArrayList<ArrayList<Integer>> buckets;
        buckets = new ArrayList<>();
        Integer maxValue = max(list);
        int digits = maxValue.toString().length();
        for (int d = 0; d < digits; d++) { // iterate through the radix positions
            iterations++;
            // create 10 buckets
            for (int j = 0; j < 10; j++) {
                iterations++;
                buckets.add(new ArrayList<>());
            }
            // Distribute the data on the buckets
            for (int j = 0; j < list.size(); j++) {
                iterations++;
                // find the index of the bucket where list[j] should be placed
                int bucketIndex = (list.get(j) % (int) (Math.pow(10, d + 1))) / (int) (Math.pow(10, d));
                ArrayList<Integer> bucket = buckets.get(bucketIndex);
                bucket.add(list.get(j));
            }
            // Move the data from the buckets back to the list
            int k = 0;
            for (int j = 0; j < 10; j++) {
                iterations++;
                ArrayList<Integer> bucket = buckets.get(j);
                for (int l = 0; l < bucket.size(); l++) {
                    iterations++;
                    list.set(k++, bucket.get(l));
                }
            }
            // clear all the buckets for the next iteration
            buckets.clear();
        }
    }

}