package com.maxdidato.crackthecodeinterview.linkedlists;

import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.LinkedList;
import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * Give two (singly) linked lists, determine if the two list intersect. Return the intersecting node.
 * Note that the intersection is defined basaed on reference, not value. That is, if the kth element of the first linked
 * list is the exact same node (by reference) as the jth of the second linked list, then they are intersecting
 */
public class Intersection {

    /*
      First thing we need to visualise is how two lists intersect. Two lists intersect when one of their nodes is the same
      AND all the subsequent nodes also belong to both lists. You cannot have two lists intersecting only by one node,
      unless that node is the tail of both lists. This is because otherwise the intersecting node would need to have two
      pointers to next.
      Having said that the naive way of solving it would be to loop through one list (the longest maybe) putting every node
      in a set and then, looping through the second list checking if the current node is in the set. If it is return it.
      This would take O(N+M) where N is the size of the first list an M the size of the second.
      It would also require O(N) space (where N is the size of the longest list).

      A better approach consist in figuring out the size of the two lists. Calculate the difference and move the head of
      the longest list a number of nodes equal to the difference. In this way the head of the two lists will be in a position
      where the size is the same for both.
      We can now move each list pointer up of one until we either find the intersecting node or we reach the end
      If the size of the list is not known we need to calculate it so the complexity is still O(N+M) but space is O(1)
      If the size is known then complexity is O(N) where N is the size of the longest list
     */
    public Node<Integer> intersect(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        Node<Integer> longer = list1.head();
        Node<Integer> shorter = list2.head();
        if (list2.size() > list1.size()) {
            longer = list2.head();
            shorter = list1.head();
        }
        for (int i = 0; i < Math.abs(list1.size() - list2.size()); i++) {
            longer = longer.next();
        }
        while (longer != null && longer != shorter) {
            longer = longer.next();
            shorter = shorter.next();
        }
        return longer;
    }


    public static void main(String[] args) {
        int[] initialize = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(10);
        list2.add(11);
        for (int i = 0; i < initialize.length; i++) {
            Node<Integer> nodeToAdd = new Node<>(initialize[i]);
            list1.add(nodeToAdd);
            if (i == 5) {
                list2.add(nodeToAdd);
            }
        }
        list2.setSize(5);
        Node<Integer> intersect = new Intersection().intersect(list1, list2);
        System.out.println(intersect.getData());
    }
}
