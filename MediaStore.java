import java.util.Scanner;
import java.io.IOException;

public class MediaStore {
    public static void main(String[] args) throws IOException {
        Scanner scnr = new Scanner(System.in);

        System.out.println("Welcome to the Media Store! You may choose up to 10 movies or books if you want.");

        MediaSystem catalog = new MediaSystem("src/movies.txt", "src/movieYear.txt", "src/books.txt", "src/bookYear.txt");

        System.out.println("Enter 1 to shop for movies or 2 to shop for books: ");
        int choice = scnr.nextInt();

        if(choice == 1) {
            catalog.movieShoppingCart(scnr);
            System.out.println("Would you like to shop for books too? Enter 1 for yes or 2 for no: ");
            int choice2 = scnr.nextInt();
            if(choice2 == 1) {
                catalog.bookShoppingCart(scnr);
            }
        } else {
            catalog.bookShoppingCart(scnr);
            System.out.println("Would you like to shop for movies too? Enter 1 for yes or 2 for no: ");
            int choice3 = scnr.nextInt();
            if(choice3 == 1) {
                catalog.movieShoppingCart(scnr);
            }
        }
        scnr.close();
    }
}
