package com.lzm.ds.tree;

import java.util.Arrays;

/**
 * @Author lzm
 * @Date 2023/8/28 17:50
 * 两三天时间吧序，终于弄明白堆排的思路：
 * ①先把整体的数组搞成大堆顶的方式，只需将每个非叶子节点都弄成大顶堆(从下到上，从右到左)
 * ②把堆顶元素放去末尾.
 * ③放好之后，从堆顶开始，重新调成结构,保持大堆顶模式.
 */
public class heapSort {
    public static void main(String[] args) {
        int[] arr = {-1,3,5,-9,10,34,-34};
        new heapSort().HeapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 把当前父节点调成大堆顶的模式（升序）
     * @param arr 待排序的数组
     * @param parentNode 当前需要处理的父节点（父节点下面可能会有子节点（非叶子节点））
     * @param maxLength 可以有效处理的数据最大取值(因为堆排是把堆顶放去最后，放完之后就不要管了),一开始是数组最后的下标
     */
    public void adjustBigHeap(int[] arr,int parentNode,int maxLength){
        int temp = arr[parentNode];

        for (int i = parentNode * 2 + 1; i < maxLength;  i *= 2 + 1) {

            // 判断左右节点哪个数值大
            if (i + 1 < maxLength && arr[i] < arr[i + 1]){
                i++;
            }

            // 如果子节点比父节点大，交换
            if (arr[i] > temp){
                arr[parentNode] = arr[i];
                parentNode = i;
            } else {
                break;
            }
        }
        arr[parentNode] = temp;
    }

    public void HeapSort(int[] arr) {
        int temp;
        // log(n)
        // 先把整个数组改改造成大顶堆的模样
        // 每个小的父节点都改成大顶堆的格式
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            adjustBigHeap(arr,i,arr.length);
        }

        // n
        // 让最上面的堆顶数组的末元素放去尾，重新恢复成大顶堆模式
        for (int i = arr.length - 1; i > 0; i--) { // 放末尾、倒数第二个、倒数...
            // 堆顶放去末尾
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // 调整数组
            adjustBigHeap(arr,0,i);
        }
    }
}
