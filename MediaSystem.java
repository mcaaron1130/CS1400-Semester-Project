import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;

public class MediaSystem {
   
    private Movie[] movieData;
    private Book[] bookData;
    
    public MediaSystem(String movieFile, String movieYearFile, String bookFile, String bookYearFile) throws IOException {
        movieData = createMovieData(movieFile, movieYearFile);
        bookData = createBookData(bookFile, bookYearFile);
    }

    public Movie[] createMovieData(String movieFile, String yearFile) throws IOException {
        // Read all lines from all three files
        ArrayList<String> movieLines = new ArrayList<>(Files.readAllLines(Paths.get(movieFile)));
        ArrayList<String> yearLines = new ArrayList<>(Files.readAllLines(Paths.get(yearFile)));

        // Validate that all files have the same number of lines
        if(movieLines.size() != yearLines.size()) {
            throw new IOException("Error: All files must have the same number of entries. " +
                "movies.txt has " + movieLines.size() + 
                ", movieYear.txt has " + yearLines.size());
        }

        // Create arrays with the appropriate size
        Movie[] movieData = new Movie[movieLines.size()];

        // Create Movie objects pairing movie names with companies and years
        for(int i = 0; i < movieLines.size(); i++) {
            String movieTitle = movieLines.get(i);
            int movieYear = Integer.parseInt(yearLines.get(i).trim());
            movieData[i] = new Movie(movieTitle, movieYear);
        }

        return movieData;
    }

    public Book[] createBookData(String bookFile, String yearFile) throws IOException {
        ArrayList<String> bookLines = new ArrayList<>(Files.readAllLines(Paths.get(bookFile)));
        ArrayList<String> bookYearLines = new ArrayList<>(Files.readAllLines(Paths.get(yearFile)));

        // Validate that all files have the same number of lines
        if(bookLines.size() != bookYearLines.size()) {
            throw new IOException("Error: All files must have the same number of entries. " +
                "books.txt has " + bookLines.size() + 
                ", bookYear.txt has " + bookYearLines.size());
        }

        // Create arrays with the appropriate size
        Book[] bookData = new Book[bookLines.size()];

        // Create Book objects pairing book names with years
        for(int i = 0; i < bookLines.size(); i++) {
            String bookTitle = bookLines.get(i);
            int bookYear = Integer.parseInt(bookYearLines.get(i).trim());
            bookData[i] = new Book(bookTitle, bookYear);
        }

        return bookData;
    }

    public void setMovieList() {

        for(int i = 0; i < movieData.length; ++i) {
            System.out.print(i + 1 + ". ");
            System.out.print(movieData[i].getTitle() + " (" + movieData[i].getYear() + ")");
            System.out.println(" - $" + movieData[i].getPrice());
            
        }
        
    }

    public void setBookList() {
        for(int i = 0; i < bookData.length; ++i) {
            System.out.print(i + 1 + ". ");
            System.out.print(bookData[i].getTitle() + " (" + bookData[i].getYear() + ")");
            System.out.println(" - $" + bookData[i].getPrice());
        }
    }

    public String chooseMovie() {
        Scanner scnr = new Scanner(System.in);
        int choice = scnr.nextInt();
        scnr.close();

        return movieData[choice - 1].getTitle();
    }

    public void movieShoppingCart(Scanner scnr) {
        ArrayList<Movie> cart = new ArrayList<>(10);
        int choice;
        double price = 0.0;

        setMovieList();
        System.out.println("Please enter the number of the movie you would like to add to your cart (up to 10 movies): ");
        System.out.println("Please enter -1 to remove a movie from your cart or 0 to go to check out.");
        for(int i = 0; i < 10; ++i) {
            choice = scnr.nextInt();
            if(choice == -1) {
                // Remove a movie from the cart
                System.out.println("Please enter the number of the movie you would like to remove from your cart: ");
                int removeChoice = scnr.nextInt();
                cart.remove(movieData[removeChoice - 1]);
                price -= movieData[removeChoice - 1].getPrice();
                System.out.println("Movie removed: " + movieData[removeChoice - 1].getTitle() + " - $" + movieData[removeChoice - 1].getPrice());
                System.out.print("Total price: $");
                System.out.printf("%.2f", price);
                System.out.println();
            } else if(choice == 0) {
                // Go to checkout
                break;
            } else {
                cart.add(movieData[choice - 1]);
                price += movieData[choice - 1].getPrice();
                System.out.println("Movie added: " + movieData[choice - 1].getTitle() + " - $" + movieData[choice - 1].getPrice());
            }

            System.out.print("Total amount: $");
            System.out.printf("%.2f", price);
            System.out.println();
        }

        checkout(price, "movies");
    }

    public void bookShoppingCart(Scanner scnr) {
        ArrayList<Book> cart = new ArrayList<>(10);
        int choice;
        double price = 0.0;

        setBookList();
        System.out.println("Please enter the number of the book you would like to add to your cart (up to 10 books): ");
        System.out.println("Please enter -1 to remove a book from your cart or 0 to go to check out.");
        for(int i = 0; i < 10; ++i) {
            choice = scnr.nextInt();
            if(choice == -1) {
                // Remove a book from the cart
                System.out.println("Please enter the number of the book you would like to remove from your cart: ");
                int removeChoice = scnr.nextInt();
                cart.remove(bookData[removeChoice - 1]);
                price -= bookData[removeChoice - 1].getPrice();
                System.out.println("Book removed: " + bookData[removeChoice - 1].getTitle() + " - $" + bookData[removeChoice - 1].getPrice());
                System.out.print("Total price: $");
                System.out.printf("%.2f", price);
                System.out.println();
            } else if(choice == 0) {
                // Go to checkout
                break;
            } else {
                cart.add(bookData[choice - 1]);
                price += bookData[choice - 1].getPrice();
                System.out.println("Book added: " + bookData[choice - 1].getTitle() + " - $" + bookData[choice - 1].getPrice());
            }
            System.out.print("Total amount: $");
            System.out.printf("%.2f", price);
            System.out.println();
        }

        checkout(price, "books");
    }

    public void checkout(double price, String type) {
        System.out.println("Thank you for shopping with us! A tax will be added to your total price.");
        if(price > 50.00) {
            price *= 1.05;
        }
        else {
            price *= 1.03;
        }
        System.out.print("You have spent $");
        System.out.printf("%.2f", price);
        System.out.println(" on " + type + " today!");

    }
}
  

