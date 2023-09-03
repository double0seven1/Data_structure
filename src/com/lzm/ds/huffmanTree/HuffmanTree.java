package com.lzm.ds.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author lzm
 * @Date 2023/9/1 5:45
 * 给你一个数组{13，7，8，3，29，6}，怎么把这些数据封装成节点并构建成赫夫曼树？
 * 什么是赫夫曼树：树的带权路径最小
 * 什么是树的带权路径：每个节点的带权路径之和
 * 带权路径之和：从root出发的路径 * 节点值的大小
 *
 * 思路：①对这个数组进行排序（从小到大）
 * ②取出数组前两个最小的值，作为左右节点，父节点则是他俩的和（构建二叉树）
 * ③把左右节点从数组里面删掉，把父节点加进去
 * ④循环上面的步骤，直到数组里面只有一个元素
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6};
        infixSort(createHuffmanTree(arr));
    }

    public static Node createHuffmanTree(int[] arr){
        // 把arr放进List中
        ArrayList<Node> list = new ArrayList<>();

        // 封装每个Node节点进List
        for (int i:arr) {
            list.add(new Node(i));
        }

        while (list.size() > 1){
            // Collections里面有很多的方法都是用static修饰的！
            // 将list元素进行排序
            Collections.sort(list);

            // 排序完之后，将前两个拿出来
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);

            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 先删再加和先加再删有什么区别？ ——没区别
            list.add(parent);
            list.remove(leftNode);
            list.remove(rightNode);

        }
        return list.get(0);
    }

    public static void infixSort(Node root){
        if (root != null){
            root.infixSort();
        } else {
            System.out.println("该节点为空");
        }
    }
}

class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    public void infixSort(){
        if (this.left != null){
            this.left.infixSort();
        }

        System.out.print(this + "\t");

        if (this.right != null){
            this.right.infixSort();
        }
    }
}
