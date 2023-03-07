package com.maxdidato.crackthecodeinterview.linkedlists;

import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.LinkedList;
import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.Node;

/**
You have two numbers represented by a linked list, where each node contains a single digit. The digits
 are stored in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two
 numbers and returns the sum as linked list
 EXAMPLE
 Input: ( 7->1->6) + (5->9->2) Tha is 617 + 295
 Output: 2->1->9 That is 912
 Follow up
 Suppose the digits are stored in forward order. Repeat the above problem
 */
public class Sum {

    /*
       Assuming the input is made of two lists (one for each number) and the two numbers have same number of digits.
       We loop through the two lists and we sum the digits at the same position (starting from the head, the least
       significant digit).
       The correspondent digit of the solution is going to be the result of module of 10 for the sum of the digits.
       The same sum is divided by zero and that represent the remainder, which will be added to the next sum .

       For example
       Having two numbers in input

       7 -> 1 -> 6
       5 -> 9 -> 2    The result would be 912 ( 2->1->9)

       We start from the first node for both lists and sum them
       7 + 5 = 12
       We then apply module 10 -> 12 % 10 = 2
       2 Is going to be the first element of the solution
       We then divide the sum by 10 -> 12/10 = 1 That is the remainder
       2
       The next one is 9 + 1 + the remainder of 1 = 11
       Applying the same logic we have
       2 -> 1 and the remainder of 1
       For last 6 + 2 + 1 = 9
       2 -> 1 -> 9

     */
    public Node<Integer> sum(LinkedList<Integer> l1, LinkedList<Integer> l2){
     Node<Integer> n1 = l1.head();
     Node<Integer> n2 = l2.head();
     Node<Integer> previous = null;
     Node<Integer> result=null;
     Node<Integer> node = null;
     int remainder = 0;
     while (n1 != null && n2!=null){
         int sum = n1.getData() + n2.getData() + remainder;
         remainder = sum/10;
         node = new Node<>(sum % 10);
         if (previous!=null){
             previous.next(node);
         }
         if (result==null){
             result = node;
         }
         previous = node;
         n1 = n1.next();
         n2 = n2.next();
     }
     //If the last operation leaves a remainder then that needs to be added at the end (the result is going to have more digits)
     if (remainder!=0){
         Node<Integer> finalNode = new Node<>(remainder);
        node.next(finalNode);
     }
     return result;
     }

     public Node<Integer> sumReverse(LinkedList<Integer> l1, LinkedList<Integer> l2){
         Result result = sumReverse(l1.head(), l2.head());
         //As for the other method, if there is a remainder left we need to attach it to the head.
         if (result.remainder!=0){
             Node<Integer> integerNode = new Node<>(result.remainder);
             integerNode.next(result.node);
             return integerNode;
         }else{
             return result.node;
         }
     }

    /**
     * If we have the reverse order as in the title of the excercise we can implement a recusrive algorithm.
     * Between each recursion we need to carry over the sum mod 10 (contained in the node) and the remainder.
     * For this we create a Result class to contain the two information.
     * We recurisvely arrive at the end of the lists and we do the usual operation of adding the numbers. The value of the
     * tail node is going to be the sum mod 10 and the remainder sum / 10.
     * Every pop we connect the current node to the one coming from the previous recursion till the end.
     *
     */
     public Result sumReverse(Node<Integer> n1, Node<Integer> n2){
        if (n1.next()==null || n2.next() == null){
            int sum = n1.getData()+n2.getData();
            Node<Integer> integerNode = new Node<>(sum % 10);
            return new Result(integerNode,sum / 10);
        }else{
            Result result = sumReverse(n1.next(),n2.next());
            int sum = n1.getData() + n2.getData() + result.remainder;
            Node<Integer> integerNode = new Node<>(sum % 10);
            integerNode.next(result.node);
            return new Result(integerNode,sum/10);
        }
     }

     class Result{
        public Node<Integer> node;
        public int remainder;

        public Result(Node<Integer> node, int remainder){
            this.node = node;
            this.remainder = remainder;
        }
     }


    public static void main(String[] args) {
        int[] initialize = new int[]{7,1,6};
        LinkedList<Integer> l1 = new LinkedList<>();
        for (int i=0; i< initialize.length;i++){
            l1.add(initialize[i]);
        }
        initialize = new int[]{5,9,2};
        LinkedList<Integer> l2 = new LinkedList<>();
        for (int i=0; i< initialize.length;i++){
            l2.add(initialize[i]);
        }


        Node<Integer> result = new Sum().sum(l1,l2);
        while(result!=null){
            System.out.print(" "+result.getData());
            result = result.next();
        }

        System.out.println("");
        result = new Sum().sumReverse(l1,l2);
        while(result!=null){
            System.out.print(" "+result.getData());
            result = result.next();
        }
    }
}
