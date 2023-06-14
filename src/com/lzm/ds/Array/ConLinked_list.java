package com.lzm.ds.Array;

public class ConLinked_list {
    // 实现单向链表的 增 删 改 查

    // 计数器，链表共有几个节点
    private int sum;

    // 头节点
    Node head;
    // 尾节点
    Node rear;

    // 增加一个节点(考虑序号排序)
    public void addNode(Node new_node) {
        if (sum == 0) {
            this.head = new_node;
            this.rear = new_node;
            sum++;
            return;
        }

        // 要是序号直接比头节点小-->与头节点交换
        if (new_node.compareTo(head) < 0) {
            // 保存旧的头指针
            Node old = head;

            head = new_node;
            new_node.next = old;
            sum++; return;
        }

        // 循环遍历，直到compareTo的方法返回值是负数
        Node old = head; // 这里的小细节
        Node cur = head.next;
        for(int i = 0; i < sum - 1; i++) {
            if (new_node.compareTo(cur) < 0) {
                old.next = new_node;
                new_node.next = cur;
                sum++;
                return;
            }
            old = cur;
            cur = cur.next;
        }

        // 到了这里，必定只能是在最后面添加元素
        rear.next = new_node;
        rear = new_node;
        sum++;

    }


    // 删除一个节点(注意头节点的处理)
    public void delNode(Object value) {
        if (sum == 0) {
            System.out.println("没有元素，不能删除");
        }
        // 当前节点
        Node curNode = head;
        // 单独考虑要删的节点是不是头节点
        if (curNode.value == value) {
            // 把头节点的next设为头节点
            this.head = curNode.next;
            // 再把头节点的指针域置为0（交给垃圾回收器处理）
            curNode.next = null;
            sum--;
            return;
        }

        // 程序能来到这里，说明头节点一定不是要删的元素
        // 把curNode修改一下.
        curNode = curNode.next;

        // 当前节点的上一个节点
        Node lastNode = head;
        // 要删的节点不是头节点-->让上一个节点的next指向要删的next。
        for (int i = 0; i < sum - 1; i++) {
                if (curNode.value.equals(value)) {
                    lastNode.next = curNode.next;
                    System.out.println("删除成功");
                    sum--;
                    return;
                }
            lastNode = curNode;
            curNode = curNode.next;
        }
    }

    // 修改一个节点
    public void updateNode(Object value) {

    }

    // 查节点（全部）
    public void showNodes(){
        if (sum == 0) {
            System.out.println("没有元素可查");
            return;
        }
        Node curNode = head;
        for (int i = 0; i < sum; i++) {
            System.out.println(curNode);
            curNode = curNode.next;
        }
    }
}


class Node implements Comparable<Node>{
    // 序号
    int no;
    // 数值域
    Object value;
    // 指针域
    Node next;

    public Node(int no, Object value) {
        this.no = no;
        this.value = value;
    }

    @Override
    public String toString() {
        return "no = "+ no +", value = " + value;
    }

    // 必须重写equal方法
    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }
        return ((Node)obj).value == this.value;
    }

    @Override
    public int compareTo(Node o) {
        return this.no - o.no;
    }
}
