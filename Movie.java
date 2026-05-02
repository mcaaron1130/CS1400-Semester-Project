public class Movie {
    
    private String title;
    private int year;
    private double price;  

    public Movie(String title, int year) {
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
        if(year < 2010) {
            price = 15.99;
        }
        else if (year >= 2010 && year < 2020) {
            price = 19.99;
        }
        else {
            price = 25.99;
        }
        return price;
    }
}
