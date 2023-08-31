package com.lzm.ds.sequentialStorage;

/**
 * @Author lzm
 * @Date 2023/8/23 21:57
 * 需求:给你一个数组，模仿二叉树的形式对该数组进行前序遍历
 * 搞这出有什么用？
 * 为未来的堆排序打基础
 */
public class ArrTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        new ArrBinaryTree(arr).preSort();
    }
}

// 数组二叉树
class ArrBinaryTree{
    int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 函数重载
    public void preSort(){
        this.preSort(0);
    }

    // 前序遍历该数组（根左右）
    public void preSort(int index) {
        // 根
        System.out.print(arr[index] + "  ");

        // 左
        if (index * 2 + 1 < arr.length){ // 防止数组下标越界.
            preSort(index * 2 + 1);
        }

        // 右
        if (index * 2 + 2 < arr.length){
            preSort(index * 2 + 2);
        }
    }
}
