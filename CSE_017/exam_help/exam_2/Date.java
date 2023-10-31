import java.util.InputMismatchException;

public class Date implements Comparable<Date> {
    private int day, month, year;

    public Date(int mm, int dd, int yyyy) {
        day = dd;
        month = mm;
        year = yyyy;
    }

    public Date(String d) {
        if (d.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            String[] tokens = d.split("/");
            month = Integer.parseInt(tokens[0]);
            day = Integer.parseInt(tokens[1]);
            year = Integer.parseInt(tokens[2]);
        } else {
            throw new InputMismatchException("Invalid date (mm/dd/yyyy)");
        }
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int dd) {
        day = dd;
    }

    public void setMonth(int mm) {
        month = mm;
    }

    public void setYear(int yyyy) {
        year = yyyy;
    }

    public String toString() {
        return month + "/" + day + "/" + year;
    }

    public int compareTo(Date d) {
        if (this.year != d.year) {
            return this.year - d.year;
        } else if (this.month != d.month) {
            return this.month - d.month;
        } else {
            return this.day - d.day;
        }
    }

    public boolean equals(Object o) {
        if (o instanceof Date) {
            Date d = (Date) o;
            return this.year == d.year &&
                    this.month == d.month &&
                    this.day == d.day;
        }
        return false;
    }
}