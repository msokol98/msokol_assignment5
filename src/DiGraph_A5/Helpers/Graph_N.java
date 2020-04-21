package DiGraph_A5.Helpers;

import java.util.HashMap;

public class Graph_N {
	
	
	private long idNum;
	public String theLabel;
	
	public HashMap<String, Graph_E> edge_on_inside;
	public HashMap<String, Graph_E> edge_on_outside;
	
	public int numNeighbors = 0;
	public long dist;
	public boolean isMarked;
	
  
  
  public int getNumNeighbors() {
		return numNeighbors;
	}

	public void setNumNeighbors(int numNeighbors) {
		this.numNeighbors = numNeighbors;
	}

	public long getDist() {
		return dist;
	}

	public void setDist(long dist) {
		this.dist = dist;
	}

	public boolean getIsMarked() {
		return isMarked;
	}

	public void setFlag(boolean isMarked) {
		this.isMarked = isMarked;
	}

	public Graph_N(long givenId, String label) {
	   isMarked = false;
		
	   edge_on_inside = new HashMap<>();
	   edge_on_outside = new HashMap<>();
	   
	   this.idNum = givenId;
	   this.theLabel = label;
	}
  
  public long getId() {
	  return idNum;
  }
  
  public String getTheLabel() {
    return theLabel;
  }
  
  
  public String toString() {
	  return "Node: id: " + this.idNum + "  lable:"+ theLabel;
  }
}
