// Amelia Li
// 01/18/2024
// CSE 123
// TA: Jake Page
// This class represents a book object. It records the book's title,
//  author(s) and rating(s) information.     

import java.util.ArrayList;
import java.util.List;

public class Book implements Media{

    private String title;
    private List<String> author;
    private List<Integer> ratings;
    
    //pre: takes two strings represeting book title and author name
    //post: creates new Book object with title and one author
    public Book(String title, String author)
    {
        this.title = title;

        this.author = new ArrayList<>();
        this.author.add(author);

        this.ratings = new ArrayList<>();
    }

    //pre: the constructor takes a string and a list of strings representing book title
    //  and author names
    //post: creates new Book object with title and a list of authors
    public Book(String title, List<String> authors)
    {
        this.title = title;
        this.author = authors;
        this.ratings = new ArrayList<>();
    }

    //pre: the method takes no argument
    //post: format and returns a string that shows the Book object's specific information
    public String toString()
    {

        String message = "";
        message += title + " by ";

        String au = "[";
        for (int i = 0; i < author.size(); i++)
        {
            if (i < author.size() - 1)
            {
                au += author.get(i) + ", ";
            }
            else
            {
                au += author.get(i) + "]";
            }
        }

        message += au;

        if (ratings == null || ratings.size() < 1)
        {
            return message += ": No ratings yet!";
        }

        double avg = getAverageRating();
        avg = Math.round(avg * 100.0) / 100.0;

        message += ": " + String.valueOf(avg) + " (" + String.valueOf(getNumRatings()) + " ratings)";
        return message;
    }

    //takes no argument and returns the book title
    public String getTitle()
    {
        return title;
    } 

    //takes no argument and returns the list of author(s)
    public List<String> getArtists()
    {
        return author;
    }

    //takes an int representing a new rating and add it to existing ratings
    public void addRating(int score)
    {
        ratings.add(score);
    }

    //takes no argument and returns the number of ratings of the object
    public int getNumRatings()
    {
        return ratings.size();
    }

    //takes no argument and returns the average rating of the object
    public double getAverageRating()
    {
        if (ratings.size() < 1)
        {
            return 0.0;
        }
        
        double sum = 0.0;

        for (int rate : ratings)
        {
            sum += rate;
        }
        return sum / ratings.size();
    }

}