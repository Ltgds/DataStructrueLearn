import java.util.LinkedList;
import java.util.Queue;

public class serialBinaryTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    
    //序列化和反序列化 二叉树
    /**
     * 序列化
     * @param head
     * @return
     */
    public static String serialByPre(Node head) {
        if(head == null) {
            return "#_";
        }

        //将头转换为字符串并写上下划线
        String res = head.value + " ";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }


    /**
     * 反序列化
     * @param preStr
     * @return
     */
    public static Node reconByPreString(String preStr) {
        //根据下划线做值的分隔
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }


    private static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll(); //弹出队列中的一个值
        if(value.equals("#")) {
            return null;  //若弹出#则建立空节点
        }
        //若不是空,则使用当前value建立节点
        Node head = new Node(Integer.valueOf(value));
        //在建左右子树递归
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }
}
