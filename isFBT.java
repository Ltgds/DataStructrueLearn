

public class isFBT {
    
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 满二叉树 Full Binary Tree
     * 
     * 由于需要满足 k = 2^{n} - 1
     * 
     * 需要的信息：
     * - 深度
     * - 节点数
     */
    public static boolean isFull(Node head) {
        if (head == null) {
            return true;
        }
        ReturnType data = process(head);
        return data.nodes == ((1 << data.height) - 1);
    }

    public static class ReturnType {
        public int height;
        public int nodes;

        public ReturnType(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static ReturnType process(Node x) {
        if(x == null) {
            return new ReturnType(0, 0);
        }

        //获取左树和右树的信息
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);
        
        int height = Math.max(leftData.height, rightData.height);
        int nodes = leftData.nodes + rightData.nodes + 1;

        return new ReturnType(height, nodes);
    }
}
