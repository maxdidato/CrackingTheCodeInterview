package com.maxdidato.crackthecodeinterview.linkedlists.datastructure;

public class Node<T> {

    private T data;
    private Node<T> next;

    public Node(T data){
        this.data = data;
    }

    public boolean hasNext(){
        return this.next != null;
    }
    public void next(Node<T> next){
        this.next = next;
    }

    public Node<T> next(){
        return this.next;
    }

    public T getData(){
        return this.data;
    }

    public void setData(T data){
        this.data = data;
    }
}
