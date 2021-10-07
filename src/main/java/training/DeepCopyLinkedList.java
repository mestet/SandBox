package training;


import lombok.AllArgsConstructor;

import java.util.*;


public class DeepCopyLinkedList {

    public static void main(String[] args) {
        DeepCopyLinkedList instance = new DeepCopyLinkedList();
        Node head = instance.generate(13);
        instance.printAllNodes(head);
        System.out.println("=====================================");
        Node copy = instance.copy(head);
        instance.printAllNodes(copy);
        Node copyInPlace = instance.copyInPlace(head);
        instance.printAllNodes(copyInPlace);
        System.out.println("=====================================");
        instance.printAllNodes(head);

    }

    static class Node {
        String val;
        Node next;
        Node random;

        Node valueCopy() {
            Node copy = new Node();
            copy.val = this.val + "_c";
            return copy;
        }
    }

    public Node copy(Node input) {
        LinkedHashMap<Node, Node> originalCopyMap = new LinkedHashMap<>();

        Node subj = input;
        while (subj != null) {
            Node copy = subj.valueCopy();
            originalCopyMap.put(subj, copy);
            subj = subj.next;
        }

        for (Map.Entry<Node, Node> entry : originalCopyMap.entrySet()) {
            Node copy = entry.getValue();
            copy.next = originalCopyMap.get(entry.getKey().next);
            copy.random = originalCopyMap.get(entry.getKey().random);
        }

        return originalCopyMap.get(input);
    }

    public Node copyInPlace(Node input) {
        if (input == null) return null;
        Node subj = input;
        while (subj != null) {
            Node copy = subj.valueCopy();
            Node next = subj.next;
            subj.next = copy;
            copy.next = next;
            subj = next;
        }

        subj = input;
        while (subj != null) {
            Node copy = subj.next;
            Node next = subj.next.next;
            Node rand = subj.random;
            if (next != null) {
                copy.next = next.next;
            }
            if (rand != null) {
                copy.random = rand.next;
            }
            subj = next;
        }

        return input.next;
    }

    public Node generate(int size) {
        List<Node> nodeList = new ArrayList<>();
        Node head = new Node();
        head.val = "0";
        nodeList.add(head);
        for (int i = 1; i < size; i++) {
            Node curr = new Node();
            curr.val = String.valueOf(i);
            nodeList.add(curr);
            nodeList.get(i - 1).next = curr;
        }

        Random rnd = new Random();
        for (Node curr : nodeList) {
            curr.random = randomOrNull(nodeList);
        }

        return head;
    }

    public <T> T randomOrNull(List<T> list) {
        Random rnd = new Random();
        if (rnd.nextInt(100) < 33) {
            return null;
        } else {
            return list.get(rnd.nextInt(list.size()));
        }
    }

    public void printAllNodes(Node input) {
        Node curr = input;
        StringJoiner sj = new StringJoiner("->");
        while (curr != null) {
            Node rnd = curr.random;
            String randomValue = "";
            if (rnd != null) {
                randomValue = rnd.val;
            }

            sj.add(curr.val + "(" + randomValue + ")");
            curr = curr.next;
        }
        System.out.println(sj);
    }
}
