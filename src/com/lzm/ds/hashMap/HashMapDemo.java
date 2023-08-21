package com.lzm.ds.hashMap;

/**
 * @Author lzm
 * @Date 2023/8/19 22:10
 */
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap(7);
        Emp emp0 = new Emp(1, "梁振明");
        Emp emp1 = new Emp(2, "孔芷娴");
        Emp emp2 = new Emp(3, "邓春婵");
        Emp emp3 = new Emp(4, "张三");
        Emp emp4 = new Emp(5, "李四");
        Emp emp5 = new Emp(9, "孔芷娴二号");
        Emp emp6 = new Emp(16, "孔芷娴三号");

        hashMap.add(emp0);
        hashMap.add(emp1);
        hashMap.add(emp2);
        hashMap.add(emp3);
        hashMap.add(emp4);
        hashMap.add(emp5);
        hashMap.add(emp6);

        hashMap.showHashMap();
        hashMap.findEmpById(17);
    }
}

// 员工
class Emp {
     int id;
     String name;
     // 下一个节点默认为空.
     Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}



// 员工链表
class EmpLinkedList {
    private Emp head;
    // 加元素
    public void addEmp(Emp emp){
        if (head == null){
            head = emp;
            return;
        }
        // 寻找最后一个节点
        Emp curEmp = head;
        while (curEmp.next != null){
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    // 遍历元素
    public void showLinkedList(){
        if (head == null){
            System.out.print("这行没元素");
            return;
        }
        Emp curEmp = head;
        while (curEmp != null){
            System.out.print("id = " + curEmp.id + " name = " + curEmp.name + "\t\t");
            curEmp = curEmp.next;
        }
    }

    // 寻找元素
    public void findEmpByNo(int id){
        if (head == null){
            System.out.println("该ID为: " + id + " 的员工找不到!");
            return;
        }
        // 将这id与链表上的id一一比对，如果都为false，就找不到咯
        Emp curEmp = head;
        while (curEmp.next != null){
            if (id == curEmp.id){
                System.out.println("找到了，这名员工的名字为: " + curEmp.name);
                return;
            }
            curEmp = curEmp.next;
        }
        // 来到这里是最后一个元素
        if (curEmp.id == id) {
            System.out.println("找到了，这名员工的名字为: " + curEmp.name);
        } else {
            System.out.println("该链表找不到该员工");
        }


    }
}





// 员工哈希表
class HashMap{
    int maxSize;
    EmpLinkedList[] empLinkedListArr;

    // 构造方法
    public HashMap(int maxSize) {
        this.maxSize = maxSize;
        // 临时变量empLinkedListArr只是在栈内存空间开辟了maxSize大小的空间，并且里面放的都是null
        this.empLinkedListArr = new EmpLinkedList[maxSize];
        for (int i = 0; i < empLinkedListArr.length; i++) {
            // 这一步的作用:处在栈内存里面的empLinkedListArr能指向堆内存里new的链表。
            empLinkedListArr[i] = new EmpLinkedList();
        }
    }

    // 来一个哈希函数，计算该放进哪一个链表
    public int hashFunction(int id){
        return id % maxSize;
    }


    // 往哈希表中添加元素
    public void add(Emp emp){
        this.empLinkedListArr[hashFunction(emp.id)].addEmp(emp);
    }

    // 遍历数组
    public void showHashMap(){
        int count = 0;
        for (EmpLinkedList k:empLinkedListArr) {
            System.out.print("第 " + count++ + " 个链表:");
            k.showLinkedList();
            System.out.println();
        }
    }

    // 传一个ID，返回员工的name
    public void findEmpById(int id){
        // 先找到是在哪一个链表
        empLinkedListArr[hashFunction(id)].findEmpByNo(id);
    }
}