package com.lzm.ds.sparsearr_queue_linkedlist;

/**
 * @Author lzm
 * @Date 2023/6/18 15:22
 */

/**
 * 设编号为1,2,...n的n个人围成坐成一圈，约定编号为k（1 <= k <= n）开始报数，数到m的那个人出列，他的下一位又从1开始报数，依此类推，直到所有人出列，我需要你写一个方法：产生一个出队编号的序列
 */
public class Josephu {
    int sum;
    Girl first = null;
    /*创建一个环形链表*/
    // 思路：当前节点的next指向新加的节点，新加的节点再指向first（注意判断first是否为空）
    public void createCircularLinkedList(int sum) {
        this.sum = sum;
        // 数据校验
        if (sum < 1) {
            System.out.println("请输入正确的数字");
            return;
        }
        // 辅助指针
        Girl curGirl = null;
        for (int i = 1; i <= sum; i++) {
            Girl girl = new Girl(i);
            if (i == 1) {
                first = girl;
                curGirl = first;
                // 自己指向自己（防止极端客服搞事:严谨！）
                girl.next = girl;
            } else {
                curGirl.next = girl;
                girl.next = first;
                curGirl = girl;
            }
        }
    }

    /*遍历环形链表*/
    //思路：while(curGirl.next != first)
    public void showGirl() {
        Girl curGirl = first;
        // 判断是不是空的环形链表
        if (curGirl == null) {
            System.out.println(curGirl);
            return;
        }
        while (curGirl.next != first) {
            System.out.println(curGirl);
            curGirl = curGirl.next;
        }
        // 来到这里是最后一个节点
        System.out.println(curGirl);
    }

    /*出圈*/
    // 根据他的要求：第k的人开始数，数到m的人就出圈
    public void leave(int startNo, int countNum){
        if (first == null || startNo < 1) {
            System.out.println("滚！");
            return;
        }

       // 必须要通过while来获得最后的最后的Girl，如果要删的恰巧是第一个，那不就尴尬了？
        Girl curGirl = first;
        Girl frontGirl = first;
        while(frontGirl.next != first) {
            frontGirl = frontGirl.next;
        }
        // 总共要出列几次
        for (int i = 0; i < this.sum - 1; i++) {
            // 具体出列操作
            for (int j = 0; j < countNum - 1; j++) {
                curGirl = curGirl.next;
                frontGirl = frontGirl.next;
            }
            System.out.println(curGirl);
            curGirl = curGirl.next;
            frontGirl.next = curGirl;

        }
        // 来到这里是最后一个Girl
        System.out.println(curGirl.next);
    }
}


class Girl {
    int no;
    Girl next;

    public Girl(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "no = "+ no;
    }
}
