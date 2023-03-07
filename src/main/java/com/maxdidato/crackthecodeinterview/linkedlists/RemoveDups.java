package com.maxdidato.crackthecodeinterview.linkedlists;

import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.LinkedList;
import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * Write code to remove duplicates from an unsorted linked list
 * FOLLOW UP
 * How would you solve the problem if a temporary buffer is not allowed?
 */
public class RemoveDups {

    /*
        Tackling the problem with a buffer is quite straightforward.
        We loop through the list and we put any value we encounter in a set.
        Before doing so we check if the value is already in the set, if it is then we delete the node and move on
        If we don't we add it to the set  Complexity: O(N) and space O(N)
     */
    public void removeDups(LinkedList<Integer> list){
        if (list.size()==0) return;
       Node<Integer> current = list.head();
       Set<Integer> dups = new HashSet<Integer>();

        while (current.hasNext()){
            dups.add(current.getData());
            current = current.next();
            if (dups.contains(current.getData())){
                list.remove(current);
            }
        }
    }

    /*
    If no buffer is allowed things get a little more complicated. The solution would be to loop through
    every element of the list and, with a nested loop, we delete any value == to the current node.
    This would be O(N2)
    Since this is a list the loop and inner loop "indexes" are, respectively, the current node and the runner node.
    The current node points to the element under examination for dups, the runner node is the inner loop of the algorithm
    and will move from current to the end looking for duplicates.

     */
    public void removeDupsNoBuffer(LinkedList<Integer> list){
        if (list.size() == 0) return;
        Node<Integer> current = list.head();
        Node<Integer> runner;

        while (current!=null) {
            runner = current.next();
            while (runner != null) {
                if (runner.getData() == current.getData()) {
                    list.remove(runner);
                }
                runner = runner.next();
            }
            current = current.next();
        }
    }


    public static void main(String[] args) {
        int[] initialize = new int[]{10,2,52,3,2,64,3,10,52,10};
        LinkedList<Integer> list = new LinkedList<>();
        for (int i=0; i< initialize.length;i++){
            list.add(initialize[i]);
        }
        list.printContentToStOut();
        new RemoveDups().removeDupsNoBuffer(list);
        list.printContentToStOut();
    }
}
