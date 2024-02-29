// Amelia Li
// 02/08/2024
// CSE 123 
// Creative Project 2: Mondrian Art
// TA: Jake Page
// This class takes in the intended size for canvas and randomly generates a basic or complex
// artwork in the style of Mondrian.

import java.util.*;
import java.awt.*;

public class Mondrian {

    private Random rand = new Random();

    // The method accepts an array of color pixels and calls another helper method to generate
    // basic random Mondrian drawing.
    public void paintBasicMondrian (Color[][] pixels)
    {
        paintBasicMondrian(pixels, 0, 0, pixels.length, pixels[0].length, pixels.length,
         pixels[0].length);
    }

    // Pre: Accepts an array of color pixels, two ints as row numbers and two ints as column numbers
    //      as boundaries of the region being considered, and two ints for total rows and columns.
    // Post: Recursively divides the canvas into regions with widths and heights less than 1/4 of the
    //      total canvas width/height and fills with color to generate a basic random Mondrian drawing;
    //      chooses a random color among red, yellow, cyan, or white.
    private void paintBasicMondrian (Color[][] pixels, int row1, int col1, int row2, int col2, 
    int totalR, int totalC)
    {


        if (row2 - row1 >= totalR/4 || col2 - col1 >= totalC/4)
        {
            if (row2 - row1 >= totalR/4 && col2 - col1 >= totalC/4)
            //split both
            {
                int rRow = rand.nextInt(row2 - row1-2) + (row1 + 3);

                int rCol = rand.nextInt(col2 - col1-4) + (col1 + 5);

                
                paintBasicMondrian(pixels, row1, col1, rRow-1, rCol-1, totalR, totalC);
                paintBasicMondrian(pixels, row1, rCol+1, rRow-1, col2, totalR, totalC);
                paintBasicMondrian(pixels, rRow+1, col1, row2, rCol-1, totalR, totalC);
                paintBasicMondrian(pixels, rRow+1, rCol+1, row2, col2, totalR, totalC);

            }
            else if (row2 - row1 >= totalR/4 && col2 - col1 < totalC/4)
            //at east one-fourth the height, split rows
            {
                int rRow = rand.nextInt(row2 - row1-2) + (row1 + 3);

                paintBasicMondrian(pixels, row1, col1, rRow-1, col2, totalR, totalC);
                paintBasicMondrian(pixels, rRow+1, col1, row2, col2, totalR, totalC);


            }
            else if (row2 - row1 < totalR/4 && col2 - col1 >= totalC/4)
            //at east one-fourth the width, split columns
            {
                int rCol = rand.nextInt(col2 - col1-4) + (col1 + 5);

                paintBasicMondrian(pixels, row1, col1, row2, rCol-1, totalR, totalC);
                paintBasicMondrian(pixels, row1, rCol+1, row2, col2, totalR, totalC);

            }
            
        }
        else{
            //fill colors

            Color[] colors = new Color[] {Color.red, Color.yellow, Color.cyan, Color.white};

            int r = rand.nextInt(4);
            
            for (int i = row1; i < row2; i++)
            {
                for (int j = col1; j < col2; j++)
                {
                    pixels[i][j] = colors[r];
                }
            }
        }   
    }

    // The method accepts an array of color pixels and calls another helper method to generate
    // complex Mondrian drawing.
    public void paintComplexMondrian (Color[][] pixels)
    {
        paintComplexMondrian(pixels, 0, 0, pixels.length, pixels[0].length, 
        pixels.length, pixels[0].length);
    }
    
    
    // Pre: Accepts an array of color pixels, two ints as row numbers, and two ints as column numbers
    //      as boundaries of the region being considered, along with two ints for total rows and columns.
    // Post: Recursively divides the canvas into regions with widths and heights less than 1/4 of the
    //      total canvas width/height and fills with color to generate a complex random Mondrian drawing.
    //      If the upper bound of the region is located on the top 25% of the canvas, there's a 75% chance
    //      the region will be red and a 25% chance of being yellow, cyan, or white. If the region's upper
    //      bound is located between the top 25% and 50% of the canvas, there's a 75% chance the region will
    //      be yellow and a 25% chance of being red, cyan, or white. If the region's upper bound is located
    //      between the bottom 50% and 75% of the canvas, there's a 75% chance the region will be cyan and a
    //      25% chance of being red, yellow, or white. If the region's upper bound is located below the bottom
    //      75% of the canvas, there's a 75% chance the region will be white and a 25% chance of being red,
    //      yellow, or cyan.
    private void paintComplexMondrian (Color[][] pixels, int row1, int col1, int row2, int col2, 
    int totalR, int totalC)
    {
        if (row2 - row1 >= totalR/4 || col2 - col1 >= totalC/4)
        {
            if (row2 - row1 >= totalR/4 && col2 - col1 >= totalC/4)
            //split both
            {
                int rRow = rand.nextInt(row2 - row1-2) + (row1 + 3);

                int rCol = rand.nextInt(col2 - col1-2) + (col1 + 3);

                
                paintComplexMondrian(pixels, row1, col1, rRow-1, rCol-1, totalR, totalC);
                paintComplexMondrian(pixels, row1, rCol+1, rRow-1, col2, totalR, totalC);
                paintComplexMondrian(pixels, rRow+1, col1, row2, rCol-1, totalR, totalC);
                paintComplexMondrian(pixels, rRow+1, rCol+1, row2, col2, totalR, totalC);

            }
            else if (row2 - row1 >= totalR/4 && col2 - col1 < totalC/4)
            //at east one-fourth the height, split rows
            {
                int rRow = rand.nextInt(row2 - row1-2) + (row1 + 3);

                paintComplexMondrian(pixels, row1, col1, rRow-1, col2, totalR, totalC);
                paintComplexMondrian(pixels, rRow+1, col1, row2, col2, totalR, totalC);


            }
            else if (row2 - row1 < totalR/4 && col2 - col1 >= totalC/4)
            //at east one-fourth the width, split columns
            {
                int rCol = rand.nextInt(col2 - col1-2) + (col1 + 3);

                paintComplexMondrian(pixels, row1, col1, row2, rCol-1, totalR, totalC);
                paintComplexMondrian(pixels, row1, rCol+1, row2, col2, totalR, totalC);

            }
            
        }

        else
        {
            //fill colors
            Color c;

            double position = (double)row1/totalR;

            double d = Math.random();

            if (position <= 0.25)
            {
                if (d <= 0.75)
                {
                    c = Color.red;
                }
                else
                {
                    Color[] colors = new Color[] {Color.yellow, Color.cyan, Color.white};

                    int r = rand.nextInt(3);

                    c = colors[r];
                }
            }
            else if (0.25 < position && position <= 0.5)
            {
                if (d <= 0.75)
                {
                    c = Color.yellow;
                }
                else
                {
                    Color[] colors = new Color[] {Color.red, Color.cyan, Color.white};

                    int r = rand.nextInt(3);

                    c = colors[r];
                }

            }
            else if (0.5 < position && position < 0.75)
            {
                if (d <= 0.75)
                {
                    c = Color.cyan;
                }
                else
                {
                    Color[] colors = new Color[] {Color.red, Color.yellow, Color.white};

                    int r = rand.nextInt(3);

                    c = colors[r];
                }
            }
            else
            {
                if (d <= 0.75)
                {
                    c = Color.white;
                }
                else
                {
                    Color[] colors = new Color[] {Color.red, Color.yellow, Color.cyan};

                    int r = rand.nextInt(3);

                    c = colors[r];
                }

            }
            
            for (int i = row1; i < row2; i++)
            {
                for (int j = col1; j < col2; j++)
                {
                    pixels[i][j] = c;
                }
            }
        }   
    }
          
}