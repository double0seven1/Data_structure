package com.lzm.ds.recursion;

/**
 * @Author lzm
 * @Date 2023/7/7 22:22
 */

/**
 * 八皇后问题呀：在一个八行八列的棋盘上，放8个皇后，每个皇后不能同一行、不同一列、不同一个
 * 斜线上。需求，给我可以合理放在棋盘上的方法。
 * 吴枫老师教我说一句很重要的话：将代码实践化。什么叫做代码实践化呀，这样构建底层的模型：一维数组，是一维数组的下标代表行数，数组的值就
 * 代表列数.
 */
public class Queen8 {
    // 每行每列最多能放多少
    int max = 8;
    // 定义一个8 * 8的棋盘，因为每一行都有皇后，所以可以简化代码，只用一维数组来替代
    int[] arr = new int[max];
    // 计数器
    int count = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.queenPut(0);
        System.out.println(queen8.count);
    }

    /**
     * 这个方法是干嘛的？ —————判断该皇后放的位置是否合理
     * 。
     * 合理的条件是什么？ 将该皇后与前面的皇后进行比较，比较的条件：两个皇后不在同一列 || 不在对角线（用行减行，列减列来判断）
     * @param n 该棋盘的第几行（第几个皇后）
     * @return 如果合理：返回true，不合理：返回false
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }


    /**
     * 从第一个格子开始，让queen走遍8个棋子
     * @param n 要放的第n个皇后
     */
    private void queenPut(int n){
        // 如果可以正确地放第八个queen的时候，再进到新的queenPut，就结束该栈帧
        // 递归结束条件：
        if (n == max) {
            count++;
            for (int item : arr) {
                System.out.print(item + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < 8; i++) {
            // 每一行有8个位置，让这八个位置都走一遍
            arr[n] = i;
            // 因为前面可能已经放了queen了，我需要判断这个点位是否合理
            if (judge(n)) {
                // 如果在某一行放哪里都不行，就结束该方法
                queenPut(n + 1);
            }
            // 来到这里代表放在这一列不合适，需要换一下了（for循环自动向右走）
        }
    }
}

