package DiGraph_A5;

public class Edge {
	  String srcL;
	  String destL;
	  String Elabel;
	  
	  long idNum;
	  long theWeight;
	  
	  public Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel)
	  {
		this.idNum=idNum;
	    this.srcL = sLabel;
	    this.destL = dLabel;
	    this.theWeight = weight;
	    this.Elabel = eLabel;
	  }
	  
	  public String getSourceL()
	  {
	    return this.srcL;
	  }
	  
	  public void setSourceL(String srcL)
	  {
	    this.srcL = srcL;
	  }
	  
	  public String getDestL()
	  {
	    return this.destL;
	  }
	  
	  public void setDestL(String destiL)
	  {
	    this.destL = destiL;
	  }
	  
	  public String getELabel()
	  {
	    return this.Elabel;
	  }
	  
	  public void setELabel(String elabel)
	  {
	    this.Elabel = elabel;
	  }
	  
	  public long getEId()
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
