package bobo;

import java.util.Random;

import bobo.algorithms.HeapSort;
import bobo.structures.Heap;
import bobo.structures.Heap.SortOrder;
import bobo.structures.HeapAsArray;

public class Program {

  public enum Distribution {
    ASC, DESC, RAND
  };

  public static void main( String[] args ) {
    kthmax();
  }

  private static void kthmax() {
    int count = 100;
    int k = 5;

    int[] rawData = generateData( count, Distribution.RAND );
    int[][] data =
        { rawData.clone(), rawData.clone(), rawData.clone(), rawData.clone(), rawData.clone() };
    int[][] results = new int[5][k]; // 5 solutions

    // solution 1
    int sln1Count = count;
    for ( int i = 0; i < k; i++ ) {
      int maxIndex = 0;
      for ( int j = 0; j < sln1Count; j++ ) {
        if ( data[0][j] > data[0][maxIndex] ) {
          maxIndex = j;
        }
      }
      sln1Count--;
      results[0][i] = data[0][maxIndex];
      data[0][maxIndex] = data[0][sln1Count];
    }

    // solution 2
    HeapSort.heapSort2( data[1], SortOrder.MAX );
    for ( int i = 0; i < k; i++ ) {
      results[1][i] = data[1][count - i - 1];
    }

    for ( int h = 0; h < k; h++ ) {
      System.out.println( "Solution " + ( h + 1 ) );
      for ( int i : results[h] ) {
        System.out.print( i + ", " );
      }
      System.out.println();
    }
  }

  private static void heapExperiments( String[] args ) {
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
