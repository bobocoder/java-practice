package bobo;

import java.util.Random;

import bobo.structures.Heap;
import bobo.structures.HeapAsArray;


public class Program {

  public enum Distribution {
    ASC, DESC, RAND
  };

  public static void main(String[] args) {
    int count = 50;

    int[] data = generateData(count, Distribution.RAND);

    System.out.println("Unsorted:");
    for (int i = 0; i < count; i++) {
      System.out.println(data[i]);
    }

    // heap sort
    System.out.println("Magically sorted:");
    heapSort2(data, Heap.SortOrder.MAX);
    for (int i = 0; i < count; i++) {
      System.out.println(data[i]);
    }

    Heap heap = new HeapAsArray(Heap.SortOrder.MAX);

    for (int i = 0; i < count; i++) {
      heap.insert(data[i]);
    }

    heap.print();
    System.out.println();

    for (int i = 0; i < count; i++) {
      heap.deleteTop();
    }

    Heap heap2 = new HeapAsArray(Heap.SortOrder.MAX);
    heap2.heapify(data);
    heap2.print();
  }

  private static void heapSort1(int[] data, Heap.SortOrder sortOrder) {
    Heap heap = new HeapAsArray(sortOrder);

    for (int i = 0; i < data.length; i++) {
      heap.insert(data[i]);
    }

    for (int i = 0; i < data.length; i++) {
      data[data.length - 1 - i] = heap.deleteTop();
    }
  }

  private static void heapSort2(int[] data, Heap.SortOrder sortOrder) {
    Heap heap = new HeapAsArray(sortOrder);

    heap.heapify(data);

    for (int i = 0; i < data.length; i++) {
      data[data.length - 1 - i] = heap.deleteTop();
    }
  }


  private static int[] generateData(int count, Distribution dist) {
    int[] result = new int[count];

    Random rand = new Random();

    for (int i = 0; i < count; i++) {
      switch (dist) {
        case RAND:
          result[i] = rand.nextInt();
          break;

        case DESC:
          result[i] = count - i;
          break;

        case ASC:
        default:
          result[i] = i + 1;
      }

    }


    return result;

  }

}
