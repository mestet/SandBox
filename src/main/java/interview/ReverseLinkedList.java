package interview;

import java.util.StringJoiner;

public class ReverseLinkedList {

    public static class Node {
        Node next;
        int val;

        Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node node = ReverseLinkedList.generateList(15);
        printAll(node);
        Node last = getLast(node);
        System.out.println(last.val);

        Node tail = reverse(node);
        printAll(tail);
    }

    public static Node generateList(int amount) {
        Node start = new Node(0);
        Node cur = start;
        for (int i = 1; i < amount; i++) {
            Node next = new Node(i);
            cur.next = next;
            cur = next;
        }
        return start;
    }

    public static void printAll(Node node) {
        System.out.println("Print nodes");
        Node cur = node;
        StringJoiner joiner = new StringJoiner("->");
        while (cur != null) {
            joiner.add(String.valueOf(cur.val));
            cur = cur.next;
        }
        System.out.println(joiner);
    }

    public static Node getLast(Node init) {
        Node curr = init;
        Node result = null;
        while (curr != null) {
            Node next = curr.next;
            if (next == null) {
                result = curr;
            }
            curr = next;
        }
        return result;
    }

    static Node reverse(Node node) {
        Node prev = null;
        Node cur = node;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
