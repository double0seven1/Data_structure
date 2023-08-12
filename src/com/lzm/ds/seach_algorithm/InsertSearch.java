package com.lzm.ds.seach_algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lzm
 * @Date 2023/8/10 22:04
 */
public class InsertSearch {

    public static void main(String[] args) {
        int[] arr = {6,97,104,251,359,401,559};
        System.out.println(new InsertSearch().binarySearch(arr,359));
    }

    private int binarySearch(int[] arr, int value){
        int left = 0;
        int right = arr.length - 1;

        // 先做特殊的处理
        if (value < arr[0] || value > arr[right]){
            return -1;
        }

        while (left <= right){
            int midIndex = left + (right - left) * ((value - arr[left]) / (arr[right] - arr[left]));
            if (value == arr[midIndex])
                return midIndex;

            int i = (value < arr[midIndex]) ? (right = midIndex - 1) : (left = midIndex + 1);
        }
      return -1;
    }
}
