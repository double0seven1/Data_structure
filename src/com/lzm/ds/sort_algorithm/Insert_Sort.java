package com.lzm.ds.sort_algorithm;

import java.util.Arrays;

/**
 * @Author lzm
 * @Date 2023/7/17 22:42
 */
public class Insert_Sort {
    public static void main(String[] args) {
        int[] arr = {151,300,21,5};
        Insert_Sort sort = new Insert_Sort();
        sort.insert_Sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序思路（发牌时的捉牌）：把n个待排序的元素看成两张表，一张是有序的，一张是无序的。
     * 先把第一个元素放在有序表上，然后无序表中有n-1个元素对吧。假如是降序，就跟有序表中的第一个元素作比较，然后根据比较的结果，选择放前还是放后。
     *插入排序的核心优势是什么？
     *先找到合适的位置再放数据，而不是边比对边交换（冒泡模式）
     */
    private void insert_Sort(int[] arr){
        // 这个是临时变量，用来存放待比较的值
        int temp;
        // 这个索引，用来存放代比较值的前一个数的索引
        int temp_PreIndex;

        for (int i = 1; i < arr.length; i++) {
            temp_PreIndex = i - 1;
            temp = arr[i];
            // 因为我要实现升序，所以我要把待比较数往前面送。
            // 把待比较的数与有序列表的数一一比对。
            while (temp_PreIndex >= 0 && temp < arr[temp_PreIndex]) {
                arr[temp_PreIndex + 1] = arr[temp_PreIndex];
                temp_PreIndex--;
            }
            arr[++temp_PreIndex] = temp;
        }
    }
}