import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The Airplane class produces/manages an array of "airplane seats". The purpose
 * of this exercise is to gain practice with throwing exceptions and using
 * regular expressions.
 *
 * @since 2023-09-07
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Airplane {

    private char[][] seatMap;

    /**
     * Default Airplane class constructor
     */
    public Airplane() {
        seatMap = new char[9][8];

        for (int i = 0; i < seatMap.length; i++) {
            for (int j = 0; j < seatMap[i].length; j++) {
                seatMap[i][j] = '.';
            }
        }
    }

    /**
     * 1-Arg constructor for the airplane class initializes seatMap with a saved
     * file
     * 
     * @param filename
     */
    public Airplane(String filename) {
        this(); // call default constructor; initialize to all empty seats
        readMap(filename);
    }

    /**
     * Reads the data from the file "filename" to the array seatMap
     * 
     * @param filename
     */
    private void readMap(String filename) {

        // open file
        File file = new File(filename);
        try {
            Scanner read = new Scanner(file);
            for (int i = 0; i < seatMap.length; i++) {
                for (int j = 0; j < seatMap[i].length; j++) {
                    seatMap[i][j] = read.next().charAt(0); // read chars from file
                }
            }

            read.close();
        } catch (FileNotFoundException e) {
            // empty catch block because if file is not found then empty seatMap
        }
    }

    /**
     * Returns true if seatNumber is valid, otherwise it throws and exception. Uses
     * regex to check the seat number
     * 
     * @param seatNumber
     * @return true/false
     * @throws InvalidSeatException
     */
    private boolean checkSeatNumber(String seatNumber) throws InvalidSeatException {
        if (!seatNumber.matches("[1-9][A-H]")) {
            throw new InvalidSeatException("Invalid seat number (row[1-9]column[A-H]). Please try again.");
        }
        return true;
    }

    /**
     * Returns true if seatNumber is reserved successfully, false if the seat number
     * is already occupied, or throws an exception from checkSeatNumber()
     * 
     * @param seatNumber
     * @return true/false
     * @throws InvalidSeatException
     */
    public boolean reserveSeat(String seatNumber) throws InvalidSeatException {
        checkSeatNumber(seatNumber);

        int row = seatNumber.charAt(0) - '1';
        int col = seatNumber.charAt(1) - 'A';

        if (seatMap[row][col] == '.') {
            seatMap[row][col] = 'X';
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if seatNumber is freed successfully, false if the seat is
     * already free, or throws an exception from checkSeatNumber()
     * 
     * @param seatNumber
     * @return true/false
     * @throws InvalidSeatException
     */
    public boolean freeSeat(String seatNumber) throws InvalidSeatException {
        checkSeatNumber(seatNumber);

        int row = seatNumber.charAt(0) - '1';
        int col = seatNumber.charAt(1) - 'A';

        if (seatMap[row][col] == 'X') {
            seatMap[row][col] = '.';
            return true;
        } else {
            return false;
        }
    }

    /**
     * Writes the contents of the array seatMap to the file "filename"
     * 
     * @param filename
     */
    public void saveMap(String filename) {
        File file = new File(filename);
        try {
            PrintWriter write = new PrintWriter(file);
            for (int i = 0; i < seatMap.length; i++) {
                for (int j = 0; j < seatMap[i].length; j++) {
                    write.print(seatMap[i][j] + " ");
                }
            }
            write.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot write to file. Seat map not saved.");
        }
    }

    /**
     * Returns the content of the array seatMap in a formatted string
     */
    @Override
    public String toString() {
        String out = "\tA\tB\tC\tD\tE\tF\tG\tH\n";
        for (int i = 0; i < seatMap.length; i++) {
            out += (i + 1) + "\t";
            for (int j = 0; j < seatMap[i].length; j++) {
                out += seatMap[i][j] + "\t";
            }
            out += "\n";
        }
        return out;
    }
}