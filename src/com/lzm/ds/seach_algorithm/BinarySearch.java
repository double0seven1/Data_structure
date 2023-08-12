package com.lzm.ds.seach_algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lzm
 * @Date 2023/8/7 23:08
 */
public class BinarySearch {
    // 为第二个完整版二分查找提供一个容器
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,7,7,7,7};
        new BinarySearch().binarySearch1(arr,0,arr.length - 1,7);
        for (int i:list) {
            System.out.print(i + " ");
        }
    }


    private void binarySearch(int[] arr, int value){
        int left = 0;
        int right = arr.length - 1;
        int midIndex;

        List<Integer> resList = new ArrayList<>();

        while (left <= right){
            midIndex = (left + right) / 2;
            if (value == arr[midIndex])
               resList.add(midIndex);

            int i = (value < arr[midIndex]) ? (right = midIndex - 1) : (left = midIndex + 1);
        }

        if (resList.isEmpty()){
            System.out.println("该序列没有此元素");
        } else {
            System.out.print("需要寻找的元素下标分别为:");
            for (int i:resList) {
                System.out.print(i + " ");
            }
        }
    }

    /**递归法的完整二分查找*/
    private void binarySearch1(int arr[],int left, int right, int value){
        int midIndex = (left + right) / 2;
        if (left <= right){
            if (arr[midIndex] < value){
                binarySearch1(arr,midIndex + 1,right,value);
            } else if(arr[midIndex] > value){
                binarySearch1(arr,left,midIndex - 1,value);
            } else if(arr[midIndex] == value){
                list.add(midIndex);
                binarySearch1(arr,left,midIndex - 1,value);
                binarySearch1(arr,midIndex + 1,right,value);
            }
        }
    }
}
