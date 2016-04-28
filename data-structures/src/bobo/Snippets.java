package bobo;

public class Snippets {

  /*
  public List removeDuplicates(List input, int n) {
    Object item;
    HashTable duplicateCounter;
    List result;
    while (item = input.getNext()) {
      if (!duplicateCounter.containsKey(item)) {
        duplicateCounter.addKeyValue(item, 1);
      } else {
        int counter = duplicateCounter.getValue(item);
        if (counter < n) {
          duplicateCounter.setValue(item, ++counter);
        }
      }
    }

    while (item = input.getNext()) {
      if (duplicateCounter.containsKey(item)) {
        int counter = duplicateCounter.getValue(item);
        if (counter == 1) {
          result.add(item);
        }
        duplicateCounter.setValue(item, --counter);
      }
    }
    return result;
  }
  */

}
