package com.lzm.ds.seach_algorithm;

import java.util.Arrays;

/**
 * @Author lzm
 * @Date 2023/8/17 22:15
 */
public class FibonacciSearch {
    int maxSize = 20;
    public static void main(String[] args) {
        // 玩一下Arrays.copyOf中的方法
        int[] arr = {2,3,4,5,6,10,20,43,43,49,50,69};
        System.out.println(new FibonacciSearch().fibonacciSearch(arr,49));
    }

    // 领导可不是学校老师，不是万能的！
    private int fibonacciSearch(int[] arr,int value){
        int[] fib = this.fib();
        // 定义第几个斐波那契数，斐波那契数也是待排序的长度
        int itemOfFib = 0;

        int mid;
        int left = 0;
        int right = arr.length - 1;

        // 为序列寻找到合适的斐波那契数
        while(fib[itemOfFib] - 1 < arr.length){
            itemOfFib++;
        }

        // 因为有可能斐波那契数比数组的长度要大，所以，我得创建一个新的数组去keep
        int[] newArr = Arrays.copyOf(arr,fib[itemOfFib] - 1);
        // 更新数组最后面的值
        for (int i = right + 1; i < newArr.length; i++) {
            newArr[i] = arr[right];
        }


        while (left <= right){
            // 为什么这里mid不用加回1？左边的长度 + mid（1）+ 右边的长度 = fib[itemOfFib] - 1，只是因为数组的下标的问题（从0开始）,所以..
            mid = left + fib[itemOfFib - 1] - 1;
            if (newArr[mid] == value)
                return mid;
            else if (newArr[mid] > value) {
                right = mid - 1;
                itemOfFib--;
            } else if(newArr[mid] < value){
                left = mid + 1;
                itemOfFib -= 2;
            }
        }
        return -1;
    }

    private  int[] fib(){
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }
}
