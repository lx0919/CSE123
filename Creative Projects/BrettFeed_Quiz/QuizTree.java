// Amelia Li
// 02/27/2024
// CSE 123
// CP3: BrettFeed
// TA: Jake Page
// This class takes in a file and represents a quiz user can take.

// TODO: Implement your QuizTree class

import java.io.PrintStream;
import java.util.Scanner;

public class QuizTree {

    private QuizTreeNode root;
    
    // This constructor accepts a scanner and stores the quiz from the scanner.
    public QuizTree (Scanner inputFile)
    {
        this.root = makeQuiz(inputFile);
    }

    // pre: the method takes a scanner
    // post: recursively stores questions and results in QuizTreeNodes,
    //      returns a QuizTreeNode representing the first question
    private QuizTreeNode makeQuiz(Scanner inputfile)
    {
        String line = inputfile.nextLine();
        int score = Integer.parseInt(line.substring(line.indexOf("-")+1));

        QuizTreeNode node  = null;

        if (line.contains("/"))
        {
            String data = line.substring(0, line.indexOf("-"));
            QuizTreeNode left = makeQuiz(inputfile);
            QuizTreeNode right = makeQuiz(inputfile);
            node = new QuizTreeNode(data, score, left, right);
        }
        else if (line.contains("END"))
        {
            String data = line.substring(line.indexOf(":")+1, line.indexOf("-"));
            node = new QuizTreeNode(data, score);
        }
        return node;

    }

    // The method takes a scanner and allows user to take imported quiz; does not return anything.
    public void takeQuiz (Scanner console)
    {
        takeQuiz(console, root, 0);
    }

    // pre: takes a scanner, a QuizTreeNode, and an int for user's score
    // post: asks questions and takes input, inputs are case-insensitive; prompts quiz questions
    //      recursively, shows final result and score at the end; does not return anything
    private void takeQuiz (Scanner console, QuizTreeNode curr, int totalScore)
    {

        if (curr.left != null & curr.right != null)
        {
            String choice1 = curr.data.substring(0, curr.data.indexOf("/"));
            String choice2 = curr.data.substring(curr.data.indexOf("/") + 1);

            System.out.println("Do you prefer " + choice1 + " or " + choice2 + "?");
            String response = console.nextLine();
            response = response.toLowerCase();
            if (response.equals(choice1.toLowerCase()))
            {
                takeQuiz(console, curr.left, totalScore + curr.score);
            }
            else if (response.equals(choice2.toLowerCase()))
            {
                takeQuiz(console, curr.right, totalScore + curr.score);
            }
            else{
                System.out.println("Invalid Response; try again.");
                takeQuiz(console, curr, totalScore);
            }
        }
        else{
            System.out.println("Your result is: " + curr.data);
            System.out.println("Your score is: " + (totalScore + curr.score));
        }
    }
    
    // The method takes a PrintStream object and exports the current quiz file;
    //      does not return anything.
    public void export(PrintStream outputFile)
    {
        export(root, outputFile);
    }

    // pre: takes a QuizTreeNode and a PrintStream
    // post: recursively prints the quiz content on export file; does not return anything
    private void export(QuizTreeNode curr, PrintStream outputFile)
    {
        if (curr.left != null && curr.right != null)
        {
            outputFile.println(curr.data + "-" + curr.score);
            export(curr.left, outputFile);
            export(curr.right, outputFile);
        }
        else
        {
            outputFile.println("END:" + curr.data + "-" + curr.score);
        }
    }

    // The method adds new question and responses for the question to the quiz. It takes four Strings
    // that are response to replace, the replacing data, and its left and right results; does
    // not return anything
    public void addQuestion(String toReplace, String choices, String leftResult, String rightResult)
    {
        root = addQuestion(toReplace, choices, leftResult, rightResult, root);
    }

    // pre: takes four Strings that are response to replace, the replacing data,
    //      and its left and right results
    // post: checks each QuizTreeNode to replace recursively, replaces target QuizTreeNode,
    //      adds new responses; returns root QuizTreeNode, the first question, of the quiz
    private QuizTreeNode addQuestion(String toReplace, String choices, String leftResult, 
    String rightResult, QuizTreeNode curr)
    {
        if (curr == null)
        {
            return null;
        }
        if (toReplace.equals(curr.data))
        {
            String data = choices.substring(0, choices.indexOf("-"));
            int score = Integer.parseInt(choices.substring(choices.indexOf("-")+1));

            String leftData = leftResult.substring(0, leftResult.indexOf("-"));
            int leftScore = Integer.parseInt(leftResult.substring(leftResult.indexOf("-") + 1));
            String rightData = rightResult.substring(0, rightResult.indexOf("-"));
            int rightScore = Integer.parseInt(rightResult.substring(rightResult.indexOf("-") + 1));
            QuizTreeNode left = new QuizTreeNode(leftData, leftScore);
            QuizTreeNode right = new QuizTreeNode(rightData, rightScore);

            curr = new QuizTreeNode(data, score, left, right);
            
        }
        else
        {
            curr.left = addQuestion(toReplace, choices, leftResult, rightResult, curr.left);
            curr.right = addQuestion(toReplace, choices, leftResult, rightResult, curr.right);
        }
        
        return curr;

    }

    // The method calculates and prints the percentage of the full score of the quiz
    // that each result contains; does not take argument and does not return anything.
    public void creativeExtension()
    {
        int total = totalScore(root);
        creativeExtension(root, total, 0);
    }

    // pre: takes a QuizTreeNode, an int representing full score of the quiz, and a double keeping
    //      track of current percentage calculation
    // post: prints out the percentage of the full score each result contains; does not return anything
    private void creativeExtension(QuizTreeNode curr, int total, double currScore)
    {
        if (curr.left == null && curr.right == null)
        {
            currScore += curr.score;
            System.out.println(curr.data + ": " + roundTwoPlaces((currScore/total)*100) + "%");
        }
        else
        {
            creativeExtension(curr.left, total, currScore + curr.score);
            creativeExtension(curr.right, total, currScore + curr.score);
        }
    }

    // The methods takes a QuizTreeNode and calculates full score of the quiz; returns the full
    // score int.
    private int totalScore(QuizTreeNode curr)
    {
        if (curr.left == null && curr.right == null)
        {
            return curr.score;
        }
        return curr.score + totalScore(curr.left) + totalScore(curr.right);
    }

    // PROVIDED
    // Returns the given percent rounded to two decimal places.
    private double roundTwoPlaces(double percent) {
        return (double) Math.round(percent * 100) / 100;
    }

    // The class is a QuizTreeNode object, represents each question or response of the quiz.
    public class QuizTreeNode
    {
        public final String data;
        public final int score;
        public QuizTreeNode left;
        public QuizTreeNode right;

        // The constructor takes a string as data, an int as score, two left and right linked
        // QuizTreeNodes; make a new QuizTreeNode object representing a question.
        public QuizTreeNode(String data, int score, QuizTreeNode left, QuizTreeNode right)
        {
            this.data = data;
            this.score = score;
            this.left = left;
            this.right = right;
        }

        // The constructor takes a string as data, an int as score; make a new QuizTreeNode
        // object representing a response.
        public QuizTreeNode(String data, int score)
        {
            this(data, score, null, null);
        }

    }
}