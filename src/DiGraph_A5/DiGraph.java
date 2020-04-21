package DiGraph_A5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import DiGraph_A5.Helpers.Edge;
import DiGraph_A5.Helpers.Node;
import DiGraph_A5.Helpers.StartPairImpl;
import DiGraph_A5.PriorityQueue.MBHImpl;

public class DiGraph implements DiGraph_Interface {

	HashMap<String, Node> n_map = new HashMap<String, Node>();

	HashSet<Long> nodeIdentifiers = new HashSet<Long>();

	HashSet<Long> edgeIdentifiers = new HashSet<Long>();
	
	public long numNodes() {
		return nodeIdentifiers.size();
	}

	public long numEdges() {
		return edgeIdentifiers.size();
	}
	
	public ShortestPathInfo[] shortestPath(String theLabel) {
		int magnitude = nodeIdentifiers.size();
		ShortestPathInfo[] theShortestPath = new ShortestPathInfo[magnitude];
		MBHImpl PQ = new MBHImpl();
		StartPairImpl sNode = new StartPairImpl(theLabel, 0);
		PQ.insert(sNode);
		theShortestPath[0] = new ShortestPathInfo(theLabel, 0);
		
		int y = 0;
		
		while (PQ.size() > 0) {
			Node presentN = n_map.get(PQ.getMin().getValue());
			
			long currentDistance = PQ.getMin().getPriority();
			
			PQ.delMin();
			
			if (presentN.isMarked)
				continue;
			else {
				theShortestPath[y] = new ShortestPathInfo(presentN.theLabel, currentDistance);
				presentN.isMarked = true;
			}
			
			Iterator<Edge> nodeOnBorder = presentN.edge_on_outside.values().iterator();
			
			while (nodeOnBorder.hasNext()) {
				Node dummyNode = n_map.get(nodeOnBorder.next().getDestLabel());
				if (!dummyNode.isMarked) {
					long updatedDistance = updateDistance(presentN.edge_on_outside.get(dummyNode.theLabel).getWeight(), currentDistance);
					if (dummyNode.dist == 0 || dummyNode.dist > updatedDistance) {
						dummyNode.dist = updatedDistance;
						
						StartPairImpl newSP = new StartPairImpl(dummyNode.theLabel, dummyNode.dist);
						PQ.insert(newSP);
					}

				}
			}
			
			y = y + 1;
		}

		if (y < magnitude) {
			for (Node n : n_map.values()) {
				if (!n.isMarked) {
					n.dist = -1;
					
					theShortestPath[y] = new ShortestPathInfo(n.theLabel, n.dist);
					
					y = y + 1;
				}

			}
		}

		return theShortestPath;
	}

	public DiGraph() {
	}


	public boolean addEdge(long givenId, String stheLabel, String dtheLabel, long weight, String etheLabel) {
		if (givenId < 0 || edgeIdentifiers.contains(givenId))
			return false;
		else if (!n_map.containsKey(stheLabel) || !n_map.containsKey(dtheLabel))
			return false;

		Node sourceNode = n_map.get(stheLabel);
		Node nodeOfDestination = n_map.get(dtheLabel);

		if (sourceNode.edge_on_outside.containsKey(dtheLabel) && nodeOfDestination.edge_on_inside.containsKey(stheLabel))
			return false;

		Edge edgeToAdd = new Edge(givenId, stheLabel, dtheLabel, weight, etheLabel);
		edgeIdentifiers.add(givenId);
		
		
		sourceNode.edge_on_outside.put(dtheLabel, edgeToAdd);
		
		
		
		nodeOfDestination.edge_on_inside.put(stheLabel, edgeToAdd);
		
		nodeOfDestination.numNeighbors = nodeOfDestination.numNeighbors + 1;
		

		return true;

	}
	
	public boolean addNode(long givenId, String theLabel) {
		if (theLabel == null || givenId < 0)
			return false;

		else if (!n_map.containsKey(theLabel) && !nodeIdentifiers.contains(givenId)) {

			n_map.put(theLabel, new Node(givenId, theLabel));
			nodeIdentifiers.add(givenId);

			return true;
		}

		return false;

	}

	public boolean delNode(String theLabel) {
		if (!n_map.containsKey(theLabel))
			return false;

		Node nodeToRemove = n_map.get(theLabel);

		Iterator<String> InEdge_sL = nodeToRemove.edge_on_inside.keySet().iterator();
		Iterator<String> OutEdge_dL = nodeToRemove.edge_on_outside.keySet().iterator();

		while (InEdge_sL.hasNext()) {
			delEdge(InEdge_sL.next(), nodeToRemove.theLabel);
		}

		while (OutEdge_dL.hasNext()) {
			delEdge(nodeToRemove.theLabel, OutEdge_dL.next());
		}

		n_map.remove(theLabel, nodeToRemove);
		nodeIdentifiers.remove(nodeToRemove.getId());

		return true;
	}

	public boolean delEdge(String stheLabel, String dtheLabel) {
		if (!n_map.containsKey(stheLabel) || !n_map.containsKey(dtheLabel))
			return false;

		Node srcNode = n_map.get(stheLabel);
		Node nodeOfDestination = n_map.get(dtheLabel);

		if (srcNode.edge_on_outside.containsKey(dtheLabel)) {
			Edge edgeToRemove = n_map.get(stheLabel).edge_on_outside.get(dtheLabel);
			srcNode.edge_on_outside.remove(dtheLabel, edgeToRemove);
			nodeOfDestination.edge_on_inside.remove(stheLabel, edgeToRemove);
			nodeOfDestination.numNeighbors = nodeOfDestination.numNeighbors - 1;
			edgeIdentifiers.remove(edgeToRemove.getId());
			
			return true;
		}

		return false;
	}

	

	public String[] topoSort() {
		Queue<Node> zeroN = new LinkedList<Node>();
		
		if (n_map.isEmpty())
			return null;

		List<String> topoString = new LinkedList<String>();
		for (Node node : n_map.values()) {
			if (node.numNeighbors <  1)
				zeroN.add(node);
		}

		while (!zeroN.isEmpty()) {
			Node deqNode = zeroN.poll();
			topoString.add(deqNode.theLabel);
			Iterator<Edge> oe = deqNode.edge_on_outside.values().iterator();
			
			while (oe.hasNext()) {
				Node nodeOfDestination = n_map.get(oe.next().getDestLabel());
				nodeOfDestination.numNeighbors = nodeOfDestination.numNeighbors - 1;

				if (nodeOfDestination.numNeighbors < 1) {
					zeroN.add(nodeOfDestination);
				}

			}
		}

		if (n_map.size() == topoString.size())
			return topoString.toArray(new String[topoString.size()]);
		else
			return null;
	}
	
	private long updateDistance(long d1, long d2) {
		return d1 + d2;
	}
	
}