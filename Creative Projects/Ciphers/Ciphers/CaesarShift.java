// Amelia Li
// 01/25/2024
// CSE 123
// TA: Jake Page
// This class represents a CaesarShift cipher, that is able to encrypt a plain
//  text and decrypt an encrypted text.

public class CaesarShift extends Cipher
{

    private int shifter;

    //construct a new CaesarShift cipher with input int
    public CaesarShift (int shift)
    {  
        shift %= Cipher.TOTAL_CHARS;
        this.shifter = shift;
    }

    //pre: takes an input string to encrypt
    //post: applying CaesarShift encryption on input string, return the new string
    //  created for encryption result
    public String encrypt(String input)
    {

        String encrypted = "";

        //get the displacement by manipulating with shifter and MIN_CHAR
        for (int i = 0; i < input.length(); i++)
        {
            int c = (int)input.charAt(i);
            char o = (char)(Cipher.MIN_CHAR + (c + shifter - Cipher.MIN_CHAR) % Cipher.TOTAL_CHARS);

            encrypted += o;
        }

        return encrypted;
    }

    //pre: takes an input string to decrypt
    //post: applying CaesarShift decryption on input string, return the new string
    //  created for decryption result
    public String decrypt(String input)
    {
        String decrypted = "";

        for (int i = 0; i < input.length(); i++)
        {
            int o = (int)input.charAt(i);

            //get initial displacement by subtracting shifter and MIN_CHAR
            //mod by TOTAL_CHARS to reset displacement
            char c = (char)(Cipher.MIN_CHAR + (o - shifter - Cipher.MIN_CHAR + Cipher.TOTAL_CHARS) % 
            Cipher.TOTAL_CHARS);

            decrypted += c;
        }


        return decrypted;
    }
}