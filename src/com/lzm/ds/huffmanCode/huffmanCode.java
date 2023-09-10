package com.lzm.ds.huffmanCode;

import java.io.*;
import java.util.*;

/**
 * @Author lzm
 * @Date 2023/9/2 17:02
 * 完成的需求：实现赫夫曼编码的映射。
 * 巧妙的完成字符的统计：确实是用Map太巧妙了，我还想搞一遍遍去循环找...
 */
public class huffmanCode {
    static int countOfMeta;
    public static void main(String[] args) {


        String s = "if your father can't pay the rent,go and ask mikey mantle,see what he tell you,mikey mantle don't care you,so why you care about him.Nobody cares.";

        byte[] bytes = fengzhuang(s.getBytes());
        byte[] decode = decode(bytes, codeMap, countOfMeta);

        // String src = "D:\\资料2-21天学通C++第8版 高清完整PDF.pdf";
        // String des = "D:\\资料2-21天学通C++第8版 高清完整PDF.zip";
        // FileZip(src,des);
        //
        // String src1 = "D:\\资料2-21天学通C++第8版 高清完整PDF.zip";
        // String des1 = "D:\\资料2-21天学通C++第8版 高清完整PDF（2）.pdf";
        // FileDeZip(src1,des1);


        // String src = "D:\\new.bmp";
        // String des = "D:\\new.zip";
        // FileZip(src,des);
        //
        // String src1 = "D:\\new.zip";
        // String des1 = "D:\\new1.bmp";
        // FileDeZip(src1,des1);

        // String src = "E:\\壁纸\\3619-2022-05-26121728-1653538648669.jpg";
        // String des = "D:\\des.zip";
        // FileZip(src,des);
        //
        // String src1 = "D:\\des.zip";
        // String des1 = "D:\\photo.jpg";
        // FileDeZip(src1,des1);

        // String src = "D:\\信息收集表.docx";
        // String des = "D:\\信息收集表.zip";
        // FileZip(src,des);
        //
        // String src1 = "D:\\信息收集表.zip";
        // String des1 = "D:\\信息收集表2.docx";
        // FileDeZip(src1,des1);

        // 重大发现，用一个byte是放不了一个中文的字符的,UTF-8中，一个中文占3个字节
        // StringBuilder builder1 = new StringBuilder();
        // builder1.append("1");
        // builder1.append("42");
        // builder1.append("dsfa");
        // builder1.append("梁");
        // builder1.append("孔芷娴");
        //
        // byte[] b = builder1.toString().getBytes();
        // System.out.println(b.length);
        // System.out.println(builder1.length());


        // 对String.getBytes()方法的研究
        // String s = "1100abc";
        // byte b[] = s.getBytes();
        // System.out.println(Arrays.toString(b));//[49, 49, 48, 48, 97, 98, 99]


        // byte[] bytes = fengzhuang(new byte[]{'2', '3', '4', '5', '6'});
        // byte[] decode = decode(bytes, codeMap, countOfMeta);
        // System.out.println(Arrays.toString(decode));


    }

    /**
     * NO Problem
     * @param chuanShu 要传输2的字符串
     * @return 返回装好了每个节点的集合
     */
    public static List<Node> StringToNode(byte[] chuanShu) {
        if (chuanShu.length == 0){
            return null;
        }

        // 难点：如何统计每个char出现的次数呢？用循环吗？ ———— no
        ArrayList<Node> list = new ArrayList<>();

        // 用哈希表来记录字符串中出现的次数
        // 如果要统计每个数对应了某个值，这种关系用哈希表统计再好不过了！
        HashMap<Byte, Integer> map = new HashMap<>();

        for (byte i : chuanShu) {
            // 复习map方法：通过key去获取对应的value
            // 看似我在get出value，实则是看一下哈希表中有没有该元素
            Integer inAll = map.get(i);

            // 哈希表中没有该元素
            if (inAll == null) {
                map.put(i, 1);
            } else {
                // 一定是得先++，不能后++.
                map.put(i, ++inAll);
            }
        }
        // 出来之后，map中已经放好了每个字符及对应的出现次数（node节点所需的权值）

        // 把Map中的信息转去List中(遍历Map)，因为List方便操作

        for (Map.Entry<Byte, Integer> i : map.entrySet()) {
            list.add(new Node(i.getKey(), i.getValue()));
        }
        return list;
    }





    /**
     * No Problem(构建哈夫曼数)
     * 给你一个List，给我返回一个赫夫曼树的头节点
     * @return 返回赫夫曼树的root节点
     */
    public static Node createHuffmanTree(List<Node> list) {
        if (list.size() == 0){
            return null;
        }
        while (list.size() > 1) {
            Collections.sort(list);

            Node node = list.get(0);
            Node node1 = list.get(1);
            Node parentNode = new Node(null, node.weighted + node1.weighted);

            parentNode.left = node;
            parentNode.right = node1;

            list.remove(node);
            list.remove(node1);

            list.add(parentNode);
        }
        return list.get(0);
    }





    // 这个哈希表key是字符，value是对应的二进制编码，例:1101
    // 这个哈希表是为了getMappedCode方法而服务
    static HashMap<Byte, String> codeMap = new HashMap<>();

    // 这个StringBuilder是用来存放字符对应的编码值
    static StringBuilder builder = new StringBuilder();

    /**
     * No Problem
     * getMappedCode: 获取映射后的编码(即每个Byte所对应的二进制序列)
     * 弹幕留言：递归真是一个好东西！
     * 通过那颗哈夫曼树，将每个字符映射出对应的编码
     * @param node 在赫夫曼树中需要遍历的节点
     * @param code 需往下一个节点传输的标志（例如：往左边递归传0；往右边递归传1）
     * @param builder 用于拼接路径
     */
    public static void getMappedCode(Node node, String code, StringBuilder builder) {

        // 因为每个叶子节点的String都不一样，所以我得new出一个新的StringBuilder
        StringBuilder builder1 = new StringBuilder(builder);
        // 让builder接收上一层来的路径值
        builder1.append(code);

        if (node != null) { // 判断该节点是叶子节点，还是非叶子节点

            if (node.data == null){ // 如果data的数据类型是char，就存不了null了！
                // 非叶子节点
                // 左递归
                getMappedCode(node.left,"0",builder1);
                // 右递归
                getMappedCode(node.right,"1",builder1);
            } else {
                // 叶子节点
                codeMap.put(node.data, builder1.toString());
            }
        }
    }





    /**
     * 本来传输的是byte[] = {i like a java...}，现在有了对应的二进制编码，我需要你把字符对应的二进制编码整入byte数组中，因为数据在传输的过程中，是以字节的形式传输例：10101000这个数存进byte数组中
     * 主要的方法：Integer.ParseInt(string,radix)，把字符串中的二进制格式转成int
     */
    public static byte[] binaryDataToByte(byte[] transmit) {
        // 把transmit这个字符串通过codeMap转成String类型的二进制序列。
        StringBuilder builder = new StringBuilder();
        for (byte b:transmit) {
            // 通过codeMap，获取每个字符对应的二进制编码
            builder.append(codeMap.get(b));
        }
        String newChuanShu = builder.toString();


        // 先计算需要byte数组的大小是多少（每八个二进制放一个字节）
        // 能整除8的话，加了7之后，照样也是那个结果
        int count0 = (newChuanShu.length() + 7) / 8;
        byte[] newChuanShuArr = new byte[count0];

        int count1 = 0; // 新数组的计时器
        // 接下来就是把String里面的东西放进byte数组中（每八位放一个进byte）
        for (int i = 0; i < newChuanShu.length(); i += 8) {
            // 考虑可能会越界
            // 这里i + 8 只需 > newChuanShu.length()就行，不用i + 8 >= ..，因为字符串的subString（index,finalIndex）方法，截取的数据不包括finalIndex下标的元素.
            if (i + 8 > newChuanShu.length()){
                String toEnter = newChuanShu.substring(i);
                // 这里一定得要强转，不然符号位就会丢失！
                newChuanShuArr[count1] = (byte)Integer.parseInt(toEnter,2);
                count1++;

            } else {
                String toEnter = newChuanShu.substring(i,i + 8);
                newChuanShuArr[count1] = (byte)Integer.parseInt(toEnter,2);
                count1++;
            }
        }
        return newChuanShuArr;
    }


    // 稍微的对上面四个方法进行封装
    public static byte[] fengzhuang(byte[] transmit) {
        countOfMeta = transmit.length;
        // 封装字符成Node并放进List中.
        List<Node> nodes = StringToNode(transmit);

        // 把Node构建成赫夫曼树
        Node root = createHuffmanTree(nodes);
        System.out.println(root);
        // 通过上面的二叉树，我获得每个字符所对应的二进制编码（在codeMap中）
        getMappedCode(root,"",builder);

        // 获取压缩后的byte数组
        byte[] bytes = binaryDataToByte(transmit);
        System.out.println(Arrays.toString(bytes));
        return bytes;
    }



    // 如何把那一个byte【】转回原字符？
    // ①先把[-88, -65, -56, -65...]转成二进制的String
    // ②再把这些二进制通过哈希表转成原字符


    /**
     * 传一个里面是十进制类型数字的字节，返回一个字符串类型的二进制数。
     * 例：-1 -> "1111 1111"
     *
     * 这个byte有可能是负数、正数（需要补位->骚操作：按位或上256）、最后一位不需要补！
     * 为什么最后一位byte不需要补？
     * 例：byte[-88,34,10] -> "10011111 00001010 1010",
     * 如果10补位了，那不就改变了原数据吗？！
     *
     * 这个方法很大功劳是：Integer的toBinaryString方法
     * @param isLast 判断是否是最后一个字节
     * @param b 要变成二进制字符串的字节
     * @return byte变成String的模样
     */
    public static String byteToBitString(byte b, boolean isLast){
        // 传说中，Integer中的toBinaryString方法可以将int转成二进制形式的String类型
        // 分两种情况：如果isLast为true，不用补位（按位或256）

        int i = b; // 把byte转成int
        if (!isLast){ // 如果不是最后一位byte，就要补位(不管正负)
            i |= 256;
        }

        String s = Integer.toBinaryString(i);


        if (!isLast){
            return s.substring(s.length() - 8);
        }

        if (i < 0) {
            return s.substring(s.length() - 8);
        } else {
            return s;
        }
    }


    // 正式解码操作
    public static byte[] decode(byte[] transmit,Map<Byte,String> codeMap,int countOfTransmit){
        // 把二进制格式的字符串重新拿回来
        StringBuilder builder = new StringBuilder();

        // （真实体验）果然我把问题呈现在大脑里，从深夜到黎明地去思考的话，神明真的会动下懒腰帮助我一下：
        // 如果前面的字节和最后一个字节相同那不就废了（更多可能的排列组合，要么多要么少，怪不得之前测试的时候，有时行有时不行。即使是行也打开不了文件）？
        // for (byte i:transmit) {
        //     if (i == transmit[transmit.length - 1]){
        //         // 最后一位
        //         builder.append(byteToBitString(i,true));
        //     } else {
        //         // 不是最后一位
        //         builder.append(byteToBitString(i,false));
        //     }
        // }

        for (int i = 0; i < transmit.length; i++) {
            if (i != transmit.length - 1){ // 不是最后一位
                builder.append(byteToBitString(transmit[i],false));
            } else { // 最后一位
                builder.append(byteToBitString(transmit[i],true));
            }
        }


        // 反转哈希表：map中的entrySet可以助你一臂之力
        HashMap<String, Byte> reverseCodeMap = new HashMap<>();
        for (Map.Entry<Byte,String> m:codeMap.entrySet()) {
            reverseCodeMap.put(m.getValue(),m.getKey());
        }
        // 把builder里面的东西放去byte数组中——方便遍历
        char[] binaryData = builder.toString().toCharArray();
        // System.out.println(Arrays.toString(binaryData));

        // metaData:原数据，传输过来的原数据（需要把它还原的东西）
        byte[] metaData = new byte[countOfTransmit];
        int indexOfMeta = 0; //

        // 装不同key的builder1
        StringBuilder builder1 = new StringBuilder();


        for (int i = 0; i < binaryData.length; i++) {
            builder1.append(binaryData[i]);
            Byte b = reverseCodeMap.get(builder1.toString());
            if (b == null){ // 找不到

            } else { // 找到了
                metaData[indexOfMeta++] = b;
                builder1.setLength(0);
            }
        }

        // 来到这里，metaData里面就装好了对应的byte数据，只需把byte数据转成char数据就行.
        // char[] s = new char[countOfTransmit];
        // int countOfs = 0;
        // for (byte b:metaData) {
        //     s[countOfs++] = (char)b;
        // }
        return metaData;
    }



    // 写一个方法，真正实现文件压缩(应用于IO流)
    public static void FileZip(String srcFile, String desFile){

        try (FileInputStream fis = new FileInputStream(srcFile); FileOutputStream fos = new FileOutputStream(desFile); ObjectOutputStream oos = new ObjectOutputStream(fos)){

            // 创建读取目标文件的byte数组
            byte[] b = new byte[fis.available()];
            System.out.println(b.length);
            // 把目标文件读取进b数组中
            fis.read(b);
            // 把这个byte数组传去fengzhuang函数进行压缩
            byte[] huffmanBytes = fengzhuang(b);
            // 把huffmanBytes和codeMap这两个对象都io进文件中——用writeObject方法.
            oos.writeObject(huffmanBytes);
            oos.writeObject(codeMap);
            oos.writeObject(countOfMeta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    // 文件解压方法
    public static void FileDeZip(String src, String des){
        try (FileInputStream fis = new FileInputStream(src);
             ObjectInputStream ois = new ObjectInputStream(fis);
             FileOutputStream fos = new FileOutputStream(des)) {

            // 把那三个对象拿回来（压缩后的byte数组、HashMap<Byte,String>、countOfMeta）
            byte[] b = (byte[]) ois.readObject();
            Map<Byte,String> map = (HashMap<Byte, String>)ois.readObject();
            int i = (int)ois.readObject();

            byte[] meta = decode(b,map,i);
            fos.write(meta);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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

// 玩一下需要传入TreeSet或TreeMap的比较器
class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        return o1.data - o2.data;
    }
    public static Comparator<Node> compareByWeighted(){
        return new Comparator<Node>(){
            @Override
            public int compare(Node o1, Node o2) {
                return o1.weighted - o2.weighted;
            }
        };
    }
}


