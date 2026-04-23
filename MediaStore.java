import java.util.Scanner;
import java.io.IOException;

public class MediaStore {
    public static void main(String[] args) throws IOException {
        Scanner scnr = new Scanner(System.in);

        MediaSystem catalog = new MediaSystem ("src/movies.txt", "src/company.txt");

        catalog.setMovieList();
        
        scnr.close();
    }
}
