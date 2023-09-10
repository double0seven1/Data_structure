package com.lzm.ds.avl;


import com.sun.istack.internal.NotNull;

/**
 * @Author lzm
 * @Date 2023/9/7 15:08
 * 所谓的旋转，就是哪边高，对边旋转使高的那边变矮。例如，左边比右边高，就通过右旋把右边弄下去，左边上来(制衡)。
 * AVL树
 * •加元素的时候，什么时候需要双旋？
 * ① 当root的leftHeight较大需要右旋（左上右下）的时候（不知道左节点哪一边大），如果root左节点的rightHeight比leftHeight大，就需要把root左节点进行左旋。
 * 大，就需要将root的左节点进行左旋。
 * ② 当root的rightHeight较大需要左旋（左下右上）的时候，如果root右节点的leightHeight比rightHeight大，就需要将root右节点进行右旋。
 */
public class AVLDemo {
    public static void main(String[] args) {
        int[] arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for (int i : arr) {
            System.out.println(avlTree.root);
            avlTree.addNode(new Node(i));
        }
        System.out.println("-------------------------------");
        avlTree.infixTraverse();
        System.out.println(avlTree.root.leftHeight());
        System.out.println(avlTree.root.rightHeight());
        System.out.println(avlTree.root);
    }
}

class AVLTree {
    Node root;

    // 加元素
    public void addNode(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.addNode(node);
        }

    }

    // 遍历元素
    public void infixTraverse() {
        if (root == null) {
            System.out.println("该数为空，不可遍历");
        } else {
            root.infixTraverse();
        }

    }

    // 查找目标节点
    public Node searchNode(Node node) {
        if (root == null) {
            return null;
        }
        return root.seachNode(node);
    }

    // 查找目标节点的父节点
    public Node findParent(Node node) {
        if (node == null || root == null) {
            return null;
        }
        return root.findParent(node);
    }

    // 删除元素
    public void delNode(Node node) {
        // 这一步非常重要，拿到本尊，如果没有这一步，直接拿参数node比较的话，就会出现此节点非彼节点的情况。
        Node target = searchNode(node);
        if (root == null || target == null) {
            return;
        }

        if (root.equals(target)) {
            // 根节点左右都不为null
            if (root.left != null && root.right != null) {
                root.right.addNode(root.left);
                root = root.right;
            }
            // 根节点左指针域为null
            if (root.left == null && root.right != null) {
                root = root.right;
            }
            // 根节点右指针域位null
            if (root.left != null && root.right == null) {
                root = root.left;
            }
            // 根节点左右指针域都为null
            if (root.left == null && root.right == null) {
                root = null;
            }
        } else {
            // 先拿到父节点
            Node parent = this.findParent(target);

            // 怎么知道这个节点相对于父节点是左还是右呢？
            // 通过parent的左右两边分别对比一下咯~（拿到父节点真的爽）
            // 叶子节点
            if (target.left == null && target.right == null) {
                if (parent.left != null && parent.left.equals(target)) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }

                // 有两个分支的节点
            } else if (target.left != null && target.right != null) {
                if (parent.left != null && parent.left.equals(target)) {
                    parent.left = target.right;
                } else {
                    parent.right = target.right;
                }
                target.right.addNode(target.left);

            } else {
                // 有一个分支的节点
                // 等号比较的是地址！我要比较的是里面的数值！(终于找到bug)
                if (parent.left != null && parent.left.equals(target)) {
                    parent.left = (target.left == null) ? target.right : target.left;
                } else {
                    parent.right = (target.left == null) ? target.right : target.left;
                }
            }
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" + "value=" + value + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        return this.value == ((Node) obj).value;
    }

    public void addNode(@NotNull Node node) {
        if (node == null || this.equals(node)) { // 先考虑特殊情况
            return;
        }

        if (node.value < this.value) { // 新加的节点比当前节点小
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.addNode(node);
            }
        } else { // 新加的节点比当前节点大
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.addNode(node);
            }
        }
        // 如果右子树的高度比左子树高，进行左旋.
        // 下面的代码有问题：例如，左子树的高度是2，右子是1，也会执行方法
        // if (this.leftHeight() < this.rightHeight()) {
        //     leftRotate();
        // } else if (this.rightHeight() < this.leftHeight()) {
        //     rightRotate();
        // }

        // 如果右子树的高度比左子树高(相减大于1)，进行左旋.
        if (this.rightHeight() - leftHeight() > 1){
            // 要左旋时，手动干预一种特殊情况：root右节点的左子树比右子树要高
            if (this.right.leftHeight() > this.right.rightHeight()){
                // 让右子节点进行右旋
                this.right.rightRotate();
            }
            leftRotate();
        }
        // 如果左子树的高度比右子树高(相减大于1)，进行右旋.
        if (this.leftHeight() - rightHeight() > 1) {
            // 要右旋时，手动干预一种特殊的情况：root左节点的右子树比左子树高度要高
            if (this.left.rightHeight() > this.left.leftHeight()){
                // 让left节点进行左旋
                this.left.leftRotate();
            }
            rightRotate();
        }
    }

    public void infixTraverse() {
        if (this.left != null) {
            this.left.infixTraverse();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixTraverse();
        }
    }


    // 高招寻找父节点,找到返回父节点；找不到，返回null
    public Node findParent(Node node) {
        if ((this.left != null && this.left.value == node.value)
                || (this.right != null && this.right.value == node.value)) {
            return this;
        } else {
            if (this.value < node.value && this.right != null) {
                return this.right.findParent(node);
            } else if (this.value > node.value && this.left != null) {
                return this.left.findParent(node);
            }
        }
        return null;
    }

    // 寻找那个节点，折半查找
    public Node seachNode(Node node) {
        if (this.value == node.value) {
            return this;
        } else if (this.left != null && this.value > node.value) {
            return this.left.seachNode(node);
        } else if (this.right != null && this.value < node.value) {
            return this.right.seachNode(node);
        }
        return null;
    }

    // 我要干嘛？求AVL树的高度
    // 肯定得用递归，但没想到老师的递归那么强,那么简洁
    public int height() {
        return Math.max((left == null ? 0 : left.height()),
                (right == null ? 0 : right.height())) + 1;
    }

    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    // 左旋（右子树的高度比左子树的高度高）
    public void leftRotate() {
        // 为什么我直接就敢用this呢？
        // 什么时候才会左旋、右旋呢？ ————添加节点的时候呀。
        // 添加节点的时候都是从root这个节点类开始添加的，所以this铁定就是root。
        Node newNode = new Node(this.value);

        // newNode的左节点指向root的左节点
        newNode.left = this.left;
        // newNode的右节点指向root的右节点的左节点
        newNode.right = this.right.left;
        // 让root节点的value改成右节点的value
        this.value = this.right.value;
        // 让root节点的右节点改成右节点的右节点
        this.right = this.right.right;
        // 让root的左节点指向newNode
        this.left = newNode;
    }

    // 右旋（左子树的长度比右子树高）
    public void rightRotate() {
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }
}