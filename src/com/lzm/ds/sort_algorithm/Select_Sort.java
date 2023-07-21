package com.lzm.ds.sort_algorithm;

import java.util.Arrays;

/**
 * @Author lzm
 * @Date 2023/7/15 11:02
 */
public class Select_Sort {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        Select_Sort sort = new Select_Sort();
        sort.select_sort(arr);
        // for (int item :arr) {
        //     System.out.print(item + "  ");
        // }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序的思路：循环遍历找出最小的放在最前面，然后以此类推. 我就先试着找出最小的那个元素（还不交换），然后循环遍历完之后，再交换.
     */
    private void select_sort(int[] arr){
        // 最小值
        int min_value;
        // 最小值的下标
        int min_index;
        // 临时变量
        int temp;
        // n个数的序列， 只要排好了n - 1个数，剩下最后一个就自动排好了。
        for (int i = 0; i < arr.length - 1; i++) {
            // 默认未排序序列的第一个为min
            min_index = i;
            min_value = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                // 我就先试着找出最小的那个元素（还不交换）
                if (min_value > arr[j]){
                    min_value = arr[j];
                    min_index = j;
                }
            }
            // 循环一遍之后，就找到了最小的那个元素，然后把它放在第一个位置
            temp = arr[i];
            arr[i] = min_value;
            arr[min_index] = temp;
        }
    }
}
