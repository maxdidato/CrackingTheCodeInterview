package com.maxdidato.crackthecodeinterview.linkedlists.datastructure;

public class LinkedList<T> {

    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    public T add(T toAdd){
        Node<T> nodeToAdd = new Node<>(toAdd);
        this.add(nodeToAdd);
        return toAdd;
    }

    public Node<T> add(Node<T> nodeToAdd){
        if (this.head == null){
            this.head = nodeToAdd;
            this.tail = nodeToAdd;
        }else{
            this.tail.next(nodeToAdd);
            this.tail = nodeToAdd;
        }
        this.size++;
        return nodeToAdd;
    }

    public Node<T> head(){
        return this.head;
    }

    public void head(Node<T> newHead){
        this.head = newHead;
    }

    public int size(){
        return size;
    }

    public void setSize(int size){
        this.size = size;
    }

    public Node<T> tail(){
        return this.tail;
    }

    public Node<T> remove(Node<T> toRemove){
        if (this.size == 0) return null;
        Node<T> cursor = this.head;
        Node<T> previous = this.head;
        Node<T> deleted = null;
        while (cursor != null) {
            if (cursor==toRemove ){
                previous.next(cursor.next());
                if (cursor==head){
                    this.head = cursor.next();
                }
                if (cursor==tail){
                    this.tail = previous;
                }
                cursor=null;
                size--;
            }else{
                previous = cursor;
                cursor = cursor.next();
            }
        }
        return deleted;
    }

    public void printContentToStOut(){
        if (size==0) return;
        Node<T> current = this.head;
        System.out.print(" "+this.head.getData());
        while (current.hasNext()){
            current = current.next();
            System.out.print(" "+current.getData());
        }
        System.out.println("");
    }
}
