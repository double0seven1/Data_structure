package com.lzm.ds.sparsearr_queue_linkedlist;

/**
 * @Author lzm
 * @Date 2023/6/18 15:22
 */

/**
 * 设编号为1,2,...n的n个人围成坐成一圈，约定编号为k（1 <= k <= n）开始报数，数到m的那个人出列，他的下一位又从1开始报数，报到m结束，依此类推，直到所有人出列，我需要你写一个方法：产生一个出队编号的序列
 */
public class Josephu {
    int sum;
    Girl firstGirl = null;

    public static void main(String[] args) {
        Josephu josephu =  new Josephu();
        josephu.createCircularLinkedList(10);
        josephu.leave(2,3);
    }

    /*创建一个环形链表*/
    // 思路：当前节点的next指向新加的节点，新加的节点再指向firstGirl（注意判断firstGirl是否为空）
    public void createCircularLinkedList(int sum) {
        // 数据校验(防止传入的数据不合理，导致系统崩溃)
        if (sum < 1) {
            System.out.println("请输入正确的数字");
            return;
        }
        this.sum = sum;

        // 辅助指针,指向当前的最后元素
        Girl curGirl = new Girl(1);
        curGirl.next = curGirl;
        firstGirl = curGirl;
        Girl newGirl;
        for (int i = 2; i <= sum; i++) {
            newGirl = new Girl(i);
            curGirl.next = newGirl;
            newGirl.next = firstGirl;
            curGirl = newGirl;
        }
    }

    /*遍历环形链表*/
    //思路：while(curGirl.next != firstGirl)
    public void showGirl() {
        Girl curGirl = firstGirl;
        // 判断是不是空的环形链表
        if (curGirl == null) {
            System.out.println(curGirl);
            return;
        }
        while (curGirl.next != firstGirl) {
            System.out.println(curGirl);
            curGirl = curGirl.next;
        }
        // 来到这里是最后一个节点
        System.out.println(curGirl);
    }

    /*出圈*/
    // 根据他的要求：第k的人开始数，数到m的人就出圈
    public void leave(int startNo, int countNum){
        // 编程习惯，先去思考一些极端的情况
        if (firstGirl == null || startNo < 1 || countNum < 1 || startNo > sum) {
            System.out.println("滚！");
            return;
        }

        // 链表如果涉及到随机增减的话，就得弄快慢指针。
        Girl departingGirl = firstGirl;
        Girl frontGirl = firstGirl;
        for(int i = 0; i < startNo - 1; i++){
            departingGirl = departingGirl.next;
            if(i > 0){
                frontGirl = frontGirl.next;
            }
        }


        // 总共要出列几次
        for (int i = 0; i < this.sum - 1; i++) {
            // 具体出列操作
            for (int j = 0; j < countNum; j++) {
                departingGirl = departingGirl.next;
                frontGirl = frontGirl.next;
            }
            System.out.println(departingGirl);

            departingGirl = departingGirl.next;
            frontGirl.next = departingGirl;

        }
        // 来到这里是最后一个Girl
        System.out.println(departingGirl);
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
