import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/* Discussion of number of collisions and clusters, and the largest cluster.
 * 
 * Number of collisions:
 * - There were 16,997 collisions in the HashMapLP. This indicates that for 16,997 elements,
 *   the index its hashCode hashed to was occupied and linear probing was used to place the
 *   element. Considering this is more than a tenth of the total hashTable capacity, this is
 *   not exactly an ideal result. Perhaps quadratic probing or double-hashing would perform
 *   better, but this could be acceptable performance in some contexts. The HashTable 
 *   capacity being 131,072 makes sense because we initialized it to 63,000 and expected to
 *   have exactly one rehash, which would double the capacity to the next power of two; in
 *   this case 2^(17) = 131,072.
 * Number of Clusters:
 * - There were 36,556 clusters in the HashMapLP. This indicates that there were 36,556 
 *   unique, continuous groups of elements. In the case of a linear probing hash map, the 
 *   more clusters the better; more clusters means less probing on average. Considering the
 *   fact that this hash map has about 63,000 elements, having 36,556 clusters isn't bad. 
 *   This could/should suggest that there are many small clusters, which would be exactly 
 *   what we want in a hash map with linear probing; but as is seen in the next section, 
 *   this is not the case.
 * Size of the Largest Cluster:
 * - The size of the largest cluster was 14,729 elements in the HashMapLP. This indicates 
 *   the worst case performance of the lookup time in the hash table as the get() function
 *   will only ever iterate to the end of the cluster. A result of 14,729 as the size of the
 *   largest cluster is a strikingly poor performance and seems to imply that there is a 
 *   potential issue with the way that the provided hash function works. This is not to say
 *   that it ~doesn't~ work, rather I am suggesting that having a single cluster be so large
 *   that its size is almost half the number of the total amount of clusters clearly indicates
 *   that there are many singleton clusters and one or two very large clusters. We would 
 *   prefer to have many clusters of about equal size to ensure that our linear probing never
 *   gets too lengthy. To achieve this goal, we must edit the hash or hashCode functions to 
 *   better suit the data.
 */

