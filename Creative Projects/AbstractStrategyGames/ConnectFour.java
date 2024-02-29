// Amelia Li
// 01/24/2024
// CSE 123 
// C1: Abstract Strategy Games
// TA: Jake Page
// A class that represents a Connect Four game and implements the
//  AbstractStrategyGame interface

import java.util.Scanner;

public class ConnectFour implements AbstractStrategyGame
{

    private String[][] board;
    private boolean p1turn;

    //pre: takes no argument
    //post: constructs a new ConnectFour game
    public ConnectFour()
    {
        p1turn = true;
        board = new String[][]{{"⚪️","⚪️","⚪️","⚪️","⚪️","⚪️","⚪️"},
                             {"⚪️","⚪️","⚪️","⚪️","⚪️","⚪️","⚪️"},
                             {"⚪️","⚪️","⚪️","⚪️","⚪️","⚪️","⚪️"},
                             {"⚪️","⚪️","⚪️","⚪️","⚪️","⚪️","⚪️"},
                             {"⚪️","⚪️","⚪️","⚪️","⚪️","⚪️","⚪️"},
                             {"⚪️","⚪️","⚪️","⚪️","⚪️","⚪️","⚪️"}};
    }

    //pre: takes no argument
    //post: returns a string containing instruction of the game
    public String instructions()
    {
        String instruction = "";
        
        instruction += "Player 1 is 🔴 and goes first, Player 2 is 🟡. Spaces shown as ⚪️\n";
        instruction += "are empty. Choose whether to remove a token by enter 0 or add a\n";
        instruction += "token by enter 1. The token will be placed at the bottom-most\n";
        instruction += "available space in the column. Choose where to put token by typing\n";
        instruction += "a column number, where (0, 0) is the upper left and (5, 6) is the\n";
        instruction += "lower right. Choose which token to remove by typing its column\n";
        instruction += "number. The game ends when one player first connects four of\n";
        instruction += "their tokens horizontally, vertically, or diagonally; or when \n";
        instruction += "the board is full, the game ends in a tie.";

        return instruction;
    }

    //pre: takes no argument
    //post: returns a string showing current state of the game board
    public String toString()
    {
        String result = "";

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                result += board[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }

    //pre: takes no argument
    //post: returns a boolean variable representing whether the game is over
    public boolean isGameOver()
    {
        return getWinner() >= 0;
    }

    //pre: takes no argument
    //post: returns the winner of the game as int; 1 if player 1 (🔴), 2 if 
    //  player 2 (🟡), 0 if the game is a tie, -1 if the game is not over
    public int getWinner()
    {

        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length - 3; j++)
            {
                if (board[i][j] != "⚪️")
                {
                    if ((board[i][j] == board[i][j + 1]) && (board[i][j]
                    == board[i][j + 2]) && (board[i][j] == board[i][j + 3]))
                    {
                        if (board[i][j].equals("🔴"))
                        {
                            return 1;
                        }
                        else if (board[i][j].equals("🟡"))
                        {
                            return 2;
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < board[0].length; i++)
        {
            for (int j = 0; j < board.length - 3; j++)
            {
                if (board[j][i] != "⚪️")
                {
                    if (board[j][i] == board[j + 1][i] && 
                    board[j][i] == board[j + 2][i] && board[j][i]
                     == board[j + 3][i]) 
                    {
                        if (board[j][i].equals("🔴"))
                        {
                            return 1;
                        }
                        else if (board[j][i].equals("🟡"))
                        {
                            return 2;
                        }
                    }                   
                }
                
            }
        }

        for (int i = 3; i < board.length; i++)
        {
            for (int j = 0 ; j < board[0].length - 3; j++)
            {
                if (board[i][j] != "⚪️")
                {
                    if (board[i][j] == board[i-1][j+1] && board[i][j] 
                    == board[i-2][j+2] && board[i][j] == board[i-3][j+3])
                    {
                        if (board[i][j].equals("🔴"))
                        {
                            return 1;
                        }
                        else if (board[i][j].equals("🟡"))
                        {
                            return 2;
                        }
                    }
                }
            }
        }

        for (int i = 3; i < board.length; i++)
        {
            for (int j = 3 ; j < board[0].length - 3; j++)
            {
                if (board[i][j] != "⚪️")
                {
                    if (board[i][j] == board[i-1][j-1] && board[i][j]
                     == board[i-2][j-2] && board[i][j] == board[i-3][j-3])
                    {
                        if (board[i][j].equals("🔴"))
                        {
                            return 1;
                        }
                        else if (board[i][j].equals("🟡"))
                        {
                            return 2;
                        }
                    }
                }
            }
        }

        //unfinished or tie
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == "⚪️")
                {
                    return -1;
                }
            }
        }

        return 0;
    }

    //pre: takes no argument
    //post: return number representing which player's turn the game is at, 1 if
    //  player 1's turn, 2 if player 2's turn, -1 if the game is over
    public int getNextPlayer()
    {
        if (isGameOver())
        {
            return -1;
        }
        else if (p1turn)
        {
            return 1;
        }
        return 2;
    }
    
    //pre: takes user input, ask if user wants to remove or place token and at
    //  which column; throw IllegalArgumentException if user tries to place or
    //  remove token on column out of board bounds
    //post: remove or place token based on input; change the player taking turn
    public void makeMove(Scanner input)
    {
        String token;
        if (getNextPlayer() == 1)
        {
            token = "🔴";
        }
        else
        {
            token = "🟡";
        }

        System.out.println("Remove (enter 0) one of your tokens or place (enter 1) one?");
        int choice = input.nextInt();
        if (choice == 0)
        {
            System.out.println("Remove token in which column?");
            int numCol = input.nextInt();
            if (numCol < 0 || numCol > 6)
            {
                throw new IllegalArgumentException();
            }
            remove(numCol, token);
        }
        else if (choice == 1)
        {
            System.out.println("Place token in which column?");
            int numCol = input.nextInt();
            if (numCol < 0 || numCol > 6)
            {
                throw new IllegalArgumentException();
            }
            makeMove(numCol, token);
        }

        p1turn = !p1turn;
    }

    //pre: takes an int column number that player wants to add token on and
    //  the player's token as string; throw IllegalArgumentException if the column
    //  is full with tokens
    //post: place the token at the given column's bottom-most available space
    public void makeMove(int numCol, String token)
    {

        if (board[0][numCol] == "🔴" || board[0][numCol] == "🟡")
        {  
            throw new IllegalArgumentException();
        }

        int usedLocation = -1;
        for (int i = board.length - 1; i >= 0 ; i--)
        {
            if (board[i][numCol] == "🔴" || board[i][numCol] == "🟡")
            {
                usedLocation = i;
            }
        }
        if (usedLocation == -1)
        {
            board[board.length-1][numCol] = token;
        }
        else
        {
            board[usedLocation-1][numCol] = token;
        }
    }


    //pre: takes a string as user's token and an int as the column number where the user wants to
    // remove their token; throws IllegalArgumentException if the player's token is not in the given
    // column
    //post: removes the token from the specified column and shifts the tokens above the removed
    // token downward
    public void remove(int col, String token)
    {
        boolean removed = false;
        int row = 0;
        for (int i = board.length-1; i >= 0; i--)
        {
            if (board[i][col] == token && removed == false)
            {
                board[i][col] = "⚪️";
                row = i;
                removed = true;
            }
        }
        
        if (!removed)
        {
            throw new IllegalArgumentException();
        }

        for (int i = row - 1; i >= 0; i--)
        {
            board[i + 1][col] = board[i][col];
        }
        board[0][col] = "⚪️";
    }

}