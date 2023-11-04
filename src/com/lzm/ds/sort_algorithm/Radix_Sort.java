package com.lzm.ds.sort_algorithm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Author lzm
 * @Date 2023/8/5 23:30
 */
public class Radix_Sort {
    // 基数排序的思路；先设置好10个桶，根据不同数据的位，放到合适的桶里面，再按顺序把数据拿出来.

    public static void main(String[] args) {
        // int[] arr = new int[80000_000];
        // for (int i = 0; i < arr.length; i++) {
        //     arr[i] = (int) (Math.random() * 800000);
        // }
        // Date date1 = new Date();
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        // String start = simpleDateFormat.format(date1);
        // System.out.println(start);

        int[] arr = {43, 23, 1, 5, 8, 9, 341, 34};

        new Radix_Sort().radixSort(arr);

        // Date date2 = new Date();
        // String finish = simpleDateFormat.format(date2);
        // System.out.println(finish);
        System.out.println(Arrays.toString(arr));
    }

    private void radixSort(int[] arr) {
        // 来10个桶，每个桶的长度是数组的长度（防止特殊情况）
        int[][] bucket = new int[10][arr.length];
        // 再来一个桶，每个int记录每个桶里面共有几个元素
        int[] bucketElementCounts = new int[10];

        // 求出最大的那一个数
        int max = arr[0];
        for (int k : arr) {
            if (k > max) {
                max = k;
            }
        }

        // 求出最大的那个数的位数（int类型快速转成String，String有length（））
        int wei = (max + "").length();

        // 求出最小的那一个数(假如要对负数进行处理)
        // int min = arr[0];
        // for (int k:arr) {
        //     if (k < min){
        //         min = k;
        //     }
        // }

        // 最小那个数的绝对值
        // int minAbs = Math.abs(min);

        // 解决负数的问题
        // for (int k:arr) {
        //     k += minAbs;
        // }

        // 增强for循环对数组元素进行迭代时，不能用来修改数组中的元素，只能读取。普通的 for 循环可以进行读取和修改
        // for (int i = 0; i < arr.length; i++) {
        //     arr[i] += minAbs;
        // }


        // 这个for循环是用来统计循环要放几次的，如果是三位数，就只需要商到100就OK。
        int stopSign = 1;
        for (int i = 0; i < wei - 1; i++) {
            stopSign *= 10;
        }

        // 在for循环里面，还可以定义两个变量,例：
        // for(int i = 0, n = 1; i < wei; i++,n *= 10)

        // 这个for的作用：需要往桶里面放几次
        for (int i = 1; i <= stopSign; i *= 10) {

            for (int k : arr) {
                // 计算该元素的的位数，并放去合适的地方
                int digitOfElement = k / i % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = k;
                bucketElementCounts[digitOfElement]++;
            }

            // 把每个桶里面的数据放回去，该怎么操作？
            int count = 0;
            // 遍历十个桶，把每个桶里面的数据放回数组
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < bucketElementCounts[j]; k++) {
                    arr[count++] = bucket[j][k];
                }
                // 放完之后，需要把bucketElementCounts里面的记录数给置为0，为下一次做准备
                bucketElementCounts[j] = 0;
            }
        }
        // for (int k:arr) {
        //     k -= minAbs;
        // }

        // 处理负数的操作（每个数加上最小那个那个数的绝对值，最后再减回去）
        // for (int i = 0; i < arr.length; i++) {
        //     arr[i] -= minAbs;
        // }
    }
}
