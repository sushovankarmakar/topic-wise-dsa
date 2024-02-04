package src.nodes;

public class SingleLLNode {

    public int val;
    public SingleLLNode next;

    public SingleLLNode() {
    }

    public SingleLLNode(int val) {
        this.val = val;
    }

    public SingleLLNode(int val, SingleLLNode next) {
        this.val = val;
        this.next = next;
    }

    public static void print(SingleLLNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static SingleLLNode create(int[] values) {

        SingleLLNode head = new SingleLLNode(values[0]);
        SingleLLNode tail = head;

        for (int i = 1; i < values.length; i++) {
            tail.next = new SingleLLNode(values[i]);
            tail = tail.next;
        }
        return head;
    }
}
