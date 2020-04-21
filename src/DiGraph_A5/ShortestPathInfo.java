package DiGraph_A5;

public class ShortestPathInfo {

  private long totalWeight;
  private String destination;
  
  public ShortestPathInfo(String givenDest, long totalWeight){
    destination = givenDest;
    this.totalWeight=totalWeight;
  }
  
  public String toString(){
    return "dest: " + destination + "\ttotalWeight: "+ totalWeight;
  }
  
  public String getDest() {
    return destination;
  }

  public long getTotalWeight() {
    return totalWeight;
  }

}