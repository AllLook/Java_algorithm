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
            revert(head.next, head);// вызываем метод для начального и след за ним элемент

        }
    }

    private void revert(Node currentNode, Node previousNode) {// текущая и предидущая
        if (currentNode.next == null) {// дошли до последнего
            head = currentNode; // обновляем head последняя становиться началом
        } else {
            revert(currentNode.next, currentNode);// следующий и текущий идет дальше рекурсия
        }
        currentNode.next = previousNode;// обратное значение
        previousNode.next = null;// меняем значение для предидуще

    }

}