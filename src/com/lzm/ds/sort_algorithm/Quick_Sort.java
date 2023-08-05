package com.lzm.ds.sort_algorithm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author lzm
 * @Date 2023/7/21 14:30
 */
public class Quick_Sort {
    public static void main(String[] args) {
        // int[] arr = {1,2,3,1,2};
        int[] arr = new int[8000_0000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 800000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        String start = simpleDateFormat.format(date1);
        System.out.println(start);

        new Quick_Sort().quickSort(arr,0,arr.length - 1);

        Date date2 = new Date();
        String finish = simpleDateFormat.format(date2);
        System.out.println(finish);
        // System.out.println( Arrays.toString(arr));
    }

    /**
     * 快速排序的思路：取中间的数据作为pivot,遍历找pivot左边>=pivot、找pivot右边<=pivot的数
     * 
     * 左右索引先找，找到，判断索引是否一样，然后交换，交换后看是否和pivot相同，最后看left和right的关系
     * 为什么交换之后，需要判断左索引和右索引是否等于pivot？
     * 防止这种特殊情况————pivot的左右两边可能有一个或多个与pivot相同值
     * 例如：2 3 2 1 0（2为pivot）
     * 排序之后：0 1 2 2 3（left索引为第一个2，right索引为第二个2，这样就陷入了死循环）
     */
    private void quickSort(int[] arr, int left, int right){
        int l = left;
        int r = right;
        int temp;
        int pivot = arr[(left + right) / 2];

        while(l < r){
            // 在pivot的左边寻找比pivot大于等于的数
            while (arr[l] < pivot){
                l++;
            }

            // 在pivot的右边寻找比pivot小于等于的数
            while (arr[r] > pivot){
                r--;
            }

            // 找到之后，左右索引相等就代表，就代表已经排序完成（pivot左边都是比pivot小的数，pivot右边都是比pivot大的数）
            if (l == r){
                break;
            }

            // 交换数据
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 手动干预特殊情况（序列中有pivot相同的数）
            if (arr[l] == pivot){
                // 没有什么难题解决不了，终于知道为什么判断arr【l】等于pivot，然后r要--。
                // 你要知道，左索引和右索引的尽头都是pivot，所以得另外一个索引操作。
                r--;
            }
            if (arr[r] == pivot){
               l++;
            }
        }
        // 我喜欢邓
        // 来到这里，必定l == r(只有这样，才能跳出上面的循环)，为了递归，有以下的操作
        // 来到这里有可能r < l，不过问题也不大。
        l--;
        r++;

        // 左递归
        if (left < l){
            quickSort(arr,left,l);
        }

        // 右递归
        if (r < right){
            quickSort(arr,r,right);
        }
    }
}
