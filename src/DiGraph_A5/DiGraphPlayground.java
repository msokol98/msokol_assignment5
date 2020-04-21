package DiGraph_A5;

public class DiGraphPlayground {

  public static void main (String[] args) {
    executeTheTest();
  }
  
    public static void executeTheTest(){
    }
    public static void printTOPO(String[] toPrint){
      System.out.print("TOPO Sort: ");
      for (String string : toPrint) {
      System.out.print(string+" ");
    }
      System.out.println();
    }
    
    public static void printSP(ShortestPathInfo[] ShortestPath){
        System.out.println("ShortestPath Info:");
        for (ShortestPathInfo sp_i : ShortestPath) {
        System.out.println(sp_i + " ");
      }
        System.out.println();
      }

}