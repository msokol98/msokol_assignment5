package DiGraph_A5;

import java.util.HashMap;

public class Node {
	
	
	private long idNum;
	String theLabel;
	
	HashMap<String, Edge> Inside_Edge;
	HashMap<String, Edge> Outside_Edge;
	
	public int in_degree = 0;
	public long dist;
	public int flag = 0;
	
  
  
  public int getIn_degree() {
		return in_degree;
	}

	public void setIn_degree(int in_degree) {
		this.in_degree = in_degree;
	}

	public long getDist() {
		return dist;
	}

	public void setDist(long dist) {
		this.dist = dist;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Node(long givenId, String label) {  
	   Inside_Edge = new HashMap<>();
	   Outside_Edge = new HashMap<>();
	   
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
