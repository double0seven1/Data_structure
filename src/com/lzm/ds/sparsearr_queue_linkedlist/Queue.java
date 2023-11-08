package com.lzm.ds.sparsearr_queue_linkedlist;

public class Queue {
    /*用数组来模拟队列*/
    // front是队列头，指向头数据的前一个位置
    private int front = -1;
    // rear是队列尾，指向最尾的数据
    private int rear = -1;

    private final int maxSize = 5;
    private int arr[] = new int[maxSize];

    public boolean isEmpty() {
        return front == rear;
    }

    // 我还在想为啥是maxSize - 1，数组下标从0开始。
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 增加一个元素:
    public void addElement(int i) {
        if(isFull()){
            System.out.println("队列已满，请稍后");
            return;
        }
        rear++;
        arr[rear] = i;
    }
    // 出队列
    public int getQueue() {
        if(isEmpty()) {
            throw new RuntimeException();
        }
        front++;
        return arr[front];
    }

    public void show(){
        // 如果是空的，还遍历个鬼
        if (isEmpty())
            return;
        for (int i = front + 1; i <= rear; i++) {
            System.out.println(arr[i]);
        }
    }
}
