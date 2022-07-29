import java.util.Stack;

public class isBST {
    
    //binary search tree
    //对二叉搜索树进行中序排列，则会得到一个从小到大的序列

    //判断一颗二叉树是一个二叉搜索树?
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int preValue = Integer.MIN_VALUE;

    /**
     * 递归方式
     * @param head
     * @return
     */
    public static boolean isBST(Node head) {
        if(head == null) {
            return true;
        }

        boolean isLeftBST = isBST(head.left);
        if(!isLeftBST) { 
            return false;
        }

        //看当前节点是否比之前的大
        if(head.value <= preValue) {
            return false;
        } else {
            preValue = head.value; 
        }
        return isBST(head.right); //若有右子树,重复
    }

    //中序遍历 非递归
    //左根右
    //将左节点依次进栈,然后依次出栈
    public static void inOrderUnRecur(Node head) {
        if(head != null) {
            Stack<Node> stack = new Stack<>();
            while(head != null || !stack.isEmpty()) {
                if(head != null) {  //走到最左
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head + " ");
                    head = head.right;
                }
            }
        }
    }

    //二叉搜索树 非递归形式
    public static boolean isBstUnCur(Node head) {
        if(head == null) {
            return true;
        }

        Stack<Node> stack = new Stack<>();
        while(head != null || !stack.isEmpty()) {
            if(head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();  //先弹出节点

                if(preValue <= head.value) {
                    return false;
                } else {
                    preValue = head.value;
                }

                head = head.right;
            }

        }
        return true;
    }


    //使用递归套路--二叉搜索树
    /**
     * 二叉搜索树当前节点大于左树的最大值,小于右树的最小值
     * 左树需要：是否为二叉搜索树？最大值？最小值？
     * 右树需要：是否为二叉搜索树？最大值？最小值
     * @param head
     * @return
     */
    public static boolean isBST1(Node head) {
        return process(head).isBST;
    } 

    //设置左树和右树需要返回的三个信息
    public static class ReturnType {
        public boolean isBST;
        public int max;
        public int min;

        public ReturnType(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static ReturnType process(Node x) {
        if(x == null) {
            return null;
        }

        //获取左树和右树的信息
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);

        int max = x.value;
        int min = x.value;

        if(leftData != null) { //若左树不为空
            min = Math.min(min, leftData.min);
            max = Math.max(max, leftData.max);
        }
        if(rightData != null) {
            min = Math.min(min, rightData.min);
            max = Math.max(max, rightData.max);
        }

        boolean isBST = true;
        //列出所有可能性
        //若左边有信息且 左边不是二叉搜索树 或 左边的最大值大于当前节点的值
        if(leftData != null && (!leftData.isBST || leftData.max >= x.value)) {
            isBST = false;
        }
        if(rightData != null && (!rightData.isBST || rightData.min <= x.value)) {
            isBST = false;
        }

        return new ReturnType(isBST, max, min);
    }

}
