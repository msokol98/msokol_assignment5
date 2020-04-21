package DiGraph_A5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class DiGraph implements DiGraph_Interface {

	HashMap<String, Node> NodeMap = new HashMap<String, Node>();

	HashSet<Long> NodeId = new HashSet<Long>();

	HashSet<Long> EdgeId = new HashSet<Long>();

	public DiGraph() {
	}

	public boolean addNode(long givenId, String theLabel) {
		if (theLabel == null || givenId < 0)
			return false;

		else if (!NodeMap.containsKey(theLabel) && !NodeId.contains(givenId)) {

			NodeMap.put(theLabel, new Node(givenId, theLabel));
			NodeId.add(givenId);

			return true;
		}

		return false;

	}

	public boolean addEdge(long givenId, String stheLabel, String dtheLabel, long weight, String etheLabel) {
		if (givenId < 0 || EdgeId.contains(givenId))
			return false;
		else if (!NodeMap.containsKey(stheLabel) || !NodeMap.containsKey(dtheLabel))
			return false;

		Node sourceNode = NodeMap.get(stheLabel);
		Node destiNode = NodeMap.get(dtheLabel);

		if (sourceNode.Outside_Edge.containsKey(dtheLabel) && destiNode.Inside_Edge.containsKey(stheLabel))
			return false;

		Edge edgeToAdd = new Edge(givenId, stheLabel, dtheLabel, weight, etheLabel);
		EdgeId.add(givenId);
		sourceNode.Outside_Edge.put(dtheLabel, edgeToAdd);
		destiNode.Inside_Edge.put(stheLabel, edgeToAdd);
		destiNode.in_degree = destiNode.in_degree + 1;

		return true;

	}

	public boolean delNode(String theLabel) {
		if (!NodeMap.containsKey(theLabel))
			return false;

		Node nodeToRemove = NodeMap.get(theLabel);

		Iterator<String> InEdge_sL = nodeToRemove.Inside_Edge.keySet().iterator();
		Iterator<String> OutEdge_dL = nodeToRemove.Outside_Edge.keySet().iterator();

		while (InEdge_sL.hasNext()) {
			delEdge(InEdge_sL.next(), nodeToRemove.theLabel);
		}

		while (OutEdge_dL.hasNext()) {
			delEdge(nodeToRemove.theLabel, OutEdge_dL.next());
		}

		NodeMap.remove(theLabel, nodeToRemove);
		NodeId.remove(nodeToRemove.getId());

		return true;
	}

	public boolean delEdge(String stheLabel, String dtheLabel) {
		if (!NodeMap.containsKey(stheLabel) || !NodeMap.containsKey(dtheLabel))
			return false;

		Node srcNode = NodeMap.get(stheLabel);
		Node destiNode = NodeMap.get(dtheLabel);

		if (srcNode.Outside_Edge.containsKey(dtheLabel)) {
			Edge edgeToRemove = NodeMap.get(stheLabel).Outside_Edge.get(dtheLabel);
			srcNode.Outside_Edge.remove(dtheLabel, edgeToRemove);
			destiNode.Inside_Edge.remove(stheLabel, edgeToRemove);
			destiNode.in_degree -= 1;
			EdgeId.remove(edgeToRemove.idNum);
			return true;
		}

		return false;
	}

	public long numNodes() {
		return NodeId.size();
	}

	public long numEdges() {
		return EdgeId.size();
	}

	public String[] topoSort() {
		Queue<Node> Zero_In_Node = new LinkedList<Node>();
		if (NodeMap.isEmpty())
			return null;

		ArrayList<String> topoString = new ArrayList<String>();
		for (Node node : NodeMap.values()) {
			if (node.in_degree == 0)
				Zero_In_Node.add(node);
		}

		while (Zero_In_Node.isEmpty() == false) {
			Node deqNode = Zero_In_Node.poll();
			topoString.add(deqNode.theLabel);
			Iterator<Edge> OutEdge = deqNode.Outside_Edge.values().iterator();
			while (OutEdge.hasNext()) {
				Node destiNode = NodeMap.get(OutEdge.next().getDestL());
				destiNode.in_degree -= 1;

				if (destiNode.in_degree == 0) {
					Zero_In_Node.add(destiNode);
				}

			}
		}

		if (NodeMap.size() == topoString.size())
			return topoString.toArray(new String[topoString.size()]);
		else
			return null;
	}

	public ShortestPathInfo[] shortestPath(String theLabel) {
		int theSize = NodeId.size();
		ShortestPathInfo[] theShortestPath = new ShortestPathInfo[theSize];
		MinBinHeap PQ = new MinBinHeap();
		StartPairImpl sNode = new StartPairImpl(theLabel, 0);
		PQ.insert(sNode);
		theShortestPath[0] = new ShortestPathInfo(theLabel, 0);
		int i = 0;
		while (PQ.size() != 0) {
			Node currNode = NodeMap.get(PQ.getMin().getValue());
			long currDist = PQ.getMin().getPriority();
			PQ.delMin();
			if (currNode.flag == 1)
				continue;
			else {
				theShortestPath[i] = new ShortestPathInfo(currNode.theLabel, currDist);
				currNode.flag = 1;
			}
			Iterator<Edge> adjNode = currNode.Outside_Edge.values().iterator();
			while (adjNode.hasNext()) {
				Node aNode = NodeMap.get(adjNode.next().getDestL());
				if (aNode.flag == 0) {
					long newDist = currDist + currNode.Outside_Edge.get(aNode.theLabel).getWeight();
					if (aNode.dist == 0 || aNode.dist > newDist) {
						aNode.dist = newDist;
						StartPairImpl a = new StartPairImpl(aNode.theLabel, aNode.dist);
						PQ.insert(a);
					}

				}
			}
			i++;
		}

		if (i < theSize) {
			for (Node node : NodeMap.values()) {
				if (node.flag == 0) {
					node.dist = -1;
					theShortestPath[i] = new ShortestPathInfo(node.theLabel, node.dist);
					i++;
				}

			}
		}

		return theShortestPath;
	}
}