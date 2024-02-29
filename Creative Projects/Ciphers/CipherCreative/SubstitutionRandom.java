// Amelia Li
// 01/25/2024
// CSE 123
// TA: Jake Page
// This class represents a SubstitutionRandom cipher that uses a randomly shuffled
//  shifter, is able to encrypt plain text and decrypt encrypted text.

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class SubstitutionRandom extends Cipher {

    private int digits;
    
    //pre: take input int, check and throw IllegalArgumentException if provided key
    //  is less than or equal to 0 or greater than 9
    //post: construct new SubstitutionRandom cipher with input int
    public SubstitutionRandom(int digits) {

        if (digits <= 0 || digits > 9)
        {
            throw new IllegalArgumentException();
        }

        this.digits = digits;

    }

    //pre: takes an input string to encrypt
    //post: creates seeds based on digits and generates shifter; then uses substitution
    //  encryption method on input string and shifter, returns encrypted string with seed
    //  in front
    public String encrypt(String input) {
        //throw new UnsupportedOperationException();

        String encrypted = "";

        int min = (int)Math.pow(10, digits - 1);
        int max = (int)Math.pow(10, digits) - 1;

        Random r = new Random();
        int seed = r.nextInt(max - min + 1) + min;

        Random rand = new Random(seed);
        
        List<Character> chars = new ArrayList<>();

        for (int i = MIN_CHAR; i <= MAX_CHAR; i++)
        {
            chars.add((char)i);
        }

        Collections.shuffle(chars, rand);

        String shifter = "";
        for (char c : chars)
        {
            shifter += c;
        }

        char[] inputArr = new char[input.length()];
        for(int i = 0; i < input.length(); i++) {
            inputArr[i] = input.charAt(i);
        }

        for (char c : inputArr)
        {
            int temp = ((int)c) - MIN_CHAR; 
            encrypted += shifter.charAt(temp);
        }

        return seed + encrypted;

    }
    
    //pre: takes an input string to decrypt
    //post: gets seeds based on digits for Random and generates shifter for decryption;
    //  then uses substitution decryption method on input string and shifter, returns
    //  decrypted string
    public String decrypt(String input) {
        //throw new UnsupportedOperationException();
        
        String decrypted = "";

        int seed = Integer.parseInt(input.substring(0, digits));

        input = input.substring(digits);

        Random rand = new Random(seed);

        List<Character> chars = new ArrayList<>();

        for (int i = MIN_CHAR; i <= MAX_CHAR; i++)
        {
            chars.add((char)i);
        }

        Collections.shuffle(chars, rand);

        String shifter = "";
        for (char c : chars)
        {
            shifter += c;
        }


        char[] inputArr = input.toCharArray();


        for (char c : inputArr)
        {
            char temp = (char)(MIN_CHAR + shifter.indexOf(c));
            decrypted += temp;
        }


        return decrypted;
    }
}