// Amelia Li
// 01/25/2024
// CSE 123
// TA: Jake Page
// This class represents a MultiCipher cipher, that is able to encrypt a plain
//  text through a list of other ciphers and decrypt the encrypted text
//  in reverse order of the list of ciphers.

import java.util.List;

public class MultiCipher extends Cipher
{
    private List<Cipher> ciphers;

    //pre: takes a list of Cipher objects, throw IllegalArgumentException if the
    //   list is null
    //post: construct new MultiCipher with input list of ciphers
    public MultiCipher(List<Cipher> ciphers)
    {
        if (ciphers == null)
        {
            throw new IllegalArgumentException();
        }

        this.ciphers = ciphers;
    }

    //pre: takes an input string to encrypt
    //post: use the input to encrypt through the list of ciphers, 
    //  return the string that's been encrypted
    public String encrypt(String input)
    {
        String encrypted = input;

        for (Cipher cipher : this.ciphers)
        {
            encrypted = cipher.encrypt(encrypted);
        }

        return encrypted;
    }

    //pre: takes an input string to decrypt
    //post: use the input to decrypt through the list of ciphers in reverse order,
    //  and return the string that's been decrypted
    public String decrypt(String input)
    {
        String decrypted = input;

        for (int i = ciphers.size() - 1; i >= 0; i--)
        {
            decrypted = ciphers.get(i).decrypt(decrypted);
        }

        return decrypted;
    }

}