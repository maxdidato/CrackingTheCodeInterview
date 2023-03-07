package com.maxdidato.crackthecodeinterview.arrayandlists;

/**
 * Write a method to replace all spaces in a string with '%20'. You may assume that the string has sufficent
 * space at the end to hold the additional characters, and that you are given the "true" length of the string.
 * (Note: if implementing in Java, please use a character array so that you perform this operation in place)
 * EXAMPLE
 * Input: "Mr John Smith    "
 * Output: "Mr%20John%20Smith"
 */

public final class Urlify
{
    public void urlify(char[] a, int size){
        //we don't need to scan the string to find the number of space. The shifting factor can be inferred
        int shiftFactor = a.length - size;
        for (int i=size-1; i>=0; i--){
            if (a[i] == ' '){
                /*If we encounter a space, the shift factor must be halved.
                For every whitespace, which takes 1 char, we will need to replace it with %20, which takes 3 chars.
                Therefore we need to move (3-1) * k, where k =  number of whitespaces left in the substring
                currently in consideration
                 */
                shiftFactor = shiftFactor / 2;
                /* If the shiftFactor is 1 that means there is not going to be anymore whitepsace going backwards.
                   Therefore we can set it to zero
                 */
                if (shiftFactor==1) shiftFactor=0;
                int c = i + shiftFactor;
                a[c] = '%';
                a[c+1] = '2';
                a[c+2] = '0';
                if (shiftFactor==0) break;//as said before, no more shifts are required now so we can end the loop
            }else{
                //We can shift the current char by the shift factor
                a[i+shiftFactor] = a[i];
            }
        }
    }


    public static void main(String[] args)
    {
        int size = 13;
        char[] input = "Mr John Smith    ".toCharArray();
        new Urlify().urlify(input,size);
        System.out.println(new String(input));
    }
}


