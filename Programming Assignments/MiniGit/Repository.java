// Amelia Li
// 02/07/2024
// CSE 123
// PA1: Mini-Git
// TA: Jake Page
// This class represents a version control system repository that keeps track
// of different revisions and operations of commits.

import java.util.*;
import java.text.SimpleDateFormat;

public class Repository {

    /**
     * TODO: Implement your code here.
     */

    private Commit head;
    private String name;
    private int repoSize = 0; 


    // pre: takes an input string as name, throws IllegalArgumentException if the name
    //      is null or empty
    // post: constructs a new repository with input name
    public Repository (String name)
    {
        if (name == null || name == "")
        {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    // pre: takes no argument
    // post: returns ID of the head commit in the repository, returns null if there
    //      are no commits
    public String getRepoHead ()
    {
        if (head == null)
        {
            return null;
        }

        String toReturn = (String)head.id;

        return toReturn;
    }

    // pre: takes no argument
    // post: returns the number of commits in repository
    public int getRepoSize ()
    {
        return repoSize;
    }

    // pre: takes no argument
    // post: returns a string representation of the header commit; if there are no
    //      commits returns a string that says so
    public String toString ()
    {
        if (head == null)
        {
            return name + " - No commits";
        }
        return name + " - Current head: " + head.toString();
    }

    // pre: takes a string representing a commit ID
    // post: returns true if commit with input ID is in the repository, returns
    //      false if not
    public boolean contains (String targetID)
    {
        
        Commit temp = head;
        
        while (temp != null)
        {
            if (temp.id.equals(targetID))
            {
                return true;
            }
            else
            {
                temp = temp.past;
            }
        }

        return false;
    }

    // pre: takes an int representing number of commits to show; throw
    //      IllegalArgumentException if input number is non-positive, return an empty string
    //      if there are no commits in repository
    // post: returns a string representing the most recent given number of commits; if
    //      there are less than requested number of commits, return string with all existing
    public String getHistory (int n)
    {

        if (n <= 0)
        {
            throw new IllegalArgumentException();
        }
        if (head == null)
        {
            return "";
        }

        String toReturn = "";
        
        Commit temp = head;
        while (temp != null && n > 0)
        {
            toReturn += temp.toString() + "\n";
            temp = temp.past;
            n--;
        }

        return toReturn;
    }

    // pre: takes a string as message of a new commit
    // post: creates a new commit with input message and adds it to the repository,
    //      returns a string that's the ID of the new commit
    public String commit (String message)
    {
        Commit newCommit = new Commit(message);
        newCommit.past = head;
        head = newCommit;
        repoSize++;
        return newCommit.id;
    }

    // pre: takes a string representing a commit ID
    // post: removes the commit with input ID from the repository, returns true if
    //      target commit is successfully removed, returns false if no commit
    //      matches the ID
    public boolean drop (String targetID)
    {

        Commit curr = head;
        Commit prev = null;

        while (curr != null)
        {
            if (curr.id.equals(targetID))
            {
                if (prev == null)
                {
                    head = head.past;
                }
                else
                {
                    prev.past = curr.past;
                }
                repoSize--;
                return true;
            }
            else
            {
                prev = curr;
                curr = curr.past;
            }
            
        }

        return false;
    }

    // pre: takes another repository
    // post: takes all commits from the other repository and moves them into this
    //      repository, combines commits in chronological order; removes all
    //      commits from the other repository
    public void synchronize (Repository other)
    {
        
        if (head == null && other.head != null)
        {
            head = other.head;
            other.head = null;
        }

        Commit curr = head;
        Commit prev = null;

        while (other.head != null)
        {
            if (other.head.timeStamp > curr.timeStamp)
            {
                if (prev == null)
                {
                    Commit temp = other.head;
                    other.head = other.head.past;
                    temp.past = curr;
                    head = temp;
                    prev = temp;
                    
                }
                else
                {
                    Commit temp = other.head;
                    other.head = other.head.past;
                    temp.past = curr;
                    prev.past = temp;
                    prev = prev.past;
                }
                repoSize++;
                other.repoSize--;
                
            }
            else
            {
                if (curr.past == null)
                {
                    curr.past = other.head;
                    other.head = null;
                    repoSize += other.repoSize;
                }
                else{
                    prev = curr;
                    curr = curr.past;
                }
            }

        }

    }


    /**
     * DO NOT MODIFY
     * A class that represents a single commit in the repository.
     * Commits are characterized by an identifier, a commit message,
     * and the time that the commit was made. A commit also stores
     * a reference to the immediately previous commit if it exists.
     *
     * Staff Note: You may notice that the comments in this 
     * class openly mention the fields of the class. This is fine 
     * because the fields of the Commit class are public. In general, 
     * be careful about revealing implementation details!
     */
    public class Commit {

        private static int currentCommitID;

        /**
         * The time, in milliseconds, at which this commit was created.
         */
        public final long timeStamp;

        /**
         * A unique identifier for this commit.
         */
        public final String id;

        /**
         * A message describing the changes made in this commit.
         */
        public final String message;

        /**
         * A reference to the previous commit, if it exists. Otherwise, null.
         */
        public Commit past;

        /**
         * Constructs a commit object. The unique identifier and timestamp
         * are automatically generated.
         * @param message A message describing the changes made in this commit.
         * @param past A reference to the commit made immediately before this
         *             commit.
         */
        public Commit(String message, Commit past) {
            this.id = "" + currentCommitID++;
            this.message = message;
            this.timeStamp = System.currentTimeMillis();
            this.past = past;
        }

        /**
         * Constructs a commit object with no previous commit. The unique
         * identifier and timestamp are automatically generated.
         * @param message A message describing the changes made in this commit.
         */
        public Commit(String message) {
            this(message, null);
        }

        /**
         * Returns a string representation of this commit. The string
         * representation consists of this commit's unique identifier,
         * timestamp, and message, in the following form:
         *      "[identifier] at [timestamp]: [message]"
         * @return The string representation of this collection.
         */
        @Override
        public String toString() {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(timeStamp);

            return id + " at " + formatter.format(date) + ": " + message;
        }

        /**
        * Resets the IDs of the commit nodes such that they reset to 0.
        * Primarily for testing purposes.
        */
        public static void resetIds() {
            Commit.currentCommitID = 0;
        }
    }
}
