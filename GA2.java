//data manager

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

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

//video game class
class videoGames extends commonAttributes{
    //unique attributes
    String platform;
    String genre;
    String publisher;
    double copies_sold;    //by the millions

    public videoGames(int id, String type, String title, String platform, int release_year,
                      String rating, String genre, double copies_sold) {
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
                        int global_sales, int tracks, double duration_minutes, String genre) {
        super(id, type, title, null, release_year, null, null);
        this.artist = artist;
        this.global_sales = global_sales;
        this.tracks = tracks;
        this.duration_minutes = duration_minutes;
        this.genre = genre;
    }
}

//manager class
class Manager {
    private ArrayList<commonAttributes> mediaList;

    public  Manager() {
        this.mediaList = new ArrayList<>();
    }

    //adds media to list
    public void addMedia(commonAttributes media) {
        mediaList.add(media);
    }

    //gets total number of items within the media array
    public int getTotalMediaCount() {
        return mediaList.size();
    }

    //gets number of movies within list
    public int getMovieCount() {
        return (int) mediaList.stream().filter(m -> m instanceof movies).count();
    }

    //gets number of tv shows within list
    public int getTVShowCount() {
        return (int) mediaList.stream().filter(m -> m instanceof tvShows).count();
    }

    //gets number of video games within list
    public int getVideoGameCount() {
        return (int) mediaList.stream().filter(m -> m instanceof videoGames).count();
    }

    //gets number of music albums within list
    public int getMusicAlbumCount() {
        return (int) mediaList.stream().filter(m -> m instanceof musicAlbums).count();
    }

    //finds oldest product by release year
    public commonAttributes getOldestMedia() {
        return mediaList.stream().min(Comparator.comparingInt(m -> m.release_year)).orElse(null);
    }

    //finds most popular video game by copies sold
    public videoGames getMostPopularVideoGame() {
        return mediaList.stream()
                .filter(m -> m instanceof videoGames)   //filters for instances of video games
                .map(m -> (videoGames) m)   //casts object to video games
                .max(Comparator.comparingDouble(v -> v.copies_sold))    //finds the maximum between all instances of copies sold
                .orElse(null);  //otherwise it is null
    }

    //finds most popular music albums
    public musicAlbums getMostPopularMusicAlbum() {
        return mediaList.stream()
                .filter(m -> m instanceof musicAlbums)  //filters for instances of music albums
                .map(m -> (musicAlbums) m)  //casts object to music albums
                .max(Comparator.comparingInt(a -> a.global_sales))  //finds the maximum between all instances of global sales
                .orElse(null);  //otherwise it is null
    }


    //add getCommonAgeRating here

    //method that returns shortest movie
    public movies getShortestMovie() {
        return mediaList.stream()
                .filter(m -> m instanceof movies)
                .map(m -> (movies) m)
                .min(Comparator.comparingInt(m -> m.durations_minutes))
                .orElse(null);
    }

    //method that returns shortest music albums
    public musicAlbums getShortestMusicAlbum() {
        return mediaList.stream()
                .filter(m -> m instanceof musicAlbums)
                .map(m -> (musicAlbums) m)
                .min(Comparator.comparingDouble(a -> a.duration_minutes))
                .orElse(null);
    }

    //method that loads/reads csv
    public void loadMedia(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            //while loop reads line by line and extracts data
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length < 7) continue;    //ensures media has valid values

                int id = Integer.parseInt(values[0].trim());
                String type = values[1].trim();
                String title = values[2].trim();

                switch (type.toLowerCase()) {
                    case "movie":
                        if (values.length >= 9) {
                            addMedia(new movies(id, type, values[3].trim(), values[2].trim(), values[4].trim(),
                                    Integer.parseInt(values[5].trim()), values[6].trim(), Integer.parseInt(values[7].trim()), values[8].trim()));
                        }
                        break;

                    case "tv show":
                        if (values.length >= 9) {
                            String seasonsString = values[7].trim();
                            int numberOfSeasons;
                            if (seasonsString.replaceAll("[^0-9]", "").isEmpty()) {
                                numberOfSeasons = 0; // default value if no number is found
                            }

                            else {
                                numberOfSeasons = Integer.parseInt(seasonsString.replaceAll("[^0-9]", ""));
                            }

                            addMedia(new tvShows(id, type, title, values[3].trim(), values[4].trim(),
                                    Integer.parseInt(values[5].trim()), values[6].trim(), numberOfSeasons, values[8].trim()));
                        }
                        break;

                    case "video game":
                        if (values.length >= 7) {
                            addMedia(new videoGames(id, type, title, values[3].trim(),
                                    Integer.parseInt(values[4].trim()), values[5].trim(), values[6].trim(), Double.parseDouble(values[7].trim())));
                        }
                        break;


                    case "music album":
                        if (values.length >= 8) {
                            addMedia(new musicAlbums(id, type, Integer.parseInt(values[2].trim()), values[3].trim(), values[4].trim(),
                                    Integer.parseInt(values[5].trim()), Integer.parseInt(values[6].trim()), Double.parseDouble(values[7].trim()), values[7].trim()));
                        }
                        break;

                    default:
                        System.out.println("Unknown media type: " + type);
                        break;
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

public class GA2 {
    //driver class
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Manager manager = new Manager();

        System.out.println("\nPut your data set in the same folder this program is running in and type in the name of the file along with the extension.");
        System.out.println("You can also input the file path.");
        String filename = scnr.nextLine();
        manager.loadMedia(filename);
        commonAttributes oldestMedia = manager.getOldestMedia();

        System.out.println("\nTotal media count: " + manager.getTotalMediaCount());
        System.out.println("Movies: " + manager.getMovieCount());
        System.out.println("TV Shows: " + manager.getTVShowCount());
        System.out.println("Video Games: " + manager.getVideoGameCount());
        System.out.println("Music Albums: " + manager.getMusicAlbumCount());

        if (oldestMedia != null) {
            System.out.println("Oldest media: " + oldestMedia.title + " (" + oldestMedia.release_year + ")");
        }

        System.out.println("Most popular video game: " + manager.getMostPopularVideoGame().title);
        System.out.println("Most popular music album: " + manager.getMostPopularMusicAlbum().title);
        System.out.println("Shortest movie: " + manager.getShortestMovie().title);
        System.out.println("Shortest music album: " + manager.getShortestMusicAlbum().title);
        scnr.close();

    }
}
