package com.lzm.ds.stack;

/**
 * @Author lzm
 * @Date 2023/6/18 20:29
 */
/**
 * 利用数组模拟栈
 *思路：栈帧先指向数组底部；压栈时，从数组底部开始加，每加一个元素，栈帧就往上移动；  删除元素的话，从栈帧指向的位置开始删.(栈帧：栈顶指针)
 */
public class Stack {
    int maxSize;
    // 栈顶指针
    int topPointer;
    Object[] arr;
    public Stack(int maxSize) {
        this.maxSize = maxSize;
        topPointer = maxSize;
        arr = new Object[maxSize];
    }



    // 压栈（减一后，添加元素）
    public void push(Object value) {
        if (--topPointer == -1) {
            System.out.println("栈已满");
            topPointer++;
            return;
        }
        arr[topPointer] = value;
    }

    // 遍历:从栈顶开始遍历
    public void showStack() {
        for (int i = topPointer; i < maxSize; i++) {
            System.out.println(arr[i]);
        }
    }

    // 弹栈（先弹出当前元素，再加一）模仿iterator中的next方法
    public Object pop() {
        //判断是不是空栈
        if (topPointer == maxSize)
            throw new RuntimeException("这个栈为空的！");
        return arr[topPointer++];

    }

    // 偷窥一下栈顶的栈帧
    public Object peek(){
        return arr[topPointer];
    }
}

