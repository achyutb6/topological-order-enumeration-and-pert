# Project Description:

1. Implement enumeration of permutations. Starter code Enumerate.java is available on elearning.
   The class also contains methods for other enumeration problems that are not part of LP4.

2. Implement enumeration of all topological orders of a given directed graph.
   Starter code EnumerateTopological.java is available on elearning.

3. Implement the methods in PERT.java.

	public static PERT pert(Graph g, int[] duration);
	<br>// Run PERT algorithm on graph g. Assume that vertex 1 is s and vertex n is t.
	<br>// You need to add edges from s to all vertices and from all vertices to t.

	boolean pert();  // non-static method called after calling the constructor

  <br> The following methods can be called to query the results, after running one of the above methods:

	public int ec(Vertex u);            // Earliest completion time of u
	public int lc(Vertex u);            // Latest completion time of u
	public int slack(Vertex u);         // Slack of u
	public int criticalPath();          // Length of critical path
	public boolean critical(Vertex u);  // Is vertex u on a critical path?
	public int numCritical();           // Number of critical nodes in graph

