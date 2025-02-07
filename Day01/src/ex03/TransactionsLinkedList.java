package ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Node head;
    int size;

    TransactionsLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addLast(Transaction data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    @Override
    public void addFirst(Transaction data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public void remove(UUID idTransaction) {
        if (head == null) return;
        if (head.data.getId().equals(idTransaction)) {
            head = head.next;
            size--;
            return;
        }
        Node current = head;
        while (current.next != null) {
            if (current.next.data.getId().equals(idTransaction)) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
        throw new TransactionNotFoundException("Идентификатор не найден!");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node current = head;
        while (current != null) {
            result.append(current.data); // Добавляем данные узла
            if (current.next != null) {
                result.append("\n -> "); // Добавляем разделитель между узлами
            }
            current = current.next; // Переходим к следующему узлу
        }
        return result.toString(); // Возвращаем строковое представление списка
    }
}
