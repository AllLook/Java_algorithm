package Java_algorithm;

import org.w3c.dom.Node;

public class reversal {
    Node head;

    public class Node {
        int value;
        Node next;
        Node previous;
    }

    public void revert() {

        if (head != null && head.next != null) {// если список не пустой и след значение
            revert(head.next, head);// вызываем метод для начального и след за ним элемента

        }
    }

    private void revert(Node currentNode, Node previousNode) {
        if (currentNode.next == null) {
            head = currentNode; // текущая становиться началом
        } else {
            revert(currentNode.next, currentNode);// следующий и его предидущий(т.е текущий)
        }
        currentNode.next = previousNode;// текущий принимает значение предидущего
        previousNode.next = null;// меняем значение для следующего за предидущем

    }

}