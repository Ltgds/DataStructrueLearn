
public class isBalance {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 平衡二叉树  banance binary tree
     * 
     * 使用二叉树递归套路
     * 需要的信息：
     * 左子树：是否是平衡二叉树 树高
     * 右子树：是否是平衡二叉树 树高
     */

    public static boolean isBalanceTree(Node head) {
        return true;
    }

    public static class ReturnType {
        boolean isBalance;
        int height;

        public ReturnType(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public static ReturnType process(Node x) {
        if(x == null) {
            return new ReturnType(true, 0);
        }

        //获取左右树的信息
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);

        boolean isBalance;
        int height;

        //列出所有可能性
        //获取树的高度,左子树和右子树高的那个就是最大高度
        height = Math.max(leftData.height, rightData.height);
        
        //判断是否是平衡树
        isBalance = leftData.isBalance && rightData.isBalance
            && Math.abs(leftData.height - rightData.height) < 2;

        return new ReturnType(isBalance, height);
    }


}
