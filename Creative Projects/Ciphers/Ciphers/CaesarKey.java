// Amelia Li
// 01/25/2024
// CSE 123
// TA: Jake Page
// This class represents a CaesarKey cipher, that is able to encrypt a plain
//  text and decrypt an encrypted text.

import java.util.HashSet;
import java.util.Set;

import javax.sound.midi.MidiMessage;

public class CaesarKey extends Cipher
{
    
    private String key;

    //pre: take input string, check and throw IllegalArgumentException if provided
    //  key is null or empty, has duplicates, falls outside encoding range
    //post: construct new CaesarKey cipher with input string
    public CaesarKey(String key)
    {   
        if (key == null || key == "")
        {
            throw new IllegalArgumentException();
        }

        Set<Character> chars = new HashSet<>();

        for (int i = 0; i < key.length(); i++)
        {
            
            if (chars.contains(key.charAt(i)))
            {
                throw new IllegalArgumentException();
            }
            chars.add(key.charAt(i));
        }

        for (char c : chars)
        {
            if ((int)c < MIN_CHAR || (int)c > MAX_CHAR)
            {
                throw new IllegalArgumentException();
            }
        }

        this.key = key;

    }

    //pre: takes an input string to encrypt
    // post: creates shifter string based on key field, applying CaesarKey encryption
    //  on input string and returns the new string created for encryption result
    public String encrypt(String input)
    {

        String shifter = "";
        shifter += key;

        for (int i = MIN_CHAR; i <= MAX_CHAR; i++)
        {
            if (!shifter.contains(String.valueOf((char)i)))
            {
                shifter += (char)i;
            }
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


    //pre: takes an input string to decrypt
    //post: creates shifter string based on key field, applying CaesarKey decryption
    //  on input string and returns the new string created for decryption result
    public String decrypt(String input)
    {

        String shifter = "";
        shifter += key;

        for (int i = MIN_CHAR; i <= MAX_CHAR; i++)
        {
            if (!shifter.contains(String.valueOf((char)i)))
            {
                shifter += (char)i;
            }
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