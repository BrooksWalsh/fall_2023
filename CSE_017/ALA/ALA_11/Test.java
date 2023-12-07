import java.util.ArrayList;
import java.util.Collections;

/* Discussion & Comparisons of Performances (apologies for length, used this to review)
 * 
 * Selection Sort:
 * - Selection sort the most naive sorting algorithm, comparing every element to every
 *   other element in the list. This approach leads to a stable, never-changing time
 *   complexity that matches it's worst case O(n^2). Our testing showed exactly this,
 *   as our list size is 10,000 and thus the iterations of selection sort is always 
 *   equal to 50,000,000 or (size * (size - 1)) / 2. This is on the order of n^2.
 * Insertion Sort:
 * - Insertion sort is what happens when some smart programmers get their hands on the
 *   most naive sorting algorithm and try to improve it. It uses a very similar 
 *   approach to the selection sort, but the main difference is that it will not loop
 *   when the elements are not out of order. Our testing shows this because we can see
 *   that on average, random data will be somewhat sorted and therefore requires only
 *   about half that of selection sort. In the sorted case, insertion sort does the more
 *   efficient thing and ignores it, producing linear time complexity. In the reversed
 *   case, insertion sort is back to quadratic time complexity as its ability to take
 *   advantage of a partially sorted list is useless.
 * Bubble Sort:
 * - Bubble sort is kindof a mix of naive approaches, with iterative comparisons but 
 *   also the ability to skip over sorted lists. The main difference here is that 
 *   bubble sort must iterate through the entire list again if even only one swap is
 *   made. Thus, a randomized list for bubble sort does just as badly as selection 
 *   sort, or about 50,000,000 iterations. This is the same as reversed list for bubble
 *   sort following the same logic. A sorted list, however, is totally skipped as no
 *   swaps are needed, so it performs in linear time. (not super helpful though)
 * Merge Sort:
 * - Merge sort is the first "divide and conquer" type sorting algorithm, and it shows.
 *   In these algorithms, the goal is to divide the dataset into smaller data sets, work
 *   with those, then subdivide and work with even smaller pieces recursively. In the 
 *   case of merge sort, the primary advantage it has over all others is its stability.
 *   Merge sort will always perform the exact same amount of iterations, regardless of 
 *   the sorted/unsorted nature of the data. In the case of 10,000 elements, we can
 *   calculate the number of iterations by considering the number of recursive calls 
 *   and merge operations as well as the sublist iterations. Thus we can estimate 
 *   the actual iterations as n*log(n) + n*log(n) = 140,000 + 140,000 = 280,000.
 *   Our actual number of iterations makes sense when compared with this calculation.
 * Quick Sort:
 * - Quick sort is the simplest in-place "version" of merge sort, and acts very similar
 *   besides the absence of sublist creation in favor of a pivot value. This design 
 *   choice leads to a substantially lower iteration count for best case, but also 
 *   a dramatically different highest iteration count for worst case. The best case,
 *   a random distribution, is now simply n*log(n) = 140,000 with some extra iterations
 *   to deal with pivots. The sorted case for quick sort ends up choosing the worst
 *   pivot every iteration, leading to the dreaded o(n^2) complexity we saw with the
 *   naive comparison sorts. In the reversed case, the pivot won't always be the worst
 *   choice, but most of the pivot selections will be terrible. This will be approaching
 *   quadratic time complexity, but isn't quite as slow.
 * Heap Sort:
 * - Heap sort take a totally different approach. By leveraging the binary tree data
 *   structure, the heap sorts in a divide and conquer strategy, but every element acts
 *   kindof like its own pivot, organizing the data simply by its value. This usage
 *   of the min/max heap or heapify leads to a random and reversed case of n*log(n).
 *   In our case, that is 10K * log(10k) or about 140,000 iterations, which lines up.
 *   The unique case for heap sort is the sorted list because in order for the heap to
 *   be constructed properly, elements will require different numbers of iterations.
 *   In a sorted list, the elements at the beginning of the list will need to percolate
 *   down through the entire mid-construction heap to find the right place, and will 
 *   need more iterations to keep them in the right place as we add new elements. These
 *   processes add about 100,000 iterations given a fully sorted list of 10000 elements.
 * Bucket Sort:
 * - Bucket sort is the first non-comparison sorting algorithm we were shown. In this
 *   algorithm, we sort INTEGERS ONLY by placing them at an index corresponding to the
 *   integer value. While naive, this approach has no comparisons and is therefore the
 *   most time efficient algorithm for sorting integers. In our case, we create buckets
 *   from index 0 to index 10001 and our maximum value is bounded by the size of our 
 *   list so this algorithm will have 5 loops that all iterate through approximately
 *   the whole list. Thus we get 5 * 10,000 = 50,000 iterations. Important note: while
 *   the bucket sort may have the fewest iterations of any sorting method for integers,
 *   it also creates an ArrayList for each bucket, so space complexity has the potential
 *   to be the worst of any non-recursive method.
 * Radix Sort:
 * - Radix sort is essentially just bucket sort with a static number of buckets, one for
 *   each possible digit (10 buckets). This makes radix sort's time complexity extremely
 *   related to bucket sort's. With radix sort, we must still iterate through each item 
 *   and classify it into a bucket, but now we must do that for each decimal place that
 *   our maximum value has. Thus, for 10,000 elements with a maximum value of 9999, we 
 *   have to iterate through each digit position (1-5), and for each position must iterate
 *   through each element of the list twice (to add/remove from buckets). This approximates
 *   the 90,000 iterations seen when testing.
 */

