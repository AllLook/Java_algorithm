package Java_algorithm;

public class Heap {
    public static void sort(int[] array) {
        // построение кучи перегруппировка массива
        for (int i = array.length / 2 - 1; i >= 0; i--) {// начиная с середины
            heapify(array, array.length, i);// в результате самый большой элемент окажется в корне
        }
        // один за другим извлекаем элементы из кучи
        for (int i = array.length - 1; i >= 0; i--) {// массив с конца до начала
            //
            int temp = array[0];// перестановка корня(наибольшего) на последний индекс
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);// ззапускаем просеивание после каждой такой перестановки

        }

    }

    private static void heapify(int[] array, int heapSize, int rootIndex) {
        int largest = rootIndex;// инициализируется наибольший элемент как корень
        int leftChild = 2 * rootIndex + 1;// левый ребенок инициализируется по формуле
        int rightChild = 2 * rootIndex + 2;// правый ребенок инициализируется по формуле

        if (leftChild < heapSize && array[leftChild] > array[largest]) {// если левый ребенок больше самого большого то
                                                                        // левый становиться самым большим
            largest = leftChild;
        }
        if (rightChild < heapSize && array[rightChild] > array[largest]) {// если правый ребенок больше самого большого
                                                                          // то правый становиться самым большим
            largest = rightChild;
        }
        if (largest != rootIndex) {// если после этого самый больший элемент не равен корню
            int temp = array[rootIndex];// корень становиться временным
            array[rootIndex] = array[largest];// корень становиться наибольшим
            array[largest] = temp;// больший становиться временным,становиться на свободное место,снова сравнение
                                  // и обмен,для этого запускаем рекурсию для наибольшего элемента
            heapify(array, heapSize, largest);

        }

    }
}