package bobo.structures;

public interface Heap {

  public enum SortOrder {
    MIN, MAX
  };

  public void insert( int value );

  public int deleteTop();

  public void heapify( int[] values );

  public void print();

}
