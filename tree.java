package Java_algorithm;

public class tree {
    private class Node {
        private Color color;// цвет Node
        private int value;// значение Node
        private Node leftChild;// левый ребенок
        private Node rightChild;// правый ребенок

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ",color=" + color +
                    '}';// переназначение для вывода инфо о Node
        }
    }

    private enum Color {// константы
        RED, BLACK
    }

    public class RedBlackTree {
        private static final tree.Color Color = null;
        private Node root;

        private boolean addNode(Node node, int value) {
            if (node.value == value) {// если значение уже содержиться в этой Node
                return false;
            } else {
                if (node.value > value) {// если значение Node больше переданного
                    if (node.leftChild != null) {// если левый ребенок уже существует
                        boolean result = addNode(node.leftChild, value);// поиск рекурсивно по левому ребенку можно ли
                                                                        // там создать Node
                        node.leftChild = rebalance(node.leftChild);// проверка на балансировку
                        return result;
                    }
                } else {
                    node.leftChild = new Node(); // если можно то создаем левую Node
                    node.leftChild.color = Color.RED;// присваиваем ей красный цвет(все ноды при создании получают
                                                     // красный цвет)
                    node.leftChild.value = value;
                    return true;
                }

                if (node.rightChild != null) {// если правый ребенок существует
                    boolean result = addNode(node.rightChild, value);
                    node.rightChild = rebalance(node.rightChild);
                    return result;
                }

                else {
                    node.rightChild = new Node();
                    node.rightChild.color = Color.RED;
                    node.rightChild.value = value;
                    return true;
                }
            }

        }

        private Node rebalance(Node node) {
            Node result = node;
            boolean needRebalance;
            do {
                needRebalance = false;
                if (result.rightChild != null && result.rightChild.color == Color.RED// если есть правый ребенок и он
                                                                                     // имеет красный цвет
                        && (result.leftChild == null || result.leftChild.color == Color.BLACK)) {// если левый ребенок
                                                                                                 // имеет черный цвет
                    needRebalance = true;// еще раз балансировка
                    result = rightSwap(result);// выполняем правый поворот
                }
                if (result.leftChild != null && result.leftChild.color == Color.RED
                        && result.leftChild.leftChild == null && result.leftChild.leftChild.color == Color.RED) {
                    needRebalance = true;
                    result = leftSwap(result);// если есть две красные ноды у лувых детей подряд левый поворот
                }
                if (result.leftChild != null && result.leftChild.color == Color.RED && result.rightChild != null
                        && result.rightChild.color == tree.Color.RED) {
                    needRebalance = true;
                    colorSwap(result);// если оба ребенка имеют один цвет
                }
            } while (needRebalance);// если ни одно условие не выполнено возвращаем текущий результат балансировка
                                    // не нужна
            return result;

        }

        private Node leftSwap(Node node) {// левосторонний поворот
            Node leftChild = node.leftChild;// левый ребенок в переменную
            Node betweenChild = leftChild.rightChild;// промежуточный ребенок для смены родителя
            leftChild.rightChild = node;// вместо правого ребенка текущего родителя
            node.leftChild = betweenChild;// левым ребенком становиться промежуточный элемент
            leftChild.color = node.color;// левый ребенок принимает цвет родителя
            node.color = Color.RED;// родитель в красный(опустился ниже)
            return leftChild;//
        }

        private Node rightSwap(Node node) {
            Node rightChild = node.rightChild;
            Node betweenChild = rightChild.leftChild;
            rightChild.leftChild = node;
            node.rightChild = betweenChild;
            rightChild.color = node.color;
            node.color = Color.RED;
            return rightChild;
        }

        private void colorSwap(Node node) {// метод смены цвета
            node.rightChild.color = Color.BLACK;// детям черный цвет
            node.leftChild.color = Color.BLACK;//
            node.color = Color.RED;// сама Node в красный
            // для ситуации если у Node два красных ребенка

        }

        public boolean add(int value) {
            if (root != null) {// если root уже существует
                boolean result = addNode(root, value);// создание еще одной Node относительно root
                root = rebalance(root);
                root.color = Color.BLACK;// обязательный для root черный цвет
                return result;
            } else {
                root = new Node();// если нет root
                root.color = Color.BLACK;
                root.value = value;
                return true;
            }
        }
    }

}