/**
 * The Test class for HW_10 has many methods that all contribute to reading and
 * analyzing some movie ratings data. The largest section of text below is a
 * long explanation I made to help guide my understanding while I built a regex
 * delimiter pattern for reading movie data. Additionally, I used quicksort from
 * ALA_11 to sort movies. There was also a strange behavior in this class in
 * regard to the scanner object failing when not given the argument "UTF-8"
 * (more below).
 * 
 * @since 2023-12-09
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Test {
    public static void main(String[] args) {
        // read/initialize data using hashmap
        HashMapLP<Integer, Movie> hashMap = new HashMapLP<>(63000);
        readMovies(hashMap, "movies.csv");
        readRatings(hashMap, "ratings.csv");

        // searching hashmap with given array
        System.out.println("Results of the search in the hashmap");
        int[] movieIDs = { 1544, 2156, 31349, 3048, 4001, 356, 5672, 6287, 25738, 26 };
        searchHashMap(hashMap, movieIDs);

        // sort hashMap list with natural ordering
        // used quickSort method from ALA_11 cus the instructions didn't specify how
        ArrayList<HashMapEntry<Integer, Movie>> hashMapList = hashMap.toList();
        quickSort(hashMapList, false); // sort by natural order (# ratings) for instructions
        hashMapList = findMostRated(hashMapList); // make sublist of entries with > 10,000 ratings
        quickSort(hashMapList, true); // then sort by average rating

        // display bottom ten lowest rated movies (with 10,000 ratings or more)
        System.out.println("Bottom Ten movies with at least 10,000 ratings");
        displayBottomTen(hashMapList);

        // display the top ten highest rated movies (with 10,000 ratings or more)
        System.out.println("Top Ten movies with at least 10,000 ratings");
        displayTopTen(hashMapList);

        // display data about hashmap clusters
        System.out.println("Hash table characteristics");
        hashMap.printClusters();
    }

    /**
     * Main method helper to print ratings stats about the bottom ten movies in the
     * list created from the hashmap
     * 
     * @param list (sorted by average rating)
     */
    public static void displayBottomTen(ArrayList<HashMapEntry<Integer, Movie>> list) {
        System.out.printf("%-7s\t%-50s\t%-15s\t%-5s\n", "Id", "Title", "Number of ratings", "Average rating");
        int counter = 10, currentIndex = 0;
        while (counter > 0) {
            Movie tempMovie = list.get(currentIndex++).getValue();
            System.out.printf("%s\t%-20d\t%-10.1f\n", tempMovie, tempMovie.getRatings(), tempMovie.getRating());
            counter--;
        }
        System.out.println();
    }

    /**
     * Main method helper to print ratings stats about the top ten movies in the
     * list created from the hashmap
     * 
     * @param list (sorted by average rating)
     */
    public static void displayTopTen(ArrayList<HashMapEntry<Integer, Movie>> list) {
        System.out.printf("%-7s\t%-50s\t%-15s\t%-5s\n", "Id", "Title", "Number of ratings", "Average rating");
        int counter = 10, currentIndex = list.size() - 1;
        while (counter > 0) {
            Movie tempMovie = list.get(currentIndex--).getValue();
            System.out.printf("%s\t%-20d\t%-10.1f\n", tempMovie, tempMovie.getRatings(), tempMovie.getRating());
            counter--;
        }
        System.out.println();
    }

    /**
     * Main method helper to search hashmap with given movieID array. Prints results
     * 
     * @param hm
     * @param movieIDs
     */
    public static void searchHashMap(HashMapLP<Integer, Movie> hm, int[] movieIDs) {
        System.out.printf("%-7s\t%-50s\t%-15s\t%-5s\t%s\n", "Id", "Title", "Number of ratings", "Average rating",
                "Iterations(get)");
        for (int i = 0; i < movieIDs.length; i++) {
            Movie tempMovie = hm.get(movieIDs[i]);
            System.out.printf("%s\t%-20d\t%-10.1f\t%d\n", tempMovie, tempMovie.getRatings(), tempMovie.getRating(),
                    HashMapLP.getIterations);
        }
        System.out.println();
    }

    /**
     * Main method helper to read ratings data from a CSV file.
     * 
     * @param hm
     * @param filename
     */
    public static void readRatings(HashMapLP<Integer, Movie> hm, String filename) {
        try {
            Scanner read = new Scanner(new File(filename), "UTF-8");
            // print reference loading bar for user to see ratings progress
            System.out.print("Loading Ratings Data:\n[" + "-".repeat(50) + "]\n[");
            int counter = 0;
            while (read.hasNextLine()) {
                // print 50 "loading bars" that will match above to show progress
                if (counter % 510000 == 0) {
                    System.out.print("-");
                }
                String ratingString = read.nextLine();
                String[] tokens = ratingString.split(",");
                // add each rating to its corresponding movie
                hm.get(Integer.parseInt(tokens[1])).addRating(Double.parseDouble(tokens[2]));
                counter++;
            }
            System.out.println("]\n");
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Main method helper to read movie data from a CSV file.
     * 
     * @param hm
     * @param filename
     */
    public static void readMovies(HashMapLP<Integer, Movie> hm, String filename) {
        try {
            // "UTF-8" as scanner argument is because of element 638 (line 629) in
            // Movies.csv containing a special character code by accident. It must be an
            // invisible character but calling "UTF-8" as the character encoder explicitly
            // makes the CSV read as intended. The scanner works differently on every
            // processor/device so it is also equally likely that this is just a weird quirk
            // of my (and some other students') current setup. This was pretty difficult to
            // debug so I left a message in piazza to help other students if they encounter
            // the same issue.
            Scanner read = new Scanner(new File(filename), "UTF-8");
            while (read.hasNextLine()) {
                String movieString = read.nextLine();

                /*
                 * Regex to find commas outside of quoted fields explained
                 * - Scrolled through the CSV a bit a noticed that the movie titles with commas
                 * always also have quotation marks around them. So all I have to do is ignore
                 * commas within double quotes (sounds easy enough...).
                 * - To properly parse the movie titles, I have built the following delimiter
                 * regex to use with the split method:
                 * 
                 * ----- ,(?=(?:\"[^\"]*\")*[^\"]*$) -----
                 * 
                 * - Starts with a comma, the delimiter.
                 * 
                 * - (?= ... ) is a positive lookahead assertion. This ensures whatever is in
                 * parenthesis is true for the position right after the comma. In other words,
                 * it checks for a pattern in the String without consuming it.
                 * 
                 * - (?: ... )* is a non-capturing group repeated "*" times (zero or more).
                 * This groups the quoted part of the regex pattern together without saving the
                 * matched text in a capturing group. It is used within the lookahead assertion
                 * to grab the movie title and check for double quotes.
                 * 
                 * - \"[^\"]*\" is within the lookahead/non-capturing structure and matches a
                 * double quotes followed by any number of characters that are not a double
                 * quote, followed by a closing double quote. This part effectively matches a
                 * quoted string in the non-capturing group allowing pairs of quotes.
                 * 
                 * - [^\"]*$ finishes off the positive lookahead assertion by ensuring that it
                 * checks all the way to the end of the line. This part also catches the fact
                 * that most of the titles with commas (and therefore double quotes) have a
                 * closing double quote at the end of the movie title. The regex would
                 * erroneously split at commas within double quotes without this.
                 * 
                 * - Output: Split() method only uses comma as delimiter if the comma is
                 * followed by zero or an even number of double quotes. This implies that the
                 * comma must not be within double quotes because the regex would see an odd
                 * number of quotes ahead of the comma if the comma was within paired up
                 * quotations. This would make the lookahead assertion return false for the
                 * comma's status as a delimiter. Thus, we can be sure only the desired commas
                 * are used for delimiting values in the CSV.
                 */
                String regex = ",(?=(?:\"[^\"]*\")*[^\"]*$)";
                String[] tokens = movieString.split(regex);

                // remove quotes from beginning and end of movie titles that had commas
                // ^\" --beginning double quote
                // |\"$ --OR ending double quote
                // replace with "" (nothing string)
                tokens[1] = tokens[1].replaceAll("^\"|\"$", "");

                // create movie object and add it to the HashMap
                Movie movieToAdd = new Movie(Integer.parseInt(tokens[0]), tokens[1], tokens[2]);
                hm.put(Integer.parseInt(tokens[0]), movieToAdd);
            }
            read.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Main method helper. Returns sublist of movies with more than 10000 ratings.
     * 
     * @param al
     * @return
     */
    public static ArrayList<HashMapEntry<Integer, Movie>> findMostRated(ArrayList<HashMapEntry<Integer, Movie>> al) {
        int currentIndex = al.size() - 1;
        ArrayList<HashMapEntry<Integer, Movie>> output = new ArrayList<>();
        while (al.get(currentIndex).getValue().getRatings() > 10000) {
            output.add(al.get(currentIndex--));
        }
        return output;
    }

    /**
     * QuickSort Method
     * 
     * @param list to be sorted
     *             Time complexity: O(nlog(n)) to O(n^2)
     */
    public static void quickSort(ArrayList<HashMapEntry<Integer, Movie>> list, boolean byAvgRating) {
        quickSort(list, 0, list.size() - 1, byAvgRating);
    }

    /**
     * QuickSort Recursive Helper Method
     * 
     * @param list  to be sorted
     * @param first the index where to start quickSorting
     * @param last  the index where to stop quickSorting
     *              Time complexity: O(nlog(n)) to O(n^2)
     */
    public static void quickSort(ArrayList<HashMapEntry<Integer, Movie>> list, int first, int last,
            boolean byAvgRating) {
        if (last > first) {
            int pivotIndex = partition(list, first, last, byAvgRating);
            quickSort(list, first, pivotIndex - 1, byAvgRating);
            quickSort(list, pivotIndex + 1, last, byAvgRating);
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
    public static int partition(ArrayList<HashMapEntry<Integer, Movie>> list, int first, int last,
            boolean byAvgRating) {
        HashMapEntry<Integer, Movie> pivot;
        int index, pivotIndex;
        pivot = list.get(first);// the first element
        pivotIndex = first;
        for (index = first + 1; index <= last; index++) {
            if (byAvgRating) {
                if (list.get(index).getValue().getRating() < pivot.getValue().getRating()) {
                    pivotIndex++;
                    swap(list, pivotIndex, index);
                }
            } else {
                if (list.get(index).getValue().compareTo(pivot.getValue()) < 0) {
                    pivotIndex++;
                    swap(list, pivotIndex, index);
                }
            }
        }
        swap(list, first, pivotIndex);
        return pivotIndex;
    }

    /**
     * swap method
     * 
     * @param list   where two elements will be swapped
     * @param index1 index of one element to be swapped
     * @param index2 index of the other element to be swapped
     * @throws an exception if index1 or index2 are invalid
     */
    public static void swap(ArrayList<HashMapEntry<Integer, Movie>> list, int index1, int index2) {
        if (index1 < 0 || index1 >= list.size() || index2 < 0 || index2 >= list.size())
            throw new ArrayIndexOutOfBoundsException();
        HashMapEntry<Integer, Movie> temp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, temp);
    }
}
