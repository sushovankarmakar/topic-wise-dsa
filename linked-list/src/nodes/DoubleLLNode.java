package src.nodes;

public class DoubleLLNode {

    public static void main(String[] args) {
        DoubleLLNode head = create(new int[]{1, 2, 3, 4, 5});
        print(head);
        printInReverse(head);
    }

    public int val;
    public DoubleLLNode next;
    public DoubleLLNode prev;

    public DoubleLLNode() {
    }

    public DoubleLLNode(int val) {
        this.val = val;
    }

    public DoubleLLNode(int val, DoubleLLNode next, DoubleLLNode prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }

    public static void print(DoubleLLNode head) {
        StringBuilder sb = new StringBuilder("FRONT   : ");
        while (head != null) {
            sb.append(head.val).append(" ");
            head = head.next;
        }
        System.out.println(sb);
    }

    public static void printInReverse(DoubleLLNode head) {
        while (head.next != null) {
            head = head.next;
        }

        StringBuilder sb = new StringBuilder("REVERSE : ");
        while (head != null) {
            sb.append(head.val).append(" ");
            head = head.prev;
        }

        System.out.println(sb);
    }

    public static DoubleLLNode create(int[] values) {

        DoubleLLNode head = new DoubleLLNode(values[0]);
        DoubleLLNode tail = head;

        for (int i = 1; i < values.length; i++) {
            DoubleLLNode newNode = new DoubleLLNode(values[i]);

            tail.next = newNode;
            newNode.prev = tail;

            tail = tail.next;
        }
        return head;
    }
}
