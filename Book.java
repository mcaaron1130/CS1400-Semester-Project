public class Book {
    private String title;
    private int year;
    private double price;  

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
        this.price = 0.0;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        if(year < 1950) {
            price = 25.99;
        }
        else if (year >= 1950 && year < 2000) {
            price = 19.99;
        }
        else {
            price = 15.99;
        }
        return price;
    }
}
