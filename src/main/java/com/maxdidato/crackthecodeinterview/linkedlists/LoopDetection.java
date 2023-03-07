package com.maxdidato.crackthecodeinterview.linkedlists;

import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.LinkedList;
import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.Node;

import java.util.HashSet;
import java.util.Set;

/**
 Given a circular linked list, implement an algorithm that returns the node at the beginning of the loop
 DEFINITION:
 Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so as to make
 a loop in the linked list.
 EXAMPLE:
 Input A->B->C->D->E->C (the same C as earlier)
 Output: C
 */
public class LoopDetection {

    /*
       The brute force solution requires to loop through all the nodes, saving the already visited nodes. (for example
       in a set) When an already visited node is met we return it.
       This solution takes O(N) time and O(N) space. In java this is a little tricky as the equals method of the node
       needs to be implemented such as two nodes are the same if node1 == node2 (same pointer)

       A better solution is a variation of the loop detection algorithm in a linked list.
       To determine if a list has a loop we can use the fast and slow runners approach. We start from the head, and the
       slow runner moves one node per time while the fast runner moves two nodes per time. If there is a loop in the list
       the two pointers will point at the same node eventually and that means there is a loop.

       We can apply this concept to this problem.
       The list MUST have an initial part without loop and the loop will start at some point. If it wasn't like that
       we wouldn't be able to tell which node is the start of the loop as the loop is the first thing in the list.

       Let's say there are k nodes before the start of the loop. This  means that, from the head, we need to move k nodes
       and we will be at the beginning of the loop.
       When the slow and fast pointers will meet?
       When the slow runner enters the loop after k steps, the fast runner is going to be 2k steps and, therefore,
        2k -k = k steps in the loop. Or, more
       precisely, k mod loop_size (since k might be more than loop size). Lets say K = k % loop_size
       At each step fast runners will be closer to slow runner by one. So they will meet  after loop_size - K.
       So the collision point is k nodes before the beginning of the loop.
       The head of the list is k nodes from the beginning of the loop.
       So, once the two nodes meet in the collision point, we can move one runner and the head one node each until they
       meet. That is the beginning of the loop
     */
    public Node loop(Node list){
       Node head = list;
       Node slow = head.next();
       Node fast = head.next().next();

       try {
           while (slow != fast) {//let's move the two runners until they meet
               fast = fast.next().next();
               slow = slow.next();
           }
           while (head != fast) {// now let's move one by one unti the beginning of the loop
               head = head.next();
               fast = fast.next();
           }
           return head;
       }catch (NullPointerException e){
           System.out.println("The list doesn't have a loop");
           return null;
       }
    }



    public static void main(String[] args) {
        Node a = new Node('a');
        Node b = new Node('b');
        Node c = new Node('c');
        Node d = new Node('d');
        Node e = new Node('e');

        a.next(b);
        b.next(c);
        c.next(d);
        d.next(e);
        e.next(c);

        System.out.println(new LoopDetection().loop(a).getData());
    }
}
