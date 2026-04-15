import java.util.Scanner;

public class MediaStore {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Book book1 = new Book();
        String title;

        title = scnr.nextLine();
        book1.setTitle(title);
        System.out.println(book1.getTitle());

    }
}
