package DiGraph_A5;

public interface Heap_Interface {
  void insert(StartPairImpl entry);
  void delMin();
  StartPairImpl getMin();
  int size();
  void build(StartPairImpl [] entries);
  StartPairImpl[] getHeap();
}
