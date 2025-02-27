import java.util.ArrayList;

//common attributes class
class commonAttributes {
    int id;
    String type;
    String title;
    String country;
    int release_year;
    String rating;
    String description;

    //constructor
    public commonAttributes(int id, String type, String title, String country, int release_year, String rating, String description) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.country = country;
        this.release_year = release_year;
        this.rating = rating;
        this.description = description;
    }
}

//movie class
class movies extends commonAttributes{
    //unique attributes
    String director;
    int durations_minutes;

    //constructor
    public movies(int id, String type, String director, String title, String country,
                  int release_year, String rating, int durations_minutes, String description) {
        super(id, type, title, country, release_year, rating, description);
        this.director = director;
        this.durations_minutes = durations_minutes;
    }
}

//tv show class
class tvShows extends commonAttributes{
    //unique attributes
    String director;
    int number_of_seasons;

    public tvShows(int id, String type, String title, String director, String country,
                    int release_year, String rating, int number_of_seasons, String description) {
        super(id, type, title, country, release_year, rating, description);
        this.director = director;
        this.number_of_seasons = number_of_seasons;
    }
}

class videoGames extends commonAttributes{
    //unique attributes
    String platform;
    String genre;
    String publisher;
    int copies_sold;    //by the millions

    public videoGames(int id, String type, String title, String platform, int release_year,
                      String rating, String genre, String publisher, int copies_sold) {
        super(id, type, title, null, release_year, rating, null);
        this.platform = platform;
        this.genre = genre;
        this.publisher = publisher;
        this.copies_sold = copies_sold;
    }
}

//music albums class
class musicAlbums extends commonAttributes{
    //unique attributes
    String artist;
    int global_sales;
    int tracks;
    double duration_minutes;
    String genre;

    public musicAlbums(int id, String type, int release_year, String artist, String title,
                        int global_sales, int tracks, double duration_minutes, String genre,) {
        super(id, type, title, null, release_year, null, null);
        this.artist = artist;
        this.global_sales = global_sales;
        this.tracks = tracks;
        this.duration_minutes = duration_minutes;
        this.genre = genre;
    }
}

public class GA2 {
    //driver class
    public static void main(String[] args) {


    }
}