/**
 * This Test class is for comparing the sorting algorithms we have learned about
 * throughout the semester. It uses the helper class "Sort.java" to track
 * iterations in the various algorithms. Additionally, this class uses three
 * separate lists (one random, one sorted, and one reversed) to compare
 * efficiency in different contexts.
 * 
 * @since 2023-12-7
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Test {
    public static void main(String[] args) {
        // preparing data sets
        final int SIZE = 10000;
        ArrayList<Integer> randomList = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            int random = (int) (Math.random() * SIZE);
            randomList.add(random);
        }

        @SuppressWarnings("unchecked") // we know this is a safe operation given context
        ArrayList<Integer> sortedList = (ArrayList<Integer>) (randomList.clone());
        Collections.sort(sortedList);

        @SuppressWarnings("unchecked") // we know this is a safe operation given context
        ArrayList<Integer> reversedList = (ArrayList<Integer>) (sortedList.clone());
        Collections.reverse(reversedList);

        // test sorting algorithms (title)
        System.out.println("Dataset Size: " + SIZE + "\n");
        System.out.printf("%-20s\t%-10s\t%-10s\t%-10s\n", "Sorting Algorithm", "Random", "Sorted", "Reversed");

        // selection sort
        Sort.selectionSort(randomList);
        System.out.printf("%-20s\t%-10d", "Selection Sort", Sort.iterations);
        Sort.selectionSort(sortedList);
        System.out.printf("\t%-10d", Sort.iterations);
        Sort.selectionSort(reversedList);
        System.out.printf("\t%-10d\n", Sort.iterations);

        // reset data
        Collections.shuffle(randomList);
        Collections.reverse(reversedList);

        // insertion sort
        Sort.insertionSort(randomList);
        System.out.printf("%-20s\t%-10d", "Insertion Sort", Sort.iterations);
        Sort.insertionSort(sortedList);
        System.out.printf("\t%-10d", Sort.iterations);
        Sort.insertionSort(reversedList);
        System.out.printf("\t%-10d\n", Sort.iterations);

        // reset data
        Collections.shuffle(randomList);
        Collections.reverse(reversedList);

        // bubble sort
        Sort.bubbleSort(randomList);
        System.out.printf("%-20s\t%-10d", "Bubble Sort", Sort.iterations);
        Sort.bubbleSort(sortedList);
        System.out.printf("\t%-10d", Sort.iterations);
        Sort.bubbleSort(reversedList);
        System.out.printf("\t%-10d\n", Sort.iterations);

        // reset data
        Collections.shuffle(randomList);
        Collections.reverse(reversedList);

        // merge sort
        Sort.iterations = 0;
        Sort.mergeSort(randomList);
        System.out.printf("%-20s\t%-10d", "Merge Sort", Sort.iterations);
        Sort.iterations = 0;
        Sort.mergeSort(sortedList);
        System.out.printf("\t%-10d", Sort.iterations);
        Sort.iterations = 0;
        Sort.mergeSort(reversedList);
        System.out.printf("\t%-10d\n", Sort.iterations);

        // reset data
        Collections.shuffle(randomList);
        Collections.reverse(reversedList);

        // Quick sort
        Sort.quickSort(randomList);
        System.out.printf("%-20s\t%-10d", "Quick Sort", Sort.iterations);
        Sort.quickSort(sortedList);
        System.out.printf("\t%-10d", Sort.iterations);
        Sort.quickSort(reversedList);
        System.out.printf("\t%-10d\n", Sort.iterations);

        // reset data
        Collections.shuffle(randomList);
        Collections.reverse(reversedList);

        // heap sort
        Sort.heapSort(randomList);
        System.out.printf("%-20s\t%-10d", "Heap Sort", Sort.iterations);
        Sort.heapSort(sortedList);
        System.out.printf("\t%-10d", Sort.iterations);
        Sort.heapSort(reversedList);
        System.out.printf("\t%-10d\n", Sort.iterations);

        // reset data
        Collections.shuffle(randomList);
        Collections.reverse(reversedList);

        // bucket sort
        Sort.bucketSort(randomList);
        System.out.printf("%-20s\t%-10d", "Bucket Sort", Sort.iterations);
        Sort.bucketSort(sortedList);
        System.out.printf("\t%-10d", Sort.iterations);
        Sort.bucketSort(reversedList);
        System.out.printf("\t%-10d\n", Sort.iterations);

        // reset data
        Collections.shuffle(randomList);
        Collections.reverse(reversedList);

        // radix sort
        Sort.radixSort(randomList);
        System.out.printf("%-20s\t%-10d", "Radix Sort", Sort.iterations);
        Sort.radixSort(sortedList);
        System.out.printf("\t%-10d", Sort.iterations);
        Sort.radixSort(reversedList);
        System.out.printf("\t%-10d\n", Sort.iterations);
    }
}
