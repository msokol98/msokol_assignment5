package DiGraph_A5.Helpers;

public class Edge {
	
	  String srcLabel;
	  String destL;
	  String edgeLabel;
	  
	  long idNum;
	  long theWeight;
	  
	  public Edge(long idNum, String srcLabel, String dLabel, long weight, String edgeLabel)
	  {
		this.idNum=idNum;
	    this.srcLabel = srcLabel;
	    this.destL = dLabel;
	    this.theWeight = weight;
	    this.edgeLabel = edgeLabel;
	  }
	  
	  public String getSourceLabel()
	  {
	    return this.srcLabel;
	  }
	  
	  public void setSourceLabel(String srcLabel)
	  {
	    this.srcLabel = srcLabel;
	  }
	  
	  public String getDestLabel()
	  {
	    return this.destL;
	  }
	  
	  public void setDestLabel(String destiLabel)
	  {
	    this.destL = destiLabel;
	  }
	  
	  public String getEdgeLabel()
	  {
	    return this.edgeLabel;
	  }
	  
	  public void setEdgeLabel(String edgeLabel)
	  {
	    this.edgeLabel = edgeLabel;
	  }
	  
	  public long getId()
	  {
	    return this.idNum;
	  }
	  
	  public void setEId(long idNum)
	  {
	    this.idNum = idNum;
	  }
	  
	  public long getWeight()
	  {
	    return this.theWeight;
	  }
	  
	  public void setWeight(long weight)
	  {
	    this.theWeight = weight;
	  }
	
}
