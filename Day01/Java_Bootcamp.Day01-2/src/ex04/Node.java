package ex04;

public class Node {
    Transaction data;
    Node next;

    Node(Transaction data) {
        this.data = data;
        this.next = null;
    }
}
