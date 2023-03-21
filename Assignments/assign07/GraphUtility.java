package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Contains several methods for solving problems on generic, directed, unweighted, sparse graphs.
 * 
 * @author Erin Parker & Sebastian Barney
 * @version March 3, 2022
 */
public class GraphUtility {

	/**
	 * This method must use the recursive depth-first search algorithm presented in lecture to determine whether there is a path from the vertex with srcData to the vertex with dstData in the graph.
	 * Throws an IllegalArgumentException if there does not exist a vertex in the graph with srcData, and likewise for dstData.
	 * @param sources The graph getting searched.
	 * @param destinations - target
	 * @param srcData - data in the vertex
	 * @param dstData - data in the vertex
	 * @return if there is a path.
	 * @param <Type> generic
	 * @throws IllegalArgumentException - if there does not exist a vertex in the graph with srcData, and likewise for dstData
	 */
	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException  {
		//checks if the target is even in the graph
		if (!(sources.contains(srcData) || destinations.contains(srcData)) || !(sources.contains(dstData) || destinations.contains(dstData))) {
			throw new IllegalArgumentException("Its not that deep bro... its not that deep.");
		}

		//makes an empty list and performs a DFS on it.
		ArrayList<Type> visited = new ArrayList<>();
		return dfs(srcData, dstData, sources, destinations, visited);
	}

	/**
	 * Depth First Search method.
	 * @param current vertex we're on.
	 * @param target - vertex we want.
	 * @param sources - the graph
	 * @param destinations - where to go next.
	 * @param visited - the nodes that have been visited so far.
	 * @return if a path exists or not in a graph.
	 * @param <Type> generic
	 */
	private static <Type> boolean dfs(Type current, Type target, List<Type> sources, List<Type> destinations, ArrayList<Type> visited) {
			if (current.equals(target)) {
				return true;
			}

			visited.add(current);

			for (int i = 0; i < sources.size(); i++) {
				if (sources.get(i).equals(current) && !visited.contains(destinations.get(i))) {
					if (dfs(destinations.get(i), target, sources, destinations, visited)) {
						return true;
					}
				}
			}

			return false;
	}



