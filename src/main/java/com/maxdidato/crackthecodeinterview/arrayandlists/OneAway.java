package com.maxdidato.crackthecodeinterview.arrayandlists;

import java.util.HashMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * This is simple to solve with an hashmap. We don't need to try all possible operations between the two string but
 * we can count the differences.
 * We select the longest string as the one to compare then we put all the characters in an hashmap and where the value
 * is the number of occurrences of that character in the string.
 * We then loop to the other string and we decrease the value every time we find one of the char in the hashmap.
 * If the sum of the values is 1 then the result is true.
 * We could improve the best case by preliminary checking the size of the two string differs maximum by 1
 * EXAMPLE
 * Input: "Mr John Smith    "
 * Output: "Mr%20John%20Smith"
 */

public final class OneAway
{
    public boolean oneAway(String a, String b){
       char[] toCompare;
       char[] other;
       if (a.length() >= b.length()){
           toCompare = a.toCharArray();
           other = b.toCharArray();
       }else{
           toCompare = b.toCharArray();
           other = a.toCharArray();
       }
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < toCompare.length; i++){
            map.computeIfPresent(toCompare[i], (x,n) -> n+1);
            map.putIfAbsent(toCompare[i], 1);
        }
        for (int i = 0; i< other.length; i++){
            map.computeIfPresent(other[i], (x,n) -> n-1);
        }
        int result = map.values().stream().collect(Collectors.summingInt(Integer::intValue));
        return result == 1;

    }


    public static void main(String[] args)
    {
        String a = "pale";
        String b = "bake";
        System.out.println(new OneAway().oneAway(a,b));

    }
}


