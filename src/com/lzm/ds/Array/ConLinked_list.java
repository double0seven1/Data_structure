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

            new_node.next = head;
            head = new_node;

            sum++; return;
        }

        // 循环遍历，直到compareTo的方法返回值是负数
        Node old = head;
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

    // 查找倒数第n个结点的数值->来两个辅助指针搞定（快慢指针）
    public Node ReciprocalNode(int i) { // reciprocal:相反的
        //思路：让快指针先走，等到计数器为2的时候，再启动慢指针
        Node quick = head;
        Node slow = head;

        int count = 0;
        while (quick.next != null) {
            if(count >= 2) {
                slow = slow.next;
            }
            quick = quick.next;
            count++;
        }
        return slow.next;
    }




    // 普通版反转单链表->头插法（每遍历一个节点，就放在前面）
    public void reverseLinkedList() {
        //如果sum = 1或等于0，无需反转

        // 创建一个首首节点来辅助，每遍历一个节点，就放在首首节点的后面
        Node header = new Node();
        header.next = head;

        Node curNode = head.next;
        Node frontNode = head;//惊天大bug，frontNode的位置不会变！

        while(curNode.next != null) {
            // 把当前节点的next节点存一下
            Node curNode_next = curNode.next;
            // 这里解决了一个小bug，要保存curNode的next结点！
            curNode.next = header.next;
            header.next = curNode;
            // 调整
            frontNode.next = curNode_next;
            curNode = curNode_next;
        }
        // 来到这里是最后的一个节点
        curNode.next = header.next;
        header.next = curNode;
        frontNode.next = null;

        // 把首首节点去掉，把首节点改一下
        head = header.next;
    }


    /**
     * 递归搞定腾讯单链表逆序打印题
     * 什么是递归？
     * 1.不断的进去，然后从里面出来。（盗梦空间、进入小穴）
     * 2.必须要有结束的条件 (盗梦一定要出来)
     */
    public void recurList(Node node) {
        if (node.next != null) {
            recurList(node.next);
        }
        System.out.println(node);
    }
}


class Node implements Comparable<Node>{
    // 序号
    int no;
    // 数值域
    Object value;
    // 指针域
    Node next;

    public Node() {
    }

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
