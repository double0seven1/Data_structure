package com.lzm.ds.Array;

import java.util.Collection;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        // Queue queue = new Queue();
        // queue.addElement(1);
        // queue.addElement(2);
        // queue.addElement(3);
        // queue.addElement(4);
        // queue.addElement(5);
        // queue.show();
        // queue.getQueue();
        // queue.getQueue();
        // queue.getQueue();
        // queue.getQueue();
        // queue.getQueue();
        //
        // try {
        //     Thread.sleep(1000);
        // } catch (InterruptedException e) {
        //     throw new RuntimeException(e);
        // }
        // queue.addElement(9);
        // queue.addElement(9);
        // queue.show();

        // CircleQueue circleQueue = new CircleQueue();
        // circleQueue.add(1);
        // circleQueue.add(2);
        // circleQueue.add(3);
        // circleQueue.get();
        // circleQueue.get();
        // circleQueue.add(4);
        // circleQueue.add(5);
        // circleQueue.add(6);
        // circleQueue.add(7);
        // circleQueue.show();

        ConLinked_list conLinkedList = new ConLinked_list();
        conLinkedList.addNode(new Node(1,1));
        conLinkedList.addNode(new Node(6,2));
        conLinkedList.addNode(new Node(9,3));
        conLinkedList.addNode(new Node(24,3));
        conLinkedList.addNode(new Node(64,3));
        conLinkedList.addNode(new Node(6,3));
        conLinkedList.addNode(new Node(2,3));
        conLinkedList.addNode(new Node(100,3));
        conLinkedList.addNode(new Node(4,4));
        conLinkedList.addNode(new Node(6,6));
        conLinkedList.addNode(new Node(7,7));
        conLinkedList.showNodes();
        System.out.println("---------------------------------");
        conLinkedList.recurList(conLinkedList.head);
        // conLinkedList.reverseLinkedList();
        // conLinkedList.showNodes();

        Stack<String> stack = new Stack<>();

    }
}
