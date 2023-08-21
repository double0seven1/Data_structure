package com.lzm.ds.sparsearr_queue_linkedlist;

/**
 * @Author lzm
 * @Date 2023/6/17 19:29
 */

/**
 * 这个类用来模拟双向链表
 * @see DoubleLinkedList
 */
public class DoubleLinkedList {
    D_Node firstGirl;
    // 加元素这个步骤完全可以进行多态，如果是直接传入数值，就从末尾加；如果是提供了索引和数值，就从头节点开始加咯。
    D_Node rear;

    /*模拟普通的从末尾节点加元素*/
    public void add(Object val1) {
        D_Node val2 = this.rear;
        D_Node val3 = new D_Node(val2,val1,null);
        this.rear = val3;
        if (firstGirl == null) {
            firstGirl = val3;
        }
        val2.down = rear;
    }
}

/*双向链表的节点类*/
class D_Node {
    D_Node up;
    Object value;
    D_Node down;

    public D_Node(D_Node up, Object value, D_Node down) {
        this.up = up;
        this.value = value;
        this.down = down;
    }
}