	/**
	 * This method must use the breadth-first search algorithm presented in lecture to find a shortest path from the vertex with srcData to the vertex with dstData in the graph.
	 * Throws an IllegalArgumentException if there does not exist a vertex in the graph with srcData, and likewise for dstData.  Also, throws an IllegalArgumentException if there does not exist a path between the two vertices.
	 * @param sources the graph
	 * @param destinations where the source be goin
	 * @param srcData data in the vertex
	 * @param dstData data in the vertex
	 * @return Shortest path between the source vertex and the destination vertex.
	 * @param <Type> generic
	 * @throws IllegalArgumentException -if there does not exist a vertex in the graph with srcData, and likewise for dstData or if there does not exist a path between the two vertices.
	 */
	public static <Type> List<Type> shortestPath(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {
		// checks if there's a path and that it's actually in the graph
		if (!(sources.contains(srcData) || destinations.contains(srcData)) || !(sources.contains(dstData) || destinations.contains(dstData))) {
			throw new IllegalArgumentException("There does not exist a vertex in the graph with srcData, and likewise for dstData.");
		}

		// makes a list by performing a breadth first search, so basically just finds the shortest path
		List<List<Type>> parentLists = Collections.singletonList(bfs(sources, destinations, srcData, dstData));

		// if there isn't a shortest path then we have problems
		if (parentLists.isEmpty()) {
			throw new IllegalArgumentException("There does not exist a path between the two vertices.");
		}

		// Goes back through the path.
		List<Type> shortestPath = null;
		int shortestPathLength = Integer.MAX_VALUE;

		for (List<Type> parentList : parentLists) {
			if (parentList.get(parentList.size() - 1).equals(dstData)) {
				if (parentList.size() < shortestPathLength) {
					shortestPath = parentList;
					shortestPathLength = parentList.size();
				}
			}
		}

		if (shortestPath == null) {
			throw new IllegalArgumentException("There does not exist a path between the two vertices.");
		}

		// reverses the list that the path is in, basically going from start to finish rather than finish to start.
		Collections.reverse(shortestPath);

		return shortestPath;
	}

	/**
	 * A Breadth First Search Method for graphs.
	 * @param sources the graph to be searched.
	 * @param destinations - where the source be goin
	 * @param srcData data stored in the vertex
	 * @param dstData data stored in the vertex
	 * @return searched graph.
	 * @param <Type>
	 */
	private static <Type> List<Type> bfs(List<Type> sources, List<Type> destinations, Type srcData, Type dstData) {
		//makes a queue and a list of nodes to be visited and nodes that have been visited.
		Queue<List<Type>> queue = new LinkedList<>();
		List<Type> visited = new ArrayList<>();

		//Path in order gets added and iterated through.
		List<Type> initialPath = new ArrayList<>();
		initialPath.add(srcData);
		queue.add(initialPath);

		while (!queue.isEmpty()) {
			List<Type> currentPath = queue.poll();
			Type current = currentPath.get(currentPath.size() - 1);

			//If it finds the target it breaks from the loop and returns the list.
			if (current.equals(dstData)) {
				visited = currentPath;
				break;
			}

			//goes through each possible path.
			for (int i = 0; i < sources.size(); i++) {
				if (sources.get(i).equals(current) && !isContainedInVisited(queue, destinations.get(i))) {
					List<Type> newPath = new ArrayList<>(currentPath);
					newPath.add(destinations.get(i));
					queue.add(newPath);
				}
			}
		}

		return visited;
	}

	/**
	 * Helper method to check if a vertex has been visited by the bfs method.
	 * @param visited - a list of visited nodes.
	 * @param target - the vertex we wanna check.
	 * @return true if we have visited, false otherwise.
	 * @param <Type> generic
	 */
	private static <Type> boolean isContainedInVisited(Queue<List<Type>> visited, Type target) {
		for (List<Type> path : visited) {
			if (path.contains(target)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method must use the topological sort algorithm presented in lecture to generate a sorted ordering of the vertices in the graph.
	 * Note that a graph may have more than one valid ordering, and any such ordering is accepted.  Throws an IllegalArgumentException if the graph contains a cycle (recall topological sort works only on acyclic graphs).
	 * @param sources a list of vertices for the edges in the graph.
	 * @param destinations where source goes.
	 * @return a sorted graph
	 * @param <Type> generic
	 * @throws IllegalArgumentException -if the graph contains a cycle (recall topological sort works only on acyclic graphs).
	 */
	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		//Counting the vertices in the graph for the source and for the destination.
		List<Type> vertices = new ArrayList<>();
		for (Type source : sources) {if (!vertices.contains(source)) {vertices.add(source);}}
		for (Type destination : destinations) {if (!vertices.contains(destination)) {vertices.add(destination);}}

		//Create an empty adjacency list for each vertex. This could be done in its own method, but I didn't feel the need
		List<List<Type>> adjacencyList = new ArrayList<>(vertices.size());
		for (int i = 0; i < vertices.size(); i++) {
			adjacencyList.add(new ArrayList<>());
		}

		//Populate it with some goodies.
		for (int i = 0; i < sources.size(); i++) {
			Type source = sources.get(i);
			Type destination = destinations.get(i);
			int sourceIndex = vertices.indexOf(source);
			adjacencyList.get(sourceIndex).add(destination);
		}

		//Sort the list.
		return toposort(vertices, adjacencyList);
	}

	/**
	 * Private topological sort method for the sort() method.
	 * @param vertices - vertices to be checked.
	 * @param adjacencyList - an adjacency matrix for the graph.
	 * @return a sorted list of vertices
	 * @param <Type> - generic
	 */
	private static <Type> List<Type> toposort(List<Type> vertices, List<List<Type>> adjacencyList) {
		List<Type> sorted = new ArrayList<>();

		// Calculate the degree
		List<Integer> d = degree(vertices, adjacencyList);

		// Initialize the queue with vertices having a degree of 0
		Queue<Type> queue = new LinkedList<>();
		for (int i = 0; i < d.size(); i++) {
			if (d.get(i) == 0) {
				queue.add(vertices.get(i));
			}
		}

		//Loop through the queue to perform the topo sort.
		while (!queue.isEmpty()) {
			//Current is the front of the queue, it gets added to a list of objects that have been sorted, so it doesn't go back over the object.
			Type current = queue.poll();
			sorted.add(current);

			//Keeps track of an index and loops through the adjacency matrix and compares.
			int currentIndex = vertices.indexOf(current);
			for (Type neighbor : adjacencyList.get(currentIndex)) {
				int neighborIndex = vertices.indexOf(neighbor);
				d.set(neighborIndex, d.get(neighborIndex) - 1);
				if (d.get(neighborIndex) == 0) {
					queue.add(neighbor);
				}
			}
		}

		//If at any point the number of objects in the sorted list
		if (sorted.size() != vertices.size()) {
			throw new IllegalArgumentException("Do the loopty loop and pull, and now your shoes are lookin cool.");
		}

		return sorted;
	}

	/**
	 * Helper method that gets the degree of each node in the graph.
	 * @param vertices - a list of vertices to find the degree of.
	 * @param adjacencyList - an adjacency list for each of the vertices.
	 * @return a list of integers representing the degree of each vertex.
	 * @param <Type> generic
	 */
	private static <Type> List<Integer> degree(List<Type> vertices, List<List<Type>> adjacencyList) {
		//New list, could be an array but array lists are easier. It will be the exact same size as the list for vertices.
		//We will be building a 1-1 correspondence between each vertex and degree.
		List<Integer> list = new ArrayList<>(vertices.size());
		//Each vertex has a default value of 0 for its degree.
		for (int i = 0; i < vertices.size(); i++) {
			list.add(0);
		}
		//It will loop through each vertex and find the degree by incrementing the value of the list.
		for (List<Type> neighbors : adjacencyList) {
			for (Type neighbor : neighbors) {
				int index = vertices.indexOf(neighbor);
				var adj = list.get(index) + 1;
				list.set(index, adj);
			}
		}
		return list;
	}


//	/**
//	 * Builds "sources" and "destinations" lists according to the edges
//	 * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
//	 * data type is String.
//	 *
//	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
//	 * --accepts \\-style comments
//	 * --accepts one edge per line or edges terminated with ;
//	 * --does not accept attributes in [] (e.g., [label = "a label"])
//	 *
//	 * @param filename - name of the DOT file
//	 * @param sources - empty ArrayList, when method returns it is a valid
//	 *        "sources" list that can be passed to the public methods in this
//	 *        class
//	 * @param destinations - empty ArrayList, when method returns it is a valid
//	 *        "destinations" list that can be passed to the public methods in
//	 *        this class
//	 */
//	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<Edge> destinations) {
//
//		Scanner scan = null;
//		try {
//			scan = new Scanner(new File(filename));
//		}
//		catch(FileNotFoundException e) {
//			System.out.println(e.getMessage());
//			System.exit(0);
//		}
//
//		scan.useDelimiter(";|\n");
//
//		// Determine if graph is directed (i.e., look for "digraph id {").
//		String line = "", edgeOp = "";
//		while(scan.hasNext()) {
//			line = scan.next();
//
//			// Skip //-style comments.
//			line = line.replaceFirst("//.*", "");
//
//			if(line.indexOf("digraph") >= 0) {
//				edgeOp = "->";
//				line = line.replaceFirst(".*\\{", "");
//				break;
//			}
//		}
//		if(edgeOp.equals("")) {
//			System.out.println("DOT graph must be directed (i.e., digraph).");
//			scan.close();
//			System.exit(0);
//
//		}
//
//		// Look for edge operator -> and determine the source and destination
//		// vertices for each edge.
//		while(scan.hasNext()) {
//			String[] substring = line.split(edgeOp);
//
//			for(int i = 0; i < substring.length - 1; i += 2) {
//				// remove " and trim whitespace from node string on the left
//				String vertex1 = substring[0].replace("\"", "").trim();
//				// if string is empty, try again
//				if(vertex1.equals(""))
//					continue;
//
//				// do the same for the node string on the right
//				String vertex2 = substring[1].replace("\"", "").trim();
//				if(vertex2.equals(""))
//					continue;
//
//				// indicate edge between vertex1 and vertex2
//				sources.add(vertex1);
//				destinations.add(vertex2);
//			}
//
//			// do until the "}" has been read
//			if(substring[substring.length - 1].indexOf("}") >= 0)
//				break;
//
//			line = scan.next();
//
//			// Skip //-style comments.
//			line = line.replaceFirst("//.*", "");
//		}
//
//		scan.close();
//	}
}
