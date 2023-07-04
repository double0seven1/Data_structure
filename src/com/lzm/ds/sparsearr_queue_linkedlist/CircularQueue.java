package com.lzm.ds.sparsearr_queue_linkedlist;

public class CircularQueue {
    // 环形数组的关键：留一个空间

    // 头指针，指向环形队列的第一个元素
    private int front = 0;
    // 尾指针，指向环形队列最后一个元素的后一个位置
    private int rear = 0;

    int maxSize = 5;
    private int arr[] = new int[maxSize];

    // 判断是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    // 判断是否满了
    public boolean isFull() {
        // 如果不空出一个空间（rear指向最后一个元素的后一位）,该怎么区分判断是否为空或满？
        return (rear + 1) % maxSize == front;
    }

    // 加元素
    public void add(int i) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arr[rear] = i;
        rear = (rear + 1) % maxSize;
    }

    // 出列（取元素）
    public int get() {
        if (isEmpty())
            throw new RuntimeException("队列为空！");
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 查看元素
    public void show() {
        if (isEmpty()) {
            System.out.println("没有元素");
            return;
        }
        for (int i = front; i < front + sum(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 计算共有多少个元素
    public int sum() {
        return (rear + maxSize - front) % maxSize;
    }

}



// public class CircleQueue {
//     private int front = 0;
//     private int rear = 0;
//     private int maxSize = 5;
//     private int arr[] = new int[maxSize];
//
//     // 判断是否为空
//     public boolean isEmpty() {
//         return rear == front;
//     }
//
//     // 判断是否满了
//     public boolean isFull() {
//         return (rear + 1) % maxSize == front;
//     }
//
//     // 入队列
//     public void add(int value) {
//         arr[rear] = value;
//         rear = (rear + 1) % maxSize;
//     }
//
//     // 出队列
//     public int get() {
//         int value = arr[front];
//         front = (front + 1) % maxSize;
//         return value;
//     }
//
//     // 计算队列总共有几个元素
//     public int sum(){
//         return (rear + maxSize - front) % maxSize;
//     }
//
//     // 展示队列
//     public void show() {
//         for(int i = front; i < front + sum(); i++) {
//             System.out.printf("arr[%d] = %d", i % maxSize, arr[i % maxSize]);
//         }
//     }
// }
