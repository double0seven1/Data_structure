package com.lzm.ds.recursion;

/**
 * @Author lzm
 * @Date 2023/7/6 15:11
 */
public class Maze {
    public static void main(String[] args) {
        // 定义一个8行7列的迷宫
        int[][] maze = new int[8][7];
        // 把上下边界设成1
        for (int i = 0; i < 7; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        // 把左右边界设成1
        for (int i = 0; i < 8; i++) {
            maze[i][0] = 1;
            maze[i][6] = 1;
        }
        // 设置挡板（墙壁）
        maze[0][1] = 1;
        maze[0][2] = 1;
        maze[0][3] = 1;
        maze[0][4] = 1;
        maze[0][5] = 1;

        maze[1][4] = 1;
        maze[1][5] = 1;
        maze[1][6] = 1;

        maze[2][1] = 1;
        maze[2][2] = 1;
        maze[2][4] = 1;
        maze[2][5] = 1;

        maze[3][1] = 1;
        maze[3][2] = 1;
        maze[3][4] = 1;
        maze[3][5] = 1;

        maze[4][1] = 1;

        maze[5][1] = 1;
        maze[5][3] = 1;
        maze[5][4] = 1;
        maze[5][5] = 1;

        maze[6][1] = 1;

        maze[7][1] = 1;
        maze[7][2] = 1;
        maze[7][3] = 1;
        maze[7][4] = 1;
        maze[7][5] = 1;
        // 设置终点坐标d
        // maze[6][5] = 5;


        // 调用方法
        teacher_SeekWay(maze,1,1);

        // 遍历数组，看看走的痕迹
        for (int[] rope : maze) {
            for (int item : rope) {
                System.out.print(item + "  ");
            }
            System.out.println();
        }
    }

    /**
     * 我的迷宫里找路的方法：运用递归，把整个走的过程拆分成一个一个小的步骤
     * @param maze 需要找的迷宫
     * @param row 从哪里开始 x坐标
     * @param col 从哪里开始 y坐标
     * @return 判断此路是否可行？
     * 默认的终点坐标：6,5
     * 找路策略：下、右、上、左
     * 思路：先判断向下走，如果数值是0，递归进入,进入之后，把该位置赋值：2，如果是1（墙壁），break出来，结束函数。如果
     * 是到终点了，return直接结束该栈帧;然后再判断其他的右.
     */
    private static void seekWay(int[][] maze,int row,int col) {
        while (true) {
            // 来个索引，减少入栈的次数
            int index = diffWay(maze,row,col);
            // 来到新的栈帧，就赋值
            maze[row][col] = 2;

            // 向下走,如果为空，继续进入
            if (index== 2){
                return;
            } else if (index == 0) {
                seekWay(maze,row - 1,col);
                // 如果是墙壁，跳出来
            } else if (index == 1) {
                break;
                // 如果是终点，结束栈帧
            } else if (index == 5) {
                maze[row - 1][col] = 2;
                return;
            }

            // 向右走，这个index必须重新赋值！因为可能回溯前，就已经被修改了值（走过的路）
            if ((index = diffWay(maze,row,col)) == 2){
                return;
            } else if (index == 0) {
                seekWay(maze,row,col + 1);
                // 如果是墙壁，跳出来
            } else if (index == 1) {
                break;
                // 如果是终点，结束栈帧
            } else if (index == 5) {
                maze[row][col + 1] = 2;
                return;
            }

            // 向上走
            if ((index = diffWay(maze,row,col)) == 2){
                return;
            } else if (index == 0) {
                seekWay(maze,row + 1,col);
                // 如果是墙壁，跳出来
            } else if (index == 1) {
                break;
                // 如果是终点，结束栈帧
            } else if (index == 5) {
                maze[row + 1][col] = 2;
                return;
            }

            // 向左走
            if ((index = diffWay(maze,row,col)) == 2){
                return;
            } else if (index == 0) {
                seekWay(maze,row,col - 1);
                // 如果是墙壁，跳出来
            } else if (index == 1) {
                break;
                // 如果是终点，结束栈帧
            } else if (index == 5) {
                maze[row][col - 1] = 2;
                return;
            }
        }
    }


    /**
     * 各种走法的函数,如果是遇到墙壁，返回1;如果没遇到东西，返回0;如果是终点，返回5.
     * !!!!!!!!!!!!!回溯的时候，当年的哪个数值已经被修改了！所以如果，回溯的时候，可能会判断
     * 为2。如果为2，直接结束该栈帧.
     */
    private static int diffWay(int[][] map,int row,int col) {
        if (map[row][col] == 2) {
            return 2;
        } else if (map[row][col] == 1) {
            return 1;
        } else if (map[row][col] == 0) {
            return 0;
        } else {
            return 5;
        }
    }
    /*
     *
     *
     *
     *
     *
     *
     *
     */
    /**
     * 老师的找迷宫方法: 如果终点坐标被走过了（标记为2），直接结束函数。如果该坐标的数值为0，假设该坐标可以走，先给他赋值为2，然后进行下右上左地走。如果上下左右都走不通，赋值该点为3（死路）,然后递归结束回溯回去。
     * 如果该点的坐标不是0，有可能是1，也有可能是3，返回false
     */
    private static boolean teacher_SeekWay(int[][] maze,int row,int col) {
        if (maze[6][5] == 2)
            return true;
        if (maze[row][col] == 0) {
            // 假设这个点可以走得通，先给他赋值为2，假设下右上左都走不了，就赋值3（死路）
            maze[row][col] = 2;
            // 进行下右上左地走
            if (teacher_SeekWay(maze,row + 1,col)) {
                return true;
            } else if (teacher_SeekWay(maze,row,col + 1)) {
                return true;
            } else if (teacher_SeekWay(maze,row - 1,col)) {
                return true;
            } else if (teacher_SeekWay(maze,row,col - 1)) {
                return true;
            } else { // 有可能往下、右、左、上的情况都为false
                maze[row][col] = 3;
                return false;
            }
        } else { // 既然maze[row][col]!=0，那就有可能是墙（0），死路（3），走过的路（2）
            return false;
        }
    }



}
