package com.lzm.ds.Array;

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
        conLinkedList.addNode(new Node(4,1));
        conLinkedList.addNode(new Node(2,2));
        conLinkedList.addNode(new Node(3,3));
        conLinkedList.addNode(new Node(1,3));
        // conLinkedList.delNode(2);
        conLinkedList.showNodes();
    }
}
