package com.lzm.ds.sort_algorithm;

/**
 * @Author lzm
 * @Date 2023/7/11 14:23
 */
public class Bubble_Sort {
    // 有这样的一个需求：给你一个数组，请将里面的内容从大到小排序
    int time;

    public static void main(String[] args) {
        int[] arr = {8,9,7,6,5,4,3,2,1};
        Bubble_Sort bubbleSort = new Bubble_Sort();
        bubbleSort.realizeBubble(arr);
        for (int item:arr) {
            System.out.print(item + " ");
        }
        System.out.println();
        System.out.println(bubbleSort.time);
    }

    // 实现冒泡排序的方法(从大到小来排序)
    /**
     * 思路；将每个数与每一个数进行比较（两层for循环），比较的过程中，如果当前的数比要比的数要小
     * 就将两个数交换位置。
     * @param arr 要进行排序的数组
     */
    private void realizeBubble(int[] arr) {
        int temp;
        // 算法优化：如果一次都没有交换的话，直接结束冒泡排序算法
        int count;
        for (int i = 0; i < arr.length - 1; i++) {
            count = 0;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]){
                    count++;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (count == 0) {
                break;
            }
            this.time++;
        }
    }
}


