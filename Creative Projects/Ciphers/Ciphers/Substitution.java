// Amelia Li
// 01/25/2024
// CSE 123
// TA: Jake Page
// This class represents a Substitution cipher, that is able to encrypt a plain
//  text and decrypt an encrypted text.

import java.util.HashSet;
import java.util.Set;

public class Substitution extends Cipher
{
    
    private String shifter;

    //constructs a Substitution cipher with empty string shifter
    public Substitution()
    {
        this.shifter = "";
    }


    //pre: takes an input string shifter, throws IllegalStateException if shifter is null
    //  or empty, and throws IllegalArgumentException if provided shifter doesn't match
    //  encoding range, has duplicate characters, or any character falls outside encoding
    //  range
    //post: constructs a Substitution cipher with input shifter
    public Substitution (String shifter)
    {

        if (shifter == null || shifter == "")
        {
            throw new IllegalStateException();
        }

        //check if match encoding range, if not throw exception
        if (shifter.length() != Cipher.TOTAL_CHARS)
        {
            throw new IllegalArgumentException();
        }
        
        Set<Character> chars = new HashSet<>();

        for (int i = 0; i < shifter.length(); i++)
        {
            
            if (chars.contains(shifter.charAt(i)))
            {
                throw new IllegalArgumentException();
            }
            
            chars.add(shifter.charAt(i));
        }

        for (char c : chars)
        {
            if ((int)c < MIN_CHAR || (int)c > MAX_CHAR)
            {
                throw new IllegalArgumentException();
            }
        }

        this.shifter = shifter;
    }

    //pre: takes an input string shifter, checks and throws IllegalArgumentException if
    //  provided shifter doesn't match encoding range, has duplicate character, or falls
    //  outside encoding range
    //post: replaces old shifter string with input string
    public void setShifter(String shifter)
    {
        if (shifter.length() != Cipher.TOTAL_CHARS)
        {
            throw new IllegalArgumentException();
        }
        
        Set<Character> chars = new HashSet<>();

        for (int i = 0; i < shifter.length(); i++)
        {
            if (chars.contains(shifter.charAt(i)))
            {
                throw new IllegalArgumentException();
            }
            chars.add(shifter.charAt(i));
        }

        for (char c : chars)
        {
            if ((int)c < MIN_CHAR || (int)c > MAX_CHAR)
            {
                throw new IllegalArgumentException();
            }
        }

        this.shifter = shifter;
    }


    //pre: takes an input string, throws IllegalStateException if shifter is null or empty
    //post: applies substitution encryption on input string, returns the new string
    //  created for encryption result
    public String encrypt (String input)
    {
        if (this.shifter == null || this.shifter == "")
        {
            throw new IllegalStateException();
        }

        String encrypted = "";

        char[] inputArr = new char[input.length()];
        for(int i = 0; i < input.length(); i++) {
            inputArr[i] = input.charAt(i);
        }

        for (char c : inputArr)
        {
            
            int temp = ((int)c) - MIN_CHAR;
            encrypted += shifter.charAt(temp);
        }

        return encrypted;

    }


    //pre: takes an input string, throw IllegalStateException if shifter is null or empty
    //post: applying substitution decryption on input string, return the new string
    //  created for decryption result
    public String decrypt (String input)
    {
        if (this.shifter == null || this.shifter == "")
        {
            throw new IllegalStateException();
        }

        String decrypted = "";

        char[] inputArr = new char[input.length()];
        for(int i = 0; i < input.length(); i++) {
            inputArr[i] = input.charAt(i);
        }

        for (char c : inputArr)
        {
            char temp = (char)(MIN_CHAR + shifter.indexOf(c));
            decrypted += temp;
        }

        return decrypted;

    }

}