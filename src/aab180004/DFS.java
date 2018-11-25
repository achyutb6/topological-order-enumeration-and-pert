/** Starter code for SP8
 *  @author Achyut Arun Bhandiwad - AAB180004
 */

package aab180004;

import rbk.Graph.Vertex;
import rbk.Graph;
import rbk.Graph.Edge;
import rbk.Graph.GraphAlgorithm;
import rbk.Graph.Factory;
import rbk.Graph.Timer;

import java.io.File;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * The class represents the implementation of Depth first Search 
 * @author Mythri Thippareddy, Achyut Bhandiwad
 *
 */
/**
 * @author Mythri Thippareddy
 *
 */
public class DFS extends GraphAlgorithm<DFS.DFSVertex> {

	private LinkedList<Vertex> finishList; // Output of dfs() will be stored in finishList
	public static boolean isCyclic; // isCyclic check
	public int numberOfConnectedComponents; // The number of connected components of the graph as 0;
	private int topNum; // 	Used for dfs()

	/**
	 * @author Mythri Thippareddy, Achyut Bhandiwad
	 *
	 */
	public static class DFSVertex implements Factory {
		int cno; // component number
		public boolean seen; // check if the vertex is seen
		public Vertex parent; // storing the parent of the vertex
		int top; // storing the top number to check for back edges

		/**
		 * @param u
		 * converting vertex u to DFSVertex
		 */
		public DFSVertex(Vertex u) {
			this.seen = false; 
			this.parent = null;
			this.top = 0;
			this.cno = 0;
		}

		/* 
		 * @see rbk.Graph.Factory#make(rbk.Graph.Vertex)
		 */
		public DFSVertex make(Vertex u) {
			return new DFSVertex(u);
		}
	}

	/**
	 * @param g 
	 * Initialing g with numberOfConnectedComponents and isCyclic
	 */
	public DFS(Graph g) {
		super(g, new DFSVertex(null));
		isCyclic = false;
		numberOfConnectedComponents=0;
	}

	/**
	 * @param g
	 * @return DFS object with the objects ordered
	 */
	public static DFS depthFirstSearch(Graph g) {
		DFS d = new DFS(g);
		d.dfs();
		return d;
	}

	/**
	 * Dfs method to run depth first search
	 */
	public void dfs() {
		topNum = g.size();
		for (Vertex u : g) {
			get(u).seen = false;
			get(u).parent = null;
			get(u).top = 0;
		}
		finishList = new LinkedList<Vertex>();
		for (Vertex u : g) {
			if (!get(u).seen) {
				get(u).cno = ++numberOfConnectedComponents;
				DFS_visit(u);
			}
		}
	}

	/**
	 * @param u
	 * Visiting each node adjacent to Vertex u
	 */
	private void DFS_visit(Vertex u) {
		get(u).seen = true;
		for (Edge e : g.incident(u)) {
			Vertex v = e.otherEnd(u);
			if (!get(v).seen) {
				get(v).parent = u;
				get(v).cno = get(u).cno;
				DFS_visit(v);
			} else {
				if (get(v).top == 0) {
					isCyclic = true;
				}
			}
		}
		get(u).top = topNum--;
		finishList.addFirst(u);
	}

	
	/**
	 * @return list of vertices in topological order
	 */
	public List<Vertex> topologicalOrder1() {
		dfs();
		if(isCyclic) {
			return null;
		}
		return finishList;
	}
	
	/**
	 * @return the number of connected components
	 */
	public int connectedComponents() {
		dfs();
		return numberOfConnectedComponents;
	}

	/**
	 * @param u
	 * @return the component number of the vertex u
	 */
	public int cno(Vertex u) {
		return get(u).cno;
	}

	/**
	 * @param g
	 * @return Returns null if g is not a DAG else return the topological oder of a DAG using DFS. 
	 */
	public static List<Vertex> topologicalOrder1(Graph g) {
		DFS d = new DFS(g);
		return d.topologicalOrder1();
	}

	public static void main(String[] args) throws Exception {
		String string = "7 8   1 2 2   1 3 3   2 4 5   3 4 4   4 5 1   5 1 7   6 7 1   7 6 1 0";
		Scanner in;
		// If there is a command line argument, use it as file from which
		// input is read, otherwise use input from string.
		in = args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(string);

		// Read graph from input
		Graph g = Graph.readGraph(in);
		g.printGraph(false);

		DFS d = new DFS(g);
		int numcc = d.connectedComponents();
		System.out.println("Number of components: " + numcc + "\nu\tcno");
		for (Vertex u : g) {
			System.out.println(u + "\t" + d.cno(u));
		}
		System.out.println("");
		
		System.out.println("Output of Dfs:\nNode\ttop\tParent\n----------------------");
		for (Vertex u : g) {
			System.out.println(u + "\t" + d.get(u).top + "\t" + d.get(u).parent);
		}
		System.out.println("");
		System.out.println("Output of topological order\n-----------------------------");
		List<Vertex> toplogicalList = d.topologicalOrder1();
		if(toplogicalList==null) {
			System.out.println("Graph is cyclic. No topological order exists");
		}else {
			for(Vertex u: toplogicalList) {
				System.out.print(u+"\t");
			}
			System.out.println("");
		}
	}
}
