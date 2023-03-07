package com.maxdidato.crackthecodeinterview.linkedlists;

import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.LinkedList;
import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.Node;

/**
 *Write code to partition a linked list around a value x, such that all nodes less than x compare before all nodes
 * >= x. If x is contained within the list, the values of x only need to be after the elements less than x. The partition
 * element x can appear anywhere in the right partition, it does not need to appear between the left and right partitions.
 *
 * EXAMPLE
 * Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition=5]
 * Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8>
 */
public class Partition {

    /*
        Given all the constraints the problem can be easily solved in O(N).
        We keep track of the head and we loop through the list. If the value is < X then we move the node at the head.
        Eventually all the values < X will be before the values >= X
     */
    public void partition(LinkedList<Integer> list, int x){
        if (list.head()==null) return;
     Node<Integer> previous = list.head();
     Node<Integer> node = previous.next();
     while (node!=null){
         if (node.getData() < x){
             Node<Integer> toMove = node;
             previous.next(node.next());
             node = node.next();
             toMove.next(list.head());
             list.head(toMove);
         }else{
             previous = node;
             node = node.next();
         }
     }

    }

    public static void main(String[] args) {
        int[] initialize = new int[]{3,5,8,5,10,2,1,2,1};
        LinkedList<Integer> list = new LinkedList<>();
        for (int i=0; i< initialize.length;i++){
            list.add(initialize[i]);
        }
        list.printContentToStOut();


        new Partition().partition(list,5);
        list.printContentToStOut();
    }
}
