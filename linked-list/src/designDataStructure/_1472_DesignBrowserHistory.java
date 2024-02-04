package src.designDataStructure;

/**
 * https://www.youtube.com/watch?v=mG3KLugbOdc (Striver)
 *
 * https://leetcode.com/problems/design-browser-history/description/
 * https://www.geeksforgeeks.org/problems/design-browser-history/1
 */
public class _1472_DesignBrowserHistory {

    /**
     * Your BrowserHistory object will be instantiated and called as such:
     * BrowserHistory obj = new BrowserHistory(homepage);
     * obj.visit(url);
     * String param_2 = obj.back(steps);
     * String param_3 = obj.forward(steps);
     */

    public static void main(String[] args) {

        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");     // You are in "leetcode.com". Visit "google.com"
        browserHistory.visit("facebook.com");   // You are in "google.com". Visit "facebook.com"
        browserHistory.visit("youtube.com");    // You are in "facebook.com". Visit "youtube.com"

        System.out.println(browserHistory.back(1));     // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
        System.out.println(browserHistory.back(1));     // You are in "facebook.com", move back to "google.com" return "google.com"
        System.out.println(browserHistory.forward(1));  // You are in "google.com", move forward to "facebook.com" return "facebook.com"

        browserHistory.visit("linkedin.com");   // You are in "facebook.com". Visit "linkedin.com"

        System.out.println(browserHistory.forward(2));  // You are in "linkedin.com", you cannot move forward any steps.
        System.out.println(browserHistory.back(2));     // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
        System.out.println(browserHistory.back(7));     // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
    }

    // 1. create a double linked list node.
    // 2. in visit method, create a node and assign it after the current node and update the current point.
    // 3. this current pointer is very useful and will be required to update it in each iteration.
    // 4. in back method, go back until the steps > 0 AND you reach to the head of the node.
    // 5. in forward method, go forward until the steps > 0 AND you reach to the tail of the node.
    static class BrowserHistory {

        static class Node {

            String url;
            Node prev;
            Node next;

            Node(String url) {
                this.url = url;
                this.prev = null;
                this.next = null;
            }
        }

        Node head;
        Node curr;

        public BrowserHistory(String homepage) {
            head = new Node(homepage);
            curr = head;
        }

        public void visit(String url) {

            Node newNode = new Node(url);
            curr.next = newNode;
            newNode.prev = curr;

            curr = curr.next;
        }

        public String back(int steps) {

            while (steps > 0 && curr.prev != null) {    // I made MISTAKE. steps > 0 || curr.prev != null

                curr = curr.prev;
                steps = steps - 1;
            }

            return curr.url;
        }

        public String forward(int steps) {

            while (steps > 0 && curr.next != null) {    // I made MISTAKE. steps > 0 || curr.next != null

                curr = curr.next;
                steps = steps - 1;
            }

            return curr.url;
        }
    }
}