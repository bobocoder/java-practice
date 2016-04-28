package bobo.algorithms;

import bobo.structures.Heap;
import bobo.structures.HeapAsArray;

public class HeapSort {

  public static void heapSort1(int[] data, Heap.SortOrder sortOrder) {
    Heap heap = new HeapAsArray(sortOrder);

    for (int i = 0; i < data.length; i++) {
      heap.insert(data[i]);
    }

    for (int i = 0; i < data.length; i++) {
      data[data.length - 1 - i] = heap.deleteTop();
    }
  }

  public static void heapSort2(int[] data, Heap.SortOrder sortOrder) {
    Heap heap = new HeapAsArray(sortOrder);

    heap.heapify(data);

    for (int i = 0; i < data.length; i++) {
      data[data.length - 1 - i] = heap.deleteTop();
    }
  }

}
