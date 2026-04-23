import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;

public class MediaSystem {
   
    private Movie[] movieData;
    
    public MediaSystem(String movieFile, String companyFile) throws IOException {
        movieData = createMovieData(movieFile, companyFile);
    }

    public Movie[] createMovieData(String movieFile, String companyFile) throws IOException {
        // Read all lines from both files
        ArrayList<String> movieLines = new ArrayList<>(Files.readAllLines(Paths.get(movieFile)));
        ArrayList<String> companyLines = new ArrayList<>(Files.readAllLines(Paths.get(companyFile)));

        // Validate that both files have the same number of lines
        if(movieLines.size() != companyLines.size()) {
            throw new IOException("Error: movies.txt has " + movieLines.size() + 
                " entries but company.txt has " + companyLines.size() + " entries. Files must have equal lines.");
        }

        // Create arrays with the appropriate size
        Movie[] movieData = new Movie[movieLines.size()];

        // Create Movie objects pairing movie names with companies
        for(int i = 0; i < movieLines.size(); i++) {
            String movieTitle = movieLines.get(i);
            String movieCompany = companyLines.get(i);
            movieData[i] = new Movie(movieTitle, movieCompany);
        }

        return movieData;
    }

    public void setMovieList() {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter a movie company's name: ");
        String company = scnr.nextLine();

        for(int i = 0; i < movieData.length; ++i) {
            if(movieData[i].getCompany().equals(company)) {
                System.out.println(movieData[i].getTitle());
            }
        }
        
        scnr.close();
    }

}
  

