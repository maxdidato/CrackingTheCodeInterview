package com.maxdidato.crackthecodeinterview.arrayandlists;

import java.util.Locale;

/**
 * Given a string, write a function to check if it is a permutation of a palindrome. A palindrome is a word
 * or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters. The palindrome
 * does not need to be limited to just dictionary words
 * EXAMPLE
 * Input: Tact Coa
 * Output: True (permutations: "taco cat" , "atco cta", etc..)
 */

public final class PalindromePermutation {
    /*
    The key is to understand what makes a string a palindrome. A string is palindrome if the first half == second half
    backwards.
    The constraint is that there can be maximum one character with an odd number of occurrences.
    Assuming ASCII encoding, we create a map of characters and count the occurrences. If 0 or 1 chars have odd
    occurrences the answer is true, false otherwise
     */
    public boolean isPermOfPal(String s) {
        //We want to make sure no empty strigns
        assert (s != null && s.length() > 0);
        /*
            initialize the char map. ASCII encoding has maximum 256 chars. We could actually simplify this
            Even more by only the lowercase letters
         */
        int[] charMap = new int[256];
        //Removing spaces and making it lowercase
        char[] chars = s.toLowerCase().replace(" ", "").toCharArray();
        //one char string is true by default
        if (chars.length == 1) return true;
        //we now loop through the string counting the occurrences of each char
        for (int i = 0; i < chars.length; i++) {
            charMap[chars[i]]++;
        }
        int odds = 0;
        //we now count the chars occurring an odd number of times
        for (int i = 0; i < charMap.length; i++) {
            if (charMap[i] % 2 != 0)
                odds++;
        }
        //If less than 2 then the answer is true
        return odds < 2;
    }


    public static void main(String[] args) {
        String inputString = "Tact Coa";
        String inputString1 = "AtCo CtA";
        String inputString2 = "non palindrome";
        System.out.println(new PalindromePermutation().isPermOfPal(inputString));
        System.out.println(new PalindromePermutation().isPermOfPal(inputString1));
        System.out.println(new PalindromePermutation().isPermOfPal(inputString2));

    }
}


