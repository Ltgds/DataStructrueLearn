public class getSuccessorNode {
    

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 后继节点
     * 
     * 二叉树的中序遍历中
     * 
     * 若x有右树,则其后继节点为x右树上的最左节点
     * 若x有左树,则其前驱节点为x左树上的最右节点
     * 整棵树最有的节点无后继,为null
     */
    public static Node getSuccessor(Node node) {
        if(node == null) {
            return node;
        }

        //若有右树,则获取其右树上的最左节点
        if(node.right != null) {
            return getLeftMost(node.right);
        } else {
            //若无右树
            Node parent = node.parent;
            //当我一致是我父亲的右孩子时继续
            //设node是其父亲左树的最右节点
            while(parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;  //一层一层往上找
            }
            return parent; //只有当node是整棵树上的最右节点才会返回null
        }
        
    }

    //找到最左节点
    private static Node getLeftMost(Node node) {
        if(node == null) {
            return node;
        }

        while(node.left != null) {
            node = node.left;
        }
        return node;
    }

}
