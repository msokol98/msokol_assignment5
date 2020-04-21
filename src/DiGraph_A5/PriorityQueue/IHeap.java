package DiGraph_A5.PriorityQueue;

import DiGraph_A5.Helpers.StartPairImpl;

public interface IHeap {
  int size();
  void insert(StartPairImpl entry);
  void delMin();
  StartPairImpl getMin();
  void build(StartPairImpl [] entries);
  StartPairImpl[] getHeap();
}
