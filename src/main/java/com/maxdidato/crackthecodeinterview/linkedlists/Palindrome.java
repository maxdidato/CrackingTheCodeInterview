package com.maxdidato.crackthecodeinterview.linkedlists;

import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.LinkedList;
import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.Node;

import java.util.Stack;

/**
To solve this problem iteratively we can use the runner technique. A slow pointer which moves one node per time and
 a faster pointer which moves two nodes per time.
 In order to be a palindrome the first half list must be equal to the last half list reversed.
 We don't know the length of the list so one obvious way would be to calculate the length of the list and then iterate
 over it again putting all the letters in a stack until we reach the half. Then we compare each character of the second
 half with the popped valeu from the stack. If at the end the stack is empty the list is palindrome.
 If we want to avoid the two iterations of the list we can use the runner technique. If the faster pointer moves twice
 as fast of the slow one when the faster has reached the end the slow will be right in the middle. From that point on
 we can pop the stack to make the comparisons.
 */
public class Palindrome {

/*
     */
    public boolean palindrome(LinkedList<Character> list){
            Node<Character> slow = list.head();
            Node<Character> fast = list.head();

        Stack<Character> stack = new Stack<>();
            while (fast!=null){
                stack.add(slow.getData());
                if (fast.next()!=null) {
                    fast = fast.next().next();
                    slow = slow.next();
                }else{
                    fast=null;
                }
            }
            //Half list
            while (slow!=null){
                if (stack.pop()!=slow.getData()){
                    break;//if not equal we can skip the rest. The list is not palindrome
                }
                slow = slow.next();
            }
            return stack.isEmpty();
     }





    public static void main(String[] args) {
        char[] initialize = new char[]{'M','A','M','A','M','A','M'};
        LinkedList<Character> l1 = new LinkedList<>();
        for (int i=0; i< initialize.length;i++){
            l1.add(initialize[i]);
        }
        System.out.println(new Palindrome().palindrome(l1));
    }
}
