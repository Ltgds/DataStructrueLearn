public class lowestAncestor {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    //最低公共祖先
    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        //遇到空返回空,遇到o1返回o1,遇到o2返回o2
        if (head == null || head == o1 || head == o2) {
            return head;
        }

        //左树的返回值和右树的返回值
        Node left = lowestAncestor1(head.left, o1, o2);
        Node right = lowestAncestor1(head.right, o1, o2);

        //若返回值都不为空,返回当前的头
        if (left != null && right != null) {
            return head;  //head就是最初的汇聚点
        }

        //左右两棵树并不都有返回值
        //哪个不为空返回哪个
        return left != null ? left : right;
    }
}
