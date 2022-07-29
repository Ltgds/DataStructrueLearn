import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Order {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    
    /**
     * 前序遍历实现对二叉树的递归
     */
    public static void preOrder(Node head) {
        if(head == null) {
            return;
        }

        System.out.println(head.value + " ");
        preOrder(head.left);
        preOrder(head.right);
    }

    //非递归实现二叉树的前序遍历
    public static void preOrderUnCur(Node head) {
        if(head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();

        //将头节点压入栈中
        stack.push(head);
        while(!stack.isEmpty()) {
            head = stack.pop();
            System.out.println(head.value + " ");
            //先右后左
            if(head.right != null) {
                stack.push(head.right);
            }
            if(head.left != null) {
                stack.push(head.left);
            } 
        }
    }

    //后序遍历--非递归   根右左的逆序,
    public static void postOrderUnCur(Node head) {
        if(head != null) {
            //设置两个栈
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            //将头节点压入s1中
            s1.push(head); 
            while(!s1.isEmpty()) {
                head = s1.pop(); //弹出当前节点
                s2.push(head); //放入s2中
                //先左再右
                if(head.left != null) {
                    s1.push(head.left);
                }
                if(head.right != null) {
                    s1.push(head.right);
                }
            }

            //全部装入s2后,打印s2
            while(!s2.isEmpty()) {
                System.out.println(s2.pop().value + " ");  
            }
        }
    }


    //中序遍历--非递归
    //将head的左节点压入栈中
    //依次弹出,然后对右节点也如此
    public static void inOrderUnRecur(Node head) {
        if(head != null) {
            Stack<Node> stack = new Stack<Node>();
    
            while(head != null || !stack.isEmpty()) {
                if(head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.value + " ");
                    head = head.right;
                }
            }
        }
    }

    public static void inOrderUnRecur1(Node head) {
        if(head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);

            while(head != null || !stack.isEmpty()) {
                if(head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.value + " ");
                    head = head.right;
                }
            }
        }
    }

    //求一棵树的宽度
    public static int getMaxWidth(Node head) {
        if(head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Map<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; //当前的层数
        int curLevelNodes = 0; //当前层的节点数
        int max = Integer.MIN_VALUE; //设置最大宽度
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur); //获取当前节点的层数
            if(curNodeLevel == curLevel) { //若在同一层则节点数++
                curLevelNodes++;
            } else { //否则进入了下一层
                max = Math.max(max, curLevelNodes); //将max
                curLevel++; //层数加1
                curLevelNodes = 1; //当前层节点数设置为1
            }
            System.out.println(cur.value + " ");
            if(cur.left != null) {
                levelMap.put(cur.left, curNodeLevel+1);
                queue.add(cur.left);
            }
            if(cur.right != null) {
                levelMap.put(cur.right, curNodeLevel+1);
                queue.add(cur.right);
            }
        }
        return max;
    }

}
