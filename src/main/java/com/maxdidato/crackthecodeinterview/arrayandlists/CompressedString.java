package com.maxdidato.crackthecodeinterview.arrayandlists;

/**
 * Implement a method to perform basic string compression using the counts of repeated characters. For example,
 * the string aabccccaaa would become a2b1c5a3. If the "compressed" string would not become smalle than the original
 * string, your method should return the original string. You can assume the string has only uppercase and lowercase
 * letters (a-z)
 */

public final class CompressedString {
    public String compress(String s) {
        StringBuilder compressedSB = new StringBuilder();
        char current = ' ';
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (current != s.charAt(i)) {
                if (count > 0) {
                    compressedSB.append(current).append(count);
                    count = 0;
                }
                current = s.charAt(i);
            }
            count++;
        }
        compressedSB.append(current).append(count);
        String compressedString = compressedSB.toString();
        return (compressedString.length() <= s.length() ? compressedString : s);
    }


    public static void main(String[] args) {
        String input = "aabccccaaa";
        System.out.println(new CompressedString().compress(input));

    }
}


