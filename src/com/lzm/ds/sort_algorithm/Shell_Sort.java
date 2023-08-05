package com.lzm.ds.sort_algorithm;

import java.util.Arrays;

/**
 * @Author lzm
 * @Date 2023/7/19 20:53
 */
public class Shell_Sort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        new Shell_Sort().shell_Sort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 希尔排序1的思路:先根据步长分组，每组里面的数据再排序（冒泡的影子）
     */
    private void shell_Sort1(int[] arr){
        int temp;
        // 这一个for循环作用:获取步长(分组)
        for(int gap = arr.length / 2; gap > 0; gap /= 2){
            // 遍历列表上数据
            for (int i = gap; i < arr.length; i++) {
                // 拿当前这个数与同组的全部数据进行比较。
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    // 交换式希尔排序
    private void shell_Sort2(int[] arr){
        int temp;
        int j;
        // 获取步长（分组）
        for (int gap = arr.length / 2; gap > 0; gap /= 2){

            for (int i = gap; i < arr.length; i++) {
                temp = arr[i];
                j = i;
                // 跟前一个步长的元素进行比较
                while(j - gap >= 0 && temp < arr[j - gap]){
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                // 来到这里，说明已经找到了位置了
                arr[j] = temp;
            }
        }
    }
}
