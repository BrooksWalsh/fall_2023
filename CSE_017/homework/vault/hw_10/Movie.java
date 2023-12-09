/**
 * The Movie class represents a movie and holds an ID, title, genre, as well as
 * ratings data. The natural ordering is based on the number of ratings.
 * 
 * @since 2023-12-09
 * @version Java 11 / VSCode
 * @author Brooks Walsh
 */
public class Movie implements Comparable<Movie> {
    private int id;
    private String title;
    private String genre;
    // number of users who rated the movie
    private int ratings;
    // average of all rating entered by users (out of 5)
    private double rating;

    /**
     * 3-arg constructor of Movie class.
     * 
     * @param id
     * @param title
     * @param genre
     */
    public Movie(int id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    /**
     * Getter method for Movie ID
     * 
     * @return int ID number
     */
    public int getId() {
        return id;
    }

    /**
     * Getter method for Movie title
     * 
     * @return String title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter method for Movie genre
     * 
     * @return String genre(s)
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Getter method for number of Movie ratings
     * 
     * @return int ratings COUNT
     */
    public int getRatings() {
        return ratings;
    }

    /**
     * Getter method for average Movie rating
     * 
     * @return int ratings AVERAGE
     */
    public double getRating() {
        return rating;
    }

    /**
     * Setter method for Movie ID
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter method for Movie title
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter method for Movie genre
     * 
     * @param genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Increments ratings count and calculates new average rating
     * 
     * @param newRating
     */
    public void addRating(double newRating) {
        double newRatingSum = (rating * ratings) + newRating;
        rating = newRatingSum / ((double) ++ratings);
    }

    /**
     * Returns formatted String of Movie object
     */
    public String toString() {
        return String.format("%-7d\t%-50s", id, title);
    }

    /**
     * Defines natural ordering of movies to be by number of ratings.
     * 
     * - Personal Note: Really feels like Movies should have natural ordering of
     * average rating not number of ratings. That would both make more sense and
     * make the assignment easier.
     * 
     * @return int comparison
     */
    public int compareTo(Movie movie) {
        if (this.ratings > movie.ratings) {
            return 1;
        } else if (this.ratings < movie.ratings) {
            return -1;
        } else {
            return 0;
        }
    }
}
