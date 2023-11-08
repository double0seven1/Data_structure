package com.lzm.ds.bst;

import com.sun.istack.internal.NotNull;

/**
 * 这里是关于二叉排序树(BST)的世界。
 *
 * @Author lzm
 * @Date 2023/9/6 10:56
 */
public class bstDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BSTree bsTree = new BSTree();
        for (int i: arr) {
            bsTree.addNode(new Node(i));
        }

        bsTree.delNode(new Node(7));
        bsTree.delNode(new Node(3));
        bsTree.delNode(new Node(10));
        bsTree.delNode(new Node(12));
        bsTree.delNode(new Node(5));
        bsTree.delNode(new Node(1));
        bsTree.delNode(new Node(9));
        bsTree.delNode(new Node(2));

        bsTree.infixTraverse();
        // System.out.println(bsTree.searchNode(new Node(1)).right);
        // System.out.println(bsTree.searchNode(new Node(1)).left);

        // 死循环程序
        // Node node = new Node(10);
        // node.right = node;
        // while (node.right != null){
        //     System.out.println(node);
        // }
    }
}
class BSTree{
    static Node root;

    // 加元素
    public void addNode(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.addNode(node);
        }

    }

    // 遍历元素
    public void infixTraverse() {
        if (root == null) {
            System.out.println("该数为空，不可遍历");
        } else {
            root.infixTraverse();
        }

    }
    // 查找目标节点
    public Node searchNode(Node node){
        if (root == null){
            return null;
        }
        return root.seachNode(node);
    }

    // 查找目标节点的父节点
    public Node findParent(Node node){
        if (node == null || root == null){
            return null;
        }
        return root.findParent(node);
    }

    // 删除元素
    public void delNode(Node node){
        // 这一步非常重要，拿到本尊，如果没有这一步，直接拿参数node比较的话，就会出现此节点非彼节点的情况。
        Node target = searchNode(node);
        if (root == null || target == null){
            return;
        }

        if (root.equals(target)){
            // 根节点左右都不为null
            if (root.left != null && root.right != null){
                root.right.addNode(root.left);
                root = root.right;
            }
            // 根节点左指针域为null
            if (root.left == null && root.right != null){
                root = root.right;
            }
            // 根节点右指针域位null
            if (root.left != null && root.right == null){
                root = root.left;
            }
            // 根节点左右指针域都为null
            if (root.left == null && root.right == null){
                root = null;
            }
        } else {
            // 先拿到父节点
            Node parent = this.findParent(target);

            // 怎么知道这个节点相对于父节点是左还是右呢？
            // 通过parent的左右两边对比咯~（拿到父节点真的爽）
            // 叶子节点
            if (target.left == null && target.right == null) {
                if (parent.left != null && parent.left.equals(target)){
                    parent.left = null;
                } else {
                    parent.right = null;
                }

                // 有两个分支的节点
            } else if (target.left != null && target.right != null) {
                if (parent.left != null && parent.left.equals(target)){
                    parent.left = target.right;
                } else {
                    parent.right = target.right;
                }
                target.right.addNode(target.left);

            } else {
                // 有一个分支的节点
                // 等号比较的是地址！我要比较的是里面的数值！(终于找到bug)
                if (parent.left != null && parent.left.equals(target)){
                    parent.left = (target.left == null) ? target.right : target.left;
                } else {
                    parent.right = (target.left == null) ? target.right : target.left;
                }
            }
        }
    }


}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Node)){
            return false;
        }
        return this.value == ((Node) obj).value;
    }

    public void addNode(@NotNull Node node) {
        if (node == null || this.equals(node)) { // 先考虑特殊情况
            return;
        }

        if (node.value < this.value) { // 新加的节点比当前节点小
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.addNode(node);
            }
        } else { // 新加的节点比当前节点大
            if (this.right == null) {
                    this.right = node;
            } else {
                this.right.addNode(node);
            }
        }
    }

    public void infixTraverse() {
        if (this.left != null) {
            this.left.infixTraverse();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixTraverse();
        }
    }


    // 删除某个节点,先左递归，找到的话就结束，然后右递归找，找到的话也结束
    // 上面的思路去去去去，如果左根右去找的话，那时间复杂度也太大了！
    public void deleteNode(@NotNull Node node){

    }

    // 高招寻找父节点,找到返回父节点；找不到，返回null
    public Node findParent(Node node){
        if ((this.left != null && this.left.value == node.value)
                || (this.right != null && this.right.value == node.value) ){
            return this;
        } else {
            if (this.value < node.value && this.right != null){
                return this.right.findParent(node);
            } else if (this.value > node.value && this.left != null) {
                return this.left.findParent(node);
            }
        }
        return null;
    }

    // 寻找那个节点，折半查找
    public Node seachNode(Node node){
        if (this.value == node.value){
            return this;
        } else if (this.left != null && this.value > node.value) {
            return this.left.seachNode(node);
        } else if (this.right != null && this.value < node.value ) {
            return this.right.seachNode(node);
        }
        return null;
    }
}
