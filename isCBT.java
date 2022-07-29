import java.util.LinkedList;

public class isCBT {
    
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //完全二叉树  complete binary tree
    /**
     * 使用宽度优先遍历：
     * 1.若一个节点有右孩子,但无左孩子,返回false
     * 2.在 1 不违规的情况下,如果遇到了第一个左右孩子不全,则后续都是叶节点
     */
    public static boolean isCBT(Node head) {
        if(head == null) {
            return true;  //
        }

        //宽度优先遍历使用队列
        LinkedList<Node> queue = new LinkedList<>();
        
        //判断是否遇到左右两个孩子不双全的节点
        boolean leaf = false;

        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll(); //弹出节点,拿到他的左右孩子
            l = head.left;
            r = head.right; 
            
            //遇到了不双全的节点之后,发现当前节点还有孩子
            if ((leaf && (l != null || r != null)) || //leaf=true且当前节点不为叶节点
                (l == null && r != null)) { //有右无左
                return false;
            }

            //左孩子不为空,队列中加入左孩子
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) { //若左右都为空
                leaf = true;
            }
        }
        return true;
    }
}
