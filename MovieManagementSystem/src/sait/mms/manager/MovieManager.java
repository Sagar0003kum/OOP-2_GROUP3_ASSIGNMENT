package sait.mms.manager;

import java.util.*;
import java.io.*;
import sait.mms.problemdomain.Movie;

public class MovieManager {
    private ArrayList<Movie> movies;
    private Scanner input;

    public MovieManager() {
        movies = new ArrayList<Movie>();
        input = new Scanner(System.in);
        loadMovieList();
    }

    public void displayMenu() {
        int option = 0;

        do {
            System.out.println();
            System.out.println("Movie Management system");
            System.out.println("1\tAdd New Movie and Save");
            System.out.println("2\tGenerate List of Movies Released in a Year");
            System.out.println("3\tGenerate List of Random Movies");
            System.out.println("4\tExit");
            System.out.println();
            System.out.print("Enter an option: ");

            if (input.hasNextInt()) {
                option = input.nextInt();
                input.nextLine(); // consume newline
                switch (option) {
                    case 1:
                        addMovie();
                        break;
                    case 2:
                        generateMovieListInYear();
                        break;
                    case 3:
                        generateRandomMovieList();
                        break;
                    case 4:
                        saveMovieListToFile();
                        System.out.println("Exiting program...");
                        break;
                    default:
                        System.out.println();
                        System.out.println("Invalid option!");
                }
            } else {
                System.out.println();
                System.out.println("Invalid option!");
                input.nextLine(); // clear invalid input
            }
        } while (option != 4);
    }

    private void loadMovieList() {
        movies.clear();
        try {
            File file = new File("res/movies.txt");
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine().trim();
                if (line.length() > 0) {
                    String[] parts = line.split(",");
                    int duration = Integer.parseInt(parts[0].trim());
                    String title = parts[1].trim();
                    int year = Integer.parseInt(parts[2].trim());
                    movies.add(new Movie(duration, title, year));
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Please make sure movies.txt exists in res folder.");
        }
    }

    private void addMovie() {
        System.out.println();
        System.out.print("Enter duration: ");
        int duration = input.nextInt();
        input.nextLine();

        System.out.print("Enter movie title: ");
        String title = input.nextLine();

        System.out.print("Enter year: ");
        int year = input.nextInt();
        input.nextLine();

        if (duration <= 0 || year <= 0 || title.trim().isEmpty()) {
            System.out.println("Invalid input. Movie not added.");
            return;
        }

        Movie newMovie = new Movie(duration, title, year);
        movies.add(newMovie);
        saveMovieListToFile();
        System.out.println("Saving movies...");
        System.out.println("Added movie to the data file.");
    }

    private void generateMovieListInYear() {
        System.out.println();
        System.out.print("Enter in year: ");
        int year = input.nextInt();
        input.nextLine();

        System.out.println();
        System.out.println("Movie List");
        System.out.println("Duration\tYear\tTitle");

        int total = 0;
        for (Movie m : movies) {
            if (m.getYear() == year) {
                System.out.println(m.toString());
                total += m.getDuration();
            }
        }
        System.out.println();
        System.out.println("Total duration: " + total + " minutes");
    }

    private void generateRandomMovieList() {
        System.out.println();
        System.out.print("Enter number of movies: ");
        int count = input.nextInt();
        input.nextLine();

        if (count <= 0 || count > movies.size()) {
            System.out.println("Invalid number of movies.");
            return;
        }

        System.out.println();
        System.out.println("Movie List");
        System.out.println("Duration\tYear\tTitle");

        Random rand = new Random();
        ArrayList<Movie> randomList = new ArrayList<Movie>();
        int total = 0;

        while (randomList.size() < count) {
            Movie randomMovie = movies.get(rand.nextInt(movies.size()));
            if (!randomList.contains(randomMovie)) {
                randomList.add(randomMovie);
                System.out.println(randomMovie.toString());
                total += randomMovie.getDuration();
            }
        }

        System.out.println();
        System.out.println("Total duration: " + total + " minutes");
    }

    private void saveMovieListToFile() {
        try {
            PrintWriter writer = new PrintWriter("res/movies.txt");
            for (Movie m : movies) {
                writer.println(m.getDuration() + "," + m.getTitle() + "," + m.getYear());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }
    }
}
