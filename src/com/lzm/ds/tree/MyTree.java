package com.lzm.ds.tree;

import com.sun.istack.internal.NotNull;

/**
 * @Author lzm
 * @Date 2023/8/21 23:09
 */
public class MyTree {
    public static void main(String[] args) {
        //  张三 李四 王五 赵六 孙七、周八、吴九、郑十
        Node node = new Node(7, "孙七");
        Node node1 = new Node(13, "十三姨");
        Node node2 = new Node(4, "李四");
        Node node3 = new Node(2, "二奶");
        Node node4 = new Node(3, "张三");
        Node node5 = new Node(6, "赵六");
        Node node6 = new Node(8, "周八");
        Node node7 = new Node(18, "1.8纪念节");


        node.setRight(node1);
        node1.setLeft(node6);
        node1.setRight(node7);
        node.setLeft(node2);
        node2.setRight(node5);
        node2.setLeft(node3);
        node3.setRight(node4);

        BinaryTree binaryTree = new BinaryTree(node);

        System.out.println(binaryTree.preSearch(8));
    }
}


// 节点类
class Node {
    private int id;
    private String name;

    private Node left; // 左节点
    private Node right; // 右节点

    // 构造方法
    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    // 前序遍历
    public void preSort(){
        // 根
        System.out.println(this);
        // 左
        if (this.left != null){
            left.preSort();
        }
        // 右
        if (this.right != null){
            right.preSort();
        }
    }

    // 中序遍历
    public void infixSort(){
        // 左
        if (this.left != null){
            left.infixSort();
        }
        // 根
        System.out.println(this);
        // 右
        if (this.right != null){
            right.infixSort();
        }
    }

    // 后序遍历
    public void suffixSort(){
        // 左
        if (this.left != null){
            left.suffixSort();
        }
        // 右
        if (this.right != null){
            right.suffixSort();
        }
        // 根
        System.out.println(this);
    }


    // 前序查找(太low了，如果在二叉排序树里时间复杂度可以大大减少)
    public Node preSearch(int id){
        if (this.id == id){
            return this;
        }

        Node resNode = null;
        // 左递归，如果左递归找到了，就return
        if (this.left != null){
            resNode = this.left.preSearch(id);
        }
        // 如果左递归的过程中找到了，return上去，下面的代码也不用执行了！
        if (resNode != null){
            return resNode;
        }

        // 右递归
        if (this.right != null){
            resNode = this.right.preSearch(id);
        }
        return resNode;
    }

    // 中序查找
    public Node infixSearch(int id){
        Node resNode = null;

        // 左递归，如果左递归找到了，就return
        if (this.left != null){
            resNode = this.left.infixSearch(id);
        }
        if (resNode != null){
            return resNode;
        }
        // 根
        if (this.id == id){
            return this;
        }
        // 右递归，
        if (this.right != null){
            resNode = this.right.infixSearch(id);
        }
        return resNode;
    }

    // 后序查找
    public Node suffixSearch(int id){
        Node resNode = null;
        // 左递归，如果左递归找到了，就return
        if (this.left != null){
            resNode = this.left.suffixSearch(id);
        }
        if (resNode != null){
            return resNode;
        }
        // 右递归，
        if (this.right != null){
            resNode = this.right.suffixSearch(id);
        }
        // 中
        if (this.id == id){
            return this;
        }
        return resNode;
    }


    // 简单的节点删除: 如果是叶子节点，直接删除；如果是非叶子节点，整个树枝删除.
    public boolean delNode(int id) {
        // 按照根左右来执行
        // 根
        // if (this.id == id){
        //     this.left.right = this.right;
        //     return true;
        // }

        // 左
        if (this.left != null){
            if (this.left.id == id){
                this.left = null;
                return true;
            }
            // 左递归
            if(this.left.delNode(id)){
                return true;
            }
        }

        // 右
        if (this.right != null){
            if (this.right.id == id){
                this.right = null;
                return true;
            }
            // 右递归
            if(this.right.delNode(id)){
                return true;
            }
        }
        return false;
    }
}

class BinaryTree {
    // 至关重要的头节点，通过头节点，就可以对整颗树实现CRUD
    Node headNode;

    public BinaryTree(@NotNull Node headNode) {
        this.headNode = headNode;
    }

    // 前序遍历:我只要控制住了headNode，就能控制住所有的节点
    public void preSort(){
        if (headNode == null){
            return;
        }
        headNode.preSort();
    }

    // 中序遍历
    public void infixSort(){
        if (headNode == null){
            return;
        }
        headNode.infixSort();
    }

    // 后序遍历
    public void suffixSort(){
        if (headNode == null){
            return;
        }
        headNode.suffixSort();
    }



    // 前序查找
    public Node preSearch(int id){
        if (headNode == null){
            return null;
        }
        return headNode.preSearch(id);
    }

    // 中序查找
    public Node infixSearch(int id){
        if (headNode == null){
            return null;
        }
        return headNode.infixSearch(id);
    }

    // 后序查找
    public Node suffixSearch(int id){
        if (headNode == null){
            return null;
        }
        return headNode.suffixSearch(id);
    }


    // 简单删除节点
    public boolean delNode(int id){
        if (headNode.getId() == id){
            headNode = null;
            return true;
        }
        return headNode.delNode(id);
    }
}