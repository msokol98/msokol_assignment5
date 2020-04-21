package DiGraph_A5.PriorityQueue;

import DiGraph_A5.Helpers.StartPairImpl;

public class MBHImpl implements IHeap {

    private StartPairImpl[] arrayOfTheHeap; 
    private static final int arrayOfTheHeapSize = 10000;
    
    private int size;

    public MBHImpl() {
        this.arrayOfTheHeap = new StartPairImpl[arrayOfTheHeapSize];
        arrayOfTheHeap[0] = new StartPairImpl(null, -100000);
    }
    
	@Override
	public StartPairImpl[] getHeap() {
		return this.arrayOfTheHeap;
	}
	
	@Override
	public void insert(StartPairImpl entry) {
        arrayOfTheHeap[size()+1] = entry;
        bubbleUp(size()+1);
        
        incrementSize();
	}

	@Override
	public void delMin() {
        if (arrayOfTheHeap[1] == null) return;
        	
		if (arrayOfTheHeap[2] == null)
			arrayOfTheHeap[1] = null;

		arrayOfTheHeap[1] = arrayOfTheHeap[size()];
		arrayOfTheHeap[size()] = null;

		if (arrayOfTheHeap[2] != null)
			bubbleDown(1);

		decrementSize();
		
	}

	@Override
	public StartPairImpl getMin() {
		return arrayOfTheHeap[1];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(StartPairImpl[] entries) {
        int theIndex = 0;
        while (theIndex < entries.length && entries[theIndex] != null) {
            arrayOfTheHeap[theIndex+1] = entries[theIndex];
            
            incrementSize();
         
            theIndex++;
        }
        for (int i = size() / 2; i > 0; i--) bubbleDown(i);
	}
	
	
	
    private void bubbleUp(int index) {
        int parent = getParent(index);
        if (arrayOfTheHeap[index].getPriority() < arrayOfTheHeap[parent].getPriority()) {
            StartPairImpl swapHolder = arrayOfTheHeap[parent];
            arrayOfTheHeap[parent] = arrayOfTheHeap[index];
            arrayOfTheHeap[index] = swapHolder;
            
            if (parent != 1) bubbleUp(parent);
        }
    }

    private void bubbleDown(int theIndex) {
    	
        int integerBeingSwapped = getLeft(theIndex);
        
        if(integerBeingSwapped > 9999) {
        	return;
        }
        
        if (arrayOfTheHeap[getRight(theIndex)] != null && arrayOfTheHeap[getRight(theIndex)].getPriority() < arrayOfTheHeap[integerBeingSwapped].getPriority())
            integerBeingSwapped = getRight(theIndex);
        
        if (arrayOfTheHeap[theIndex].getPriority() > arrayOfTheHeap[integerBeingSwapped].getPriority()) {
            StartPairImpl swapHolder = arrayOfTheHeap[integerBeingSwapped];
            arrayOfTheHeap[integerBeingSwapped] = arrayOfTheHeap[theIndex];
            arrayOfTheHeap[theIndex] = swapHolder;
            
           if (arrayOfTheHeap[getLeft(integerBeingSwapped)] != null && getLeft(integerBeingSwapped)<9999) bubbleDown(integerBeingSwapped);
        }
    }
    
    private int getParent(int i) {
        return i/2;
    }

    private int getLeft(int i) {
        return 2*i;
    }

    private int getRight(int i) {
        return 2*i+1;
    }
    
    private void incrementSize() {
    	size++;
    }
    
    private void decrementSize() {
    	size--;
    }
    
}