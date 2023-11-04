package com.lzm.ds.tree.threadedBinaryTree;

/**
 * @Author lzm
 * @Date 2023/8/24 13:43
 * (中序)线索二叉树的实现（使底下的叶子节点的左右节点都放上数据）
 * 实现的最难的部分，
 * 前驱：left指针域指向pre
 * 后继：递归让preAssist节点进入最深，存起来；然后回去的时候，让preAssist的右指针指向 当前Node.
 */
public class ThreadedBinaryTree {
    Node headNode;
    Node preAssist;

    public static void main(String[] args) {
        Node node = new Node(7, "孙七");
        Node node1 = new Node(13, "十三姨");
        Node node2 = new Node(4, "李四");
        Node node3 = new Node(2, "二奶");
        Node node4 = new Node(3, "张三");
        Node node5 = new Node(6, "赵六");
        Node node6 = new Node(8, "周八");
        Node node7 = new Node(18, "1.8纪念节");


        node.setRight(node1);
        node1.setLeft(node6);
        node1.setRight(node7);
        node.setLeft(node2);
        node2.setRight(node5);
        node2.setLeft(node3);
        node3.setRight(node4);


        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(node);
        threadedBinaryTree.ThreadedNode();

        threadedBinaryTree.ThreadedTreeTraverse();
    }

    // 重载线索化方法
    public void ThreadedNode() {
        this.ThreadedNode(headNode);
    }

    // 写线索化节点的方法(中序：左根右)
    public void ThreadedNode(Node node) {
        if (node == null) {
            return;
        }
        // 左递归
        ThreadedNode(node.getLeft());

        // 如果该节点的左节点没有东西，就放上前驱节点，并标记（方便遍历）
        // 这里注意！最前面数的前驱节点虽然是null，但leftType也被赋为1了。
        if (node.getLeft() == null) {
            node.setLeft(preAssist);
            node.setLeftType(1);
        }
        // 逆天的右指针设置，先递归让preAssist节点进入最深，存起来；然后回去的时候，再让preAssist的右指针指向当前Node（暂时不要先管right指针）.
        if (preAssist != null && preAssist.getRight() == null) {
            preAssist.setRight(node);
            preAssist.setRightType(1);
        }
        // extremely important!
        preAssist = node;

        // 右递归
        ThreadedNode(node.getRight());
    }


    // 对线索化的二叉树进行遍历
    // 思路：不同于一般的二叉树，不用递归去找，只需循环找就行。先找到最左的元素，然后如果是右子树，进去、如果是后继节点，也进去找
    private void ThreadedTreeTraverse() {
        Node curNode = headNode;

        while (curNode != null) {
            // 先循环找到当前树的最左、最小的那个元素(最左元素的leftType 是 1)
            while (curNode.getLeftType() == 0) { // 默认0是树枝节点，我都tm要跳过.
                curNode = curNode.getLeft();
            }
            // 打印当前节点
            System.out.println(curNode);

            // 如果右指针是右继节点的话，直接让curNode进入（从叶子节点上去非叶子节点）
            if (curNode.getRightType() == 1) {
                curNode = curNode.getRight();
                System.out.println(curNode);
            }
            // 更新节点
            curNode = curNode.getRight();
        }
    }


    public ThreadedBinaryTree(Node headNode) {
        this.headNode = headNode;
    }


}

// 节点类(能线索化的节点)
class Node {
    private int id;
    private String name;

    // 为什么要设置左右节点类型？为了遍历方法而做准备
    private Node left; // 左节点
    private Node right; // 右节点

    // 因为该节点可能放前驱或者左子树，所以我需要去区分一下
    // 0默认节点为树枝，1为前驱或后继
    private int leftType;
    private int rightType;

    // 构造方法
    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序遍历
    public void preSort() {
        // 根
        System.out.println(this);
        // 左
        if (this.left != null) {
            left.preSort();
        }
        // 右
        if (this.right != null) {
            right.preSort();
        }
    }

    // 中序遍历
    public void infixSort() {
        // 左
        if (this.left != null) {
            left.infixSort();
        }
        // 根
        System.out.println(this);
        // 右
        if (this.right != null) {
            right.infixSort();
        }
    }

    // 后序遍历
    public void suffixSort() {
        // 左
        if (this.left != null) {
            left.suffixSort();
        }
        // 右
        if (this.right != null) {
            right.suffixSort();
        }
        // 根
        System.out.println(this);
    }


    // 前序查找
    public Node preSearch(int id) {
        if (this.id == id) {
            return this;
        }

        Node resNode = null;
        // 左递归，如果左递归找到了，就return
        if (this.left != null) {
            resNode = this.left.preSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }

        // 右递归，
        if (this.right != null) {
            resNode = this.right.preSearch(id);
        }
        return resNode;
    }

    // 中序查找
    public Node infixSearch(int id) {
        Node resNode = null;

        // 左递归，如果左递归找到了，就return
        if (this.left != null) {
            resNode = this.left.infixSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        // 根
        if (this.id == id) {
            return this;
        }
        // 右递归，
        if (this.right != null) {
            resNode = this.right.infixSearch(id);
        }
        return resNode;
    }

    // 后序查找
    public Node suffixSearch(int id) {
        Node resNode = null;
        // 左递归，如果左递归找到了，就return
        if (this.left != null) {
            resNode = this.left.suffixSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        // 右递归，
        if (this.right != null) {
            resNode = this.right.suffixSearch(id);
        }
        // 中
        if (this.id == id) {
            return this;
        }
        return resNode;
    }


    // 简单的节点删除: 如果是叶子节点，直接删除；如果是非叶子节点，整个树枝删除.
    public boolean delNode(int id) {
        // 按照根左右来执行
        // 根
        // if (this.id == id){
        //     this.left.right = this.right;
        //     return true;
        // }

        // 左
        if (this.left != null) {
            if (this.left.id == id) {
                this.left = null;
                return true;
            }
            // 左递归
            if (this.left.delNode(id)) {
                return true;
            }
        }

        // 右
        if (this.right != null) {
            if (this.right.id == id) {
                this.right = null;
                return true;
            }
            // 右递归
            if (this.right.delNode(id)) {
                return true;
            }
        }
        return false;
    }
}

