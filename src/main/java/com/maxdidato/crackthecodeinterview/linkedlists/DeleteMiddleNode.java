package com.maxdidato.crackthecodeinterview.linkedlists;

import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.LinkedList;
import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * Implement an algorithm to delete a node in the middle (any node not being head or tail)
 * of a singly linked list, given only access to that node
 */
public class DeleteMiddleNode {

    /*
        This is a quite simple problem to solve. Since we have only access to the node to delete and being a single
        linked list, we have no way of accessing the previous node. So we can simply copy the value of the next node
        into the node to delete and set the next node to the toDelete.next().next(). In this way we have effectively
        deleted the node.
        Of course this won't work for the tail
     */
    public void deleteMiddleNode(Node toDelete){
      if (toDelete.hasNext()) {
          toDelete.setData(toDelete.next().getData());
          toDelete.next(toDelete.next().next());
      }
    }

    public static void main(String[] args) {
        int[] initialize = new int[]{1,2,3,4,5,6,7,8,9,10};
        LinkedList<Integer> list = new LinkedList<>();
        for (int i=0; i< initialize.length;i++){
            list.add(initialize[i]);
        }
        list.printContentToStOut();
        Node toDelete = list.head();
        for (int i = 0 ; i < initialize.length/2; i++){
            toDelete = toDelete.next();
        }
        new DeleteMiddleNode().deleteMiddleNode(toDelete);
        list.printContentToStOut();
    }
}
