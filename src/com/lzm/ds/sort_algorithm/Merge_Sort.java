package com.lzm.ds.sort_algorithm;

import java.util.Arrays;

/**
 * @Author lzm
 * @Date 2023/7/27 11:14
 */
public class Merge_Sort { // 先分（divide）、再治（conquer）
    public static void main(String[] args) {
        int[] arr = new int[50];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }

        int[] assist = new int[arr.length];
        new Merge_Sort().mergeSort(0,arr.length - 1,arr,assist);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 分，对半的分（分治思想中的分），分完之后，就对序列去排序
     */
    private void mergeSort(int left, int right, int[] arr, int[] assist){
        if (left < right){
            int mid = (left + right) / 2;
            // 向左分（向左递归）
            mergeSort(left,mid,arr,assist);
            // 向右分（向右递归）
            mergeSort(mid + 1,right,arr,assist);

            // 分完之后，治。
            merge(left,right,mid,arr,assist);
        }
    }

    /**
     * 该方法的作用：分好之后(分)，对序列进行排序（治）。
     * 实则就是合并两个有序的序列（双指针），注意:有序的才能去合并！
     * @param left 序列第一个数
     * @param right 序列最后一个数
     * @param mid 序列的中间数
     * @param arr 需要排序的数组
     * @param assist 辅助数组（减少在原数组上动来动去，减少时间复杂度）
     */
    private void merge(int left, int right, int mid, int[] arr, int[] assist){
        int l = left; // 左序列的第一个
        int r = mid + 1; // 右序列的第一个
        int temp = 0; // 辅助数组的索引

        // 如何把两个有序序列拼成一个有序序列？
        while (l <= mid && r <= right){
            /*能用if-else语句写的，都能用三目运算符来elegantly去解决*/
            assist[temp++] = (arr[l] <= arr[r]) ? arr[l++] : arr[r++];
        }

        // 再把剩余的元素放进去temp
        while (l <= mid){
            assist[temp++] = arr[l++];
        }
        while (r <= right){
            assist[temp++] = arr[r++];
        }

        // 把辅助数组里面的数据放进原数组
        temp = 0;
        // int tempLeft = left;
        while (left <= right){
            arr[left++] = assist[temp++];
        }
    }
}
