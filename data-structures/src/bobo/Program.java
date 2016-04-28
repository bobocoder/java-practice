package bobo;

import java.util.Random;

import bobo.algorithms.HeapSort;
import bobo.structures.Heap;
import bobo.structures.HeapAsArray;

public class Program {

  public enum Distribution {
    ASC, DESC, RAND
  };

  public static void main( String[] args ) {
    int count = 50;
    int[] data = generateData( count, Distribution.RAND );

    // heap sort experiment
    System.out.println( "Unsorted:" );
    for ( int i = 0; i < count; i++ ) {
      System.out.println( data[i] );
    }

    System.out.println( "Magically sorted:" );
    HeapSort.heapSort2( data, Heap.SortOrder.MAX );
    for ( int i = 0; i < count; i++ ) {
      System.out.println( data[i] );
    }

    // heap testing
    Heap heap = new HeapAsArray( Heap.SortOrder.MAX );

    for ( int i = 0; i < count; i++ ) {
      heap.insert( data[i] );
    }

    heap.print();
    System.out.println();

    for ( int i = 0; i < count; i++ ) {
      heap.deleteTop();
    }

    Heap heap2 = new HeapAsArray( Heap.SortOrder.MAX );
    heap2.heapify( data );
    heap2.print();
  }

  private static int[] generateData( int count, Distribution dist ) {
    int[] result = new int[count];

    Random rand = new Random();

    for ( int i = 0; i < count; i++ ) {
      switch ( dist ) {
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
