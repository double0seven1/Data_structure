package com.lzm.ds.statck;
// 弄完用栈模拟计算器后，去结硬寨之前所学！

import java.util.ArrayList;

/**
 * @Author lzm
 * @Date 2023/6/19 16:44
 */
// 一定要你用栈来模拟计算器
public class SimulateCalc {
    // 数据栈
    Stack dataStack = new Stack(10);
    // 符号栈
    Stack symbolStack = new Stack(10);
    // 数据(这里可以用)
    char[] input;

    public SimulateCalc(String input){
        this.input = input.toCharArray();
    }

/**
 * 需要什么方法才能组成这个庞大的系统？
 * 1.判断放进符号栈的符号优先级（如果优先级越高，返回:1;低，返回:0）
 * 2.判断是数字还是符号
 * 3.执行运算的方法
 * 首先放完全部的数据进栈，放完之后再看一下数据栈是不是只有一个元素了f
 */
    public String result(){
        int num1;
        int num2;
        char symbol;
        String sum;
        StringBuilder stringBuilder = new StringBuilder();

        // 把所有的数据压入栈
        for (int i = 0; i < input.length; ) {
            if (input[i] == '#') {
                break;
            }
            // 判断数据类型
            int whatType = numOrChar(input[i]);
            if (whatType == 0) { // 把数字压入栈
                // 考虑多位数！进来之后，继续循环判断是不是数字
                int count = 1;
                stringBuilder.append(Integer.parseInt(String.valueOf(input[i])));
                // 接下来判断下一位是不是数字；
                // 首先要防止数组越界
                while (isNum(input[i + count])) {
                    stringBuilder.append(Integer.parseInt(String.valueOf(input[i + count])));
                    count++;
                }
                i+=count;
                dataStack.push(stringBuilder.toString());
                stringBuilder.setLength(0);

            } else {
                // 来到这里是操作符  如果是空的符号栈，直接加入
                if (symbolStack.topPointer == symbolStack.maxSize) {
                    symbolStack.push(input[i]);
                    i++;
                } else {
                    // 跟符号栈的栈顶符号比较优先级。如果优先级高，直接入符号栈; 如果优先级低或相等，把符号栈的元素弹出来和把数据栈顶和栈顶的下一个数弹出来。
                    if (priority(input[i]) <= priority((char) symbolStack.peek())) {   // 优先级低
                        num1 = Integer.parseInt((String) dataStack.pop());
                        num2 = Integer.parseInt((String) dataStack.pop());
                        symbol = (char) symbolStack.pop();
                        sum = this.count(num1, num2, symbol);
                        dataStack.push(sum);
                    }
                    symbolStack.push(input[i]);
                    i++;
                }
            }
        }
        // 来到这里，都是些剩余的东西，以符号栈为主导，弹出一个符号，就弹两下数据栈
        // 如果从栈底开始读，那用栈的意义？
        while (symbolStack.topPointer != symbolStack.maxSize) {
            num1 = Integer.parseInt((String) dataStack.pop());
            num2 = Integer.parseInt((String) dataStack.pop());
            symbol = (char) symbolStack.pop();
            sum = count(num1,num2,symbol);
            dataStack.push(sum);
        }
        return (String) dataStack.pop();
    }

    /**
     * @return 如果是返回0，表示是数字;如果返回1，表示是操作符
     */
    public int numOrChar(char item){
        if (item == '+' || item == '-' || item == '*' || item == '/')
            return 1;
        return 0;
    }

    /**判断放入符号栈的符号优先级
    * 如果返回1，表示优先级高；返回0，表示优先级低
    */
    public int priority(char item) {
        if (item == '*' || item == '/')
            return 1;
        return 0;
    }

    /**
     *
     * @param num1 栈顶指针指向的数
     * @param num2 栈顶指针的下一个数
     * @param symbol 传入符号
     * @return 返回计算结果
     */
    public String count(int num1, int num2, char symbol) {
        int res = 0;
        if (symbol == '+'){
            res = num1 + num2;
        } else if (symbol == '-') {
            res = num2 - num1;
        } else if (symbol == '*') {
            res = num1 * num2;
        } else if (symbol == '/') {
            res = num2 / num1;
        }
        return String.valueOf(res);
    }

    /*写一个方法专门用来判断该字符是不是整数*/
    public boolean isNum(char data) {
        return data >= 48 && data <= 57;
    }



    /**
     * 中缀表达式转后缀表达式的代码实现（包括带括号）
     * 思路:①定义两个栈，一个用来装结果集（数值栈），另外一个符号栈用来装操作符。
     * ②扫描到数字，直接入数值栈。
     * ③扫描到符号，如果栈顶操作符栈为空或左括号，直接push进符号栈;
     * 如果该符号的优先级高（*或/）,直接压入栈；
     * 如果是右括号，弹出符号栈里的操作符进数值栈，并消除一个左括号。
     * 如果符号优先级相等，pop出符号，然后再压。
     * 得到的字符串，逆序一下。
     */
    public void reverseStr(char[] input) {

    }

    /**
     * 把字符串放在集合中，要考虑多位数
     * 如果是非数字，直接加入list中；如果是数字，while循环判断下一位是否是数字
     */
    public static ArrayList<String> StrToList(String str){
        ArrayList<String> arrayList = new ArrayList<>();
        String joint = ""; // joint abj.联合的 这个主要是为了多位数的字符串拼接
        char item;
        int index = 0;
        // 因为对一个算式字符串长度是不固定的，不知道循环几次，用do..while。
        do {
            // 非数字:
            if ((item = str.charAt(index)) < '0' || (item = str.charAt(index)) > '9'){
                arrayList.add("" + item);
                index++;
            } else {
                // 数字
                joint = "";
                while (index < str.length() && (item = str.charAt(index)) >= '0' && (item = str.charAt(index)) <= '9') {
                    joint += item;
                    index++;
                }
                arrayList.add(joint);
            }
        } while(index < str.length());
        return arrayList;
    }


    /*想要遍历字符串的话，可以不用转成char数组，因为有length和charAt方法。*/
    public static void main(String[] args) {
        String s = "1+((3+2)*4)-6";
        System.out.println(SimulateCalc.StrToList(s));
        // if (s.matches("\\w+"))
        //     System.out.println("\\s表示匹配一个字母、数字或下划线，而\\s+，匹配前面的字符一次或多次");
        // char a = 'a';
        // String b = String.valueOf(a);

    }
}
