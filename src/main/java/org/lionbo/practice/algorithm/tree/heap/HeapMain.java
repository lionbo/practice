package org.lionbo.practice.algorithm.tree.heap;

public class HeapMain {

    public static void main(String[] args) {

        Long[] numbers = new Long[] { 3L, 20L, 9L, 10L, 5L, 7L, 100L, 101L, 88L, 99L };
        int heapsize = 5;
        Long[] result = HeapMain.buildTree(numbers, heapsize);
        for (int i = 0; i < heapsize; i++) {
            System.out.println(result[i]);
        }
    }

    private static Long[] buildTree(Long[] numbers, int heapSize) {
        int layers = HeapMain.findLayers(heapSize);
        int heapExtendSize = (int) Math.pow(2, layers);
        Long[] heap = new Long[heapExtendSize];
        for (int i = 0; i < heapSize && i < numbers.length; i++) {
            heap[i] = numbers[i];
        }
        buildHeap(heap, heapSize);
        if (numbers.length > heapSize) {
            for (int i = heapSize; i < numbers.length; i++) {
                if (heap[0] > numbers[i]) {
                    heap[0] = numbers[i];
                    adjustHeap(heap, 0, heapSize);
                }
            }
        }
        heapSort(heap, heapSize);
        return heap;
    }

    private static void heapSort(Long[] heap, int cursor) {
        int currentCursor = cursor;
        for (int i = 0; i < currentCursor; i++) {
            swap(heap, 0, cursor - 1);
            cursor--;
            adjustHeap(heap, 0, cursor);
        }
    }

    private static void buildHeap(Long[] heap, int heapSize) {
        int cursor = heapSize;
        for (int i = cursor / 2 - 1; i >= 0; i--) {
            adjustHeap(heap, i, cursor);
        }
    }

    private static void adjustHeap(Long[] heap, int index, int cursor) {
        int left = (index + 1) * 2 - 1;
        int right = (index + 1) * 2;
        int max = index;

        if (left < cursor && heap[left] > heap[index]) {
            max = left;
        }
        if (right < cursor && heap[right] > heap[max]) {
            max = right;
        }

        if (max == index || max >= cursor)
            return;
        swap(heap, index, max);
        adjustHeap(heap, max, cursor);
    }

    private static void swap(Long[] heap, int first, int second) {
        Long tmp = heap[first];
        heap[first] = heap[second];
        heap[second] = tmp;
    }

    private static int findLayers(int heapSize) {
        if (heapSize <= 0) {
            return -1;
        }
        int count = 0;

        while (heapSize != 0) {
            heapSize = heapSize >> 1;
            count++;
        }
        return count;
    }

}
