package com.lzm.ds.sparsearr_queue_linkedlist;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SparseArray {
    public static void main(String[] args) {

        /* 普通二维数组-->稀疏数组-->转去磁盘:两个for循环遍历二维数组，共有几个有效值,sum
        根据sum，稀疏数组的行：sum + 1,列: 3（row，col,value）
        稀疏数组-->普通二维数组:通过稀疏数组的第一行的数据，复原原来的表出来
        原来的值该怎么赋回去呢？
        遍历每一行，去赋值 */
        // int arr[][] = new int[6][7];
        // arr[0][3] = 22; arr[0][6] = 15; arr[1][1] = 11; arr[1][5] = 17;
        // arr[2][3] = -6; arr[3][5] = 39; arr[4][0] = 91; arr[5][2] = 28;

        // SparseArray.ArrChangeToSparseArr(arr);

        // SparseArray.SparseArrChangeToArr();

        // 怎么通过普通for循环来遍历二维数组？
        // int arr[][] = new int[3][3];
        // for (int i = 0; i < arr.length; i++) {
        //     for (int j = 0; j < arr[i].length; j++) {
        //
        //     }
        // }
    }

    public static void ArrChangeToSparseArr(int arr[][]) {
        //  求出有多少的有效数值:
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    sum += 1;
                }
            }
        }
        int[][] sparseArray = new int[sum + 1][3];
        // 原始数组总共有几列
        sparseArray[0][0] = arr.length;
        // 原始数组总共有几行
        sparseArray[0][1] = arr[0].length;
        // 原始数组总共有几个元素
        sparseArray[0][2] = sum;

        // 其它行的数据
        int count = 1; // 计数器
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                  sparseArray[count][0] = i;
                  sparseArray[count][1] = j;
                  sparseArray[count][2] = arr[i][j];
                  count++;
                }
            }
        }

        // 增强for循环遍历数组
        for (int[] sparseArr :sparseArray) {
            for (int data:sparseArr) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        // 把这个数组转去磁盘中
        // 遍历数组，取出每一个元素，转成String类型，然后调用String类中的getBytes方法变成Byte数组，恰巧write方法就是以Byte数组作为参数
        try(FileOutputStream fos = new FileOutputStream("out/production/Data_structure/SparseArray")) {
            for (int[] Sparsearr :sparseArray) {
                for (int data:Sparsearr) {
                    fos.write(String.valueOf(data).getBytes());
                    // 取回来的时候打标计
                    fos.write("\t".getBytes());
                }
                fos.write("\n".getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void SparseArrChangeToArr() {
        // 先通过文件把稀疏数组给还原回来
        // 1.先把文件中数组的内容先放到StringBuilder中
        // 2.通过"\n"获得共有几行
        // 3.再通过"\t"获得共有几列
        try(FileInputStream fis = new FileInputStream("out/production/Data_structure/SparseArray")) {
            StringBuilder sb = new StringBuilder();

            int count = 0;
            while ((count = fis.read()) != -1){
                sb.append((char)count);
            }

            String[] Lines = sb.toString().split("\n");
            // ros:行数
            int rows = Lines.length;

            int[][] restoredArr = new int[rows][];

            // 解析每行的元素
            for (int i = 0; i < rows; i++) {
                // element：每一行的元素
                String[] elements = Lines[i].split("\t");
                // cols：列数
                int cols = elements.length;
                int[] rowArray = new int[cols];
                for (int j = 0; j < cols; j++) {
                    rowArray[j] = Integer.parseInt(elements[j]);
                }
            // restoredArr数组的每行指向一个一维数组
                restoredArr[i] = rowArray;
            }
            // 通过稀疏数组变回普通二维数组
            int[][] arr = new int[restoredArr[0][0]][restoredArr[0][1]];

            // 给有效数值赋上值
            for (int i = 1; i < restoredArr.length; i++) {
                // 行，列，值
                arr[restoredArr[i][0]][restoredArr[i][1]] = restoredArr[i][2];
            }

            for (int[] commonarr :arr) {
                for (int item: commonarr) {
                    System.out.printf("%d\t",item);
                }
                System.out.println();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
