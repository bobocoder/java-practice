package bobo.structures;

public class HeapAsArray implements Heap {

  private final static int HEAP_MAX_SIZE = 10000;

  private int[] m_data;
  private int m_count;
  private SortOrder m_order;

  public HeapAsArray(SortOrder order) {
    m_data = new int[HEAP_MAX_SIZE];
    m_count = 0;
    m_order = order;
  }

  @Override
  public void insert(int value) {
    m_data[m_count] = value;
    bubbleUp(m_count);
    m_count++;
  }

  @Override
  public int deleteTop() {
    int top = m_data[0];
    m_count--;
    m_data[0] = m_data[m_count];
    bubbleDown(0);
    return top;
  }

  @Override
  public void heapify(int[] values) {
    m_data = values;
    m_count = values.length;

    for (int i = m_count / 2; i >= 0; i--) {
      bubbleDown(i);
    }
  }

  private void bubbleUp(int node) {
    // ascend parents and swap if required (terminate at root)
    while (parent(node) != node && compareSwap(parent(node), node)) {
      node = parent(node);
    }
  }

  private void bubbleDown(int node) {
    // descend to top child and swap if required (terminate at leaf)
    while (left(node) < m_count) {
      int top;
      if (right(node) >= m_count) {
        top = left(node);
      } else {
        top = top(left(node), right(node));
      }

      if (!compareSwap(node, top)) {
        break;
      }
      node = top;
    }
  }


  private boolean compare(int node1, int node2) {
    // compare based on heap sort order
    boolean result;

    switch (m_order) {
      case MIN:
        result = m_data[node1] > m_data[node2];
        break;
      case MAX:
      default:
        result = m_data[node1] < m_data[node2];

    }
    return result;
  }

  private int top(int left, int right) {
    if (compare(left, right)) {
      return right;
    } else {
      return left;
    }
  }

  private boolean compareSwap(int parent, int child) {
    boolean swap = compare(parent, child);

    // swap if comparison succeeds
    if (swap) {
      int tmp = m_data[parent];
      m_data[parent] = m_data[child];
      m_data[child] = tmp;
    }

    return swap;
  }

  private int left(int node) {
    return 2 * node + 1;
  }

  private int right(int node) {
    return 2 * node + 2;
  }

  private int parent(int node) {
    return (node - 1) / 2;
  }

  private int calculateDepth() {
    int depth = 1;
    int depthCount = m_count;
    while (depthCount > 1) {
      depthCount = depthCount / 2;
      depth++;
    }
    return depth;
  }

  @Override
  public void print() {

    final int columnWidth = 4;
    final String format = "[%2d]";

    int depth = calculateDepth();
    int columns = power(2, depth - 1);

    for (int level = 0; level < depth; level++) {

      System.out.print("Level " + level + ": ");


      int firstElement = power(2, level) - 1;
      int numElements = power(2, level);

      int extraSpaces = (columns - numElements) * columnWidth;
      int spacesToPad = extraSpaces / (numElements + 1);

      for (int element = firstElement; element < firstElement + numElements; element++) {
        if (element >= m_count) {
          break;
        }
        for (int i = 0; i < spacesToPad; i++) {
          System.out.print(" ");
        }

        System.out.print("" + String.format(format, m_data[element]) + "");
        if ((element - firstElement) % 4 == 3) {
          System.out.print(".");
        }
      }
      System.out.println();
    }
  }

  /*
   * private void printArray() { for (int i = 0; i < m_count; i++) { System.out.println(m_data[i]);
   * } }
   */
  private int power(int base, int exponent) {
    int result = 1;
    for (int i = 0; i < exponent; i++) {
      result = result * base;
    }
    return result;
  }
}
