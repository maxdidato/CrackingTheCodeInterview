package com.maxdidato.crackthecodeinterview.arrayandlists;

/**
 *
 * Assume you have a method isSubstring which checks if one word is a substring of another,. Given two strings, s1 s2
 * write code to check if s2 is a rotation of s1 using only one call to isSubstring
 */

public final class IsRotation {
    /*
    This requires some explanation.
    When rotating a string we basically split s1 at some index and s2 must be equal to the reverse of the two
    So for example if we decompose s1 in s1 = x + y then s2 = y + x
    Eg. s1 = waterbottle  s2 = bottlewater
    s1 = water + bottle
    s2 = bottle + water
    For s2 to be a rotation then it must be contained in x + y + x + y  -> s1 + s1
    We use contains instead of the hypothetical isSubstring as it is the same thing
    The complexity is the complexity of contains which is O(A+B)
     */
    public boolean isRotation(String s1,String s2) {
        if (s1.length()!=s2.length() || s1.length() == 0) return false;
        String s1s1 = s1+s1;
        return s1s1.contains(s2);
    }


    public static void main(String[] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        System.out.println(new IsRotation().isRotation(s1,s2));

    }
}


