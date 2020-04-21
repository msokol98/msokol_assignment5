package DiGraph_A5;

/*
 * 	COMP 410 Spring 2020
 * 	Mitchell Sokol
 */

public interface DiGraph_Interface {
	  boolean addNode(long idNum, String label);
	  boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel);
	  boolean delNode(String label);
	  boolean delEdge(String sLabel, String dLabel);
	  long numNodes();
	  long numEdges();
	  ShortestPathInfo[] shortestPath(String label);
}