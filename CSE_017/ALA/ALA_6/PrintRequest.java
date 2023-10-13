/**
 * The PrintRequest class is for keeping track of print requests in a simulation
 * for ALA_6. While these requests have nothing to do with actual printers, they
 * hold data such as size and priority. The primary purpose of PrintRequest
 * objects is to fill a priority queue and use it in the Test class.
 *
 * @since 2023-10-12
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class PrintRequest implements Comparable<PrintRequest> {
    private int id;
    private String group;
    private long size;

    /**
     * 3-arg constructor of the PrintRequest class. Allows user to set the id,
     * priority group, and data size of a PrintRequest object.
     * 
     * @param id
     * @param group
     * @param size
     */
    public PrintRequest(int id, String group, long size) {
        this.id = id;
        this.group = group;
        this.size = size;
    }

    // getters/setters
    public int getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    public long getSize() {
        return size;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setSize(long size) {
        this.size = size;
    }

    /**
     * Override the toString behavior in the PrintRequest class in order to print a
     * formatted request with relevant information.
     */
    @Override
    public String toString() {
        double sizeOut = size;
        String unit = "bytes";
        if (sizeOut > 1000000000) {
            sizeOut /= 1000000000;
            unit = "GB";
        } else if (sizeOut > 1000000) {
            sizeOut /= 1000000;
            unit = "MB";
        } else if (sizeOut > 1000) {
            sizeOut /= 1000;
            unit = "KB";
        }
        return String.format("%5d\t\t%-5s\t\t%-5.1f%s", id, group, sizeOut, unit);
    }

    /**
     * Overrides the compareTo method to set the natural ordering of PrintRequests
     * as determined by their priority group data member.
     * 
     * @param pr
     * @return int (ascending priority)
     */
    @Override
    public int compareTo(PrintRequest pr) {
        return this.getPriority() - pr.getPriority();
    }

    /**
     * Helper method to the compareTo method that retrieves priority as a number
     * based on the group data member.
     * 
     * @return
     */
    private int getPriority() {
        switch (group) {
            case "root":
                return 0;
            case "admin":
                return 1;
            case "user":
                return 2;
            case "batch":
                return 3;
            default:
                return -1;
        }
    }
}
