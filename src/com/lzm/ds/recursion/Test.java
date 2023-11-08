package com.lzm.ds.recursion;

/**
 * @Author lzm
 * @Date 2023/7/6 13:15
 */
public class Test {
    public static void main(String[] args) {
        
    }

    // 递归两行代码搞定一个数的阶层
    private static int factorial(int i){
        if (i <= 1)  return 1;
        return i * factorial(i - 1);
    }
}
