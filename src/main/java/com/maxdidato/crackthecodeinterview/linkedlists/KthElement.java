package com.maxdidato.crackthecodeinterview.linkedlists;

import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.LinkedList;
import com.maxdidato.crackthecodeinterview.linkedlists.datastructure.Node;

/*
Implement an algorithm to find the kth to last element of a single linked list.
 */
public class KthElement {

    /*
    We consider k being the kth element starting from the last one.
    So, for example, in a list of 5 elements 1->2->3->4->5 , k=2 will return 3 (2 positions from 5)
    k=0 means the tail of the list
    We assume we don't know the size of the list (otherwise it would be very basic) so we have to loop through the entire
    list to find out what is the tail. The algorithm will take O(N)
     */

    /*
    The iterative solution will use the same node and runner concept already used.
    We know that the node to return will be k position from the tail.
    We use a node to point the candidate node and a runner node position k nodes ahead.
    If the runner is actually the tail then we return the node. Otherwise we move on till the runner is null
    If the list size is < then k the method will return null
     */
    public Node<Integer> kthElement(int k, LinkedList list){
        Node<Integer> node = list.head();
        Node<Integer> runner = getKElementFromNode(node,k);

        while (runner!=null){
            if (!runner.hasNext()){
                return node;
            }else{
                node = node.next();
                runner = runner.next();
            }
        }
        return null;
    }

    private Node<Integer> getKElementFromNode(Node node, int k){
        if (node==null){
            return null;
        }
        Node toReturn = node;
        for (int i =0; i<k;i++){
            if (toReturn.next()!=null) {
                toReturn = toReturn.next();
            }else{
                return null;
            }
        }
        return toReturn;
    }

    /*
    The recursive solution is more elegant but it will take O(N) space (we need to put in the stack every call till
    the end of the list.

    To tackle the problem recursivly we need to push each node.next() in the stack and once the node is null, meaning that
    the previous node was the tail, we assign the index = -1 (kind of reverse index).
    Each recursive call will invoke the method on the node.next() and it will increase the index first.
    We use a wrapper class Index since we cannot return both the node and the index. So we use a wrapper class for the index
    so to leverage the side effect invokation.
     */
    public Node<Integer> kthElementRecursive(int k, LinkedList list){
        return kthElementRecursive(k,list.head(),new Index());
    }

    public Node<Integer> kthElementRecursive(int k, Node node, Index index){
        if (node == null){
            index.i=-1;
            return null;
        }else{
            index.i = index.i + 1;
            if (k == index.i){
                return node;
            }else{
                return kthElementRecursive(k,node.next(),index);
            }
        }
    }

    private class Index{
        int i;
    }
    public static void main(String[] args) {
        int[] initialize = new int[]{1,2,3,4,5,6,7,8,9,10};
        LinkedList<Integer> list = new LinkedList<>();
        for (int i=0; i< initialize.length;i++){
            list.add(initialize[i]);
        }
        System.out.println(new KthElement().kthElementRecursive(5,list).getData());
    }
}
