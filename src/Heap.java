import java.util.Arrays;
import java.util.Random;

public class Heap {

    private int[] Heap;
    private int actualHeapSize;
    private int maxHeapSize;

    private Heap(int maxHeapSize) {
        Heap = new int[maxHeapSize + 1];
        this.maxHeapSize = maxHeapSize;
        this.actualHeapSize = 0;
        Heap[0] = Integer.MIN_VALUE;
    }

    private static int LeftChildArrayIndex(int arrayIndex) {
        return (arrayIndex * 2);
    }

    private static int RightChildArrayIndex(int arrayIndex) {
        return arrayIndex * 2 + 1;
    }

    private static int parentArrayIndex(int arrayIndex) {
        return arrayIndex / 2;
    }

    private boolean isLeafNode(int arrayIndex) {
        return arrayIndex >= (actualHeapSize / 2) && arrayIndex <= actualHeapSize;
    }

    private void swap(int firstToSwap, int secondToSwap) {
        int buff;
        buff = Heap[firstToSwap];
        Heap[firstToSwap] = Heap[secondToSwap];
        Heap[secondToSwap] = buff;
    }

    private void heapify(int arrayIndex) {

        if (!isLeafNode(arrayIndex)) {
            if (Heap[arrayIndex] > Heap[LeftChildArrayIndex(arrayIndex)]
                    || Heap[arrayIndex] > Heap[RightChildArrayIndex(arrayIndex)]) {

                if (Heap[LeftChildArrayIndex(arrayIndex)] < Heap[RightChildArrayIndex(arrayIndex)]) {
                    swap(arrayIndex, LeftChildArrayIndex(arrayIndex));
                    heapify(LeftChildArrayIndex(arrayIndex));
                } else {
                    swap(arrayIndex, RightChildArrayIndex(arrayIndex));
                    heapify(RightChildArrayIndex(arrayIndex));
                }
            }
        }
    }

    private void insert(int element) {
        if (actualHeapSize >= maxHeapSize) {
            return;
        }
        Heap[++actualHeapSize] = element;
        int currentSize = actualHeapSize;

        while (Heap[currentSize] < Heap[parentArrayIndex(currentSize)]) {

            swap(currentSize, parentArrayIndex(currentSize));
            currentSize = parentArrayIndex(currentSize);

        }
    }

    private void minHeap() {
        for (int arrayIndex = (actualHeapSize / 2); arrayIndex >= 1; arrayIndex--) {
            heapify(arrayIndex);
        }
    }

    public static void main(String[] arg) {
        int n = 10;
        Heap minHeap = new Heap(n);

        for (int i = 1; i <= n; i++) {
            minHeap.insert(new Random().nextInt(100) + 1);
        }

        minHeap.minHeap();
        System.out.println("The Min Heap is ");
        System.out.println(Arrays.toString(minHeap.Heap));
    }
}
