package com.lzm.ds.huffmanCode;

import java.util.*;

/**
 * @Author lzm
 * @Date 2023/9/2 17:02
 * 完成的需求：实现赫夫曼编码。
 * 巧妙的完成字符的统计：确实是用Map太巧妙了，我还想搞一遍遍去循环找...
 */
public class huffmanCode {
    public static void main(String[] args) {

        // 去遍历Map的方法:调用Map中的KeySet或者EntrySet<>。

        // HashMap<Integer, String> map = new HashMap<>();
        // map.put(1,"舍费尔先生");
        // map.put(43,"我自己");
        // map.put(2,"卢森堡博士");
        // map.put(4,"思文老师");
        // map.put(423,"稻盛和夫先生");
        //
        // Set<Map.Entry<Integer, String>> set = map.entrySet();
        //
        // for (Map.Entry<Integer, String> p:set) {
        //     System.out.println(p.getKey());
        // }

        String transmit = "i like like like java do you like a java";
        List<Node> nodes = getNodes(transmit);

    }

    /**
     * @param chuanShu 要传输的字符串
     * @return 返回装好了每个节点的集合
     */
    public static List<Node> getNodes(String chuanShu){
        // 难点：如何统计每个char出现的次数呢？用循环吗？ ———— no
        ArrayList<Node> list = new ArrayList<>();

        // 用哈希表来记录字符串中出现的次数
        HashMap<Byte, Integer> map = new HashMap<>();

        byte[] data = chuanShu.getBytes();

        for (byte i:data) {
            // 复习map方法：通过key去获取对应的value
            Integer inAll = map.get(i);
            if (inAll == null){
                map.put(i,1);
            } else {
                // 一定是得先++，不能后++.
                map.put(i,++inAll);
            }
        }
        // 出来之后，map中已经放好了每个字符及对应的出现次数

        // 把Map中的信息转去List中(遍历Map)，因为List方便操作
        Set<Map.Entry<Byte, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Byte, Integer> i:entrySet) {
            list.add(new Node(i.getKey(),i.getValue()));
        }

        return list;
    }
}

class Node implements Comparable<Node> {
    Byte data; // 字符
    int weighted;// 字符出现的次数(构建赫夫曼树的权值)
    Node left;
    Node right;

    public Node(Byte data, int weighted) {
        this.data = data;
        this.weighted = weighted;
    }

    @Override
    public int compareTo(Node o) {
        return this.weighted - o.weighted;
    }


    @Override
    public String toString() {
        return "Node{" + "data=" + data + ", weighted=" + weighted + '}';
    }
}



