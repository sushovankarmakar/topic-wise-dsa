package src;

class Node {
    int data;
    Node next, prev;

    Node(int x) {
        data = x;
        next = null;
        prev = null;
    }

    public static Node inputList(String input) {

        String[] s = input.split("\\s+");
        Node head = new Node(Integer.parseInt(s[0])), tail = head;
        for (int i = 1; i < s.length; i++) {
            Node temp = new Node(Integer.parseInt(s[i]));
            tail.next = temp;
            tail.next.prev = tail;
            tail = tail.next;
        }
        return head;
    }

    public static void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }
}