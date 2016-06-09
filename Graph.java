/**
 * @(#)Graph.java
 * This class holds vertices and the edges between them
 * There are 6 methods including
 * a constructor method,
 * a method for getting the distance to a vertex,
 * a method that takes in a starting point and creates a navigable set,
 * a method that takes in a  navigable set and sets the distances of the vertices
 * a method that prints the path to a specified vertex
 * a method that prints the paths to all vertices
 * @author Jack Hughes
 * @version 1.00 2016/6/2
 */
import java.util.*;

public class Graph {

	//graph - maps vertices to their names
	public final Map<String, Vertex> graph;

	/**
	 * Initializes Graph object
	 * @param edgesArray
	 */
    public Graph(Edge[] edges) {
    	graph = new HashMap<>(edges.length);

		//Put edges in the hashmap
    	for(Edge e : edges){
    		if(!graph.containsKey(e.v1()))
    			graph.put(e.v1(), new Vertex(e.v1()));
    		if(!graph.containsKey(e.v2()))
    			graph.put(e.v2(), new Vertex(e.v2()));
    	}

		//Set neighbours of each edge
    	for(Edge e : edges){
    		graph.get(e.v1()).addNeighbour(graph.get(e.v2()), e.distance());
    		graph.get(e.v2()).addNeighbour(graph.get(e.v1()), e.distance());
    	}
    }

	/**
	 * distance getter method
	 * @param string
	 * @return distance
	 */
	public int get(String name){
		return graph.get(name).distance();
	}

	/**
	 * Sets pointers to vertices and puts vertices in navigable set
	 * @param string
	 */
	public void dijkstra(String start){
		if(!graph.containsKey(start)){
			System.out.printf("Vertex '%s' is not contained in graph\n", start);
			return;
		}

		final Vertex source = graph.get(start);
		NavigableSet<Vertex> set = new TreeSet<>();

		for(Vertex v : graph.values()){
			v.previous = (v == source) ? source : null;
			v.distance = (v == source) ? 0 : Integer.MAX_VALUE;
			set.add(v);
		}

		dijkstra(set);
	}

	/**
	 * Sets distances of vertices
	 * @param navigableSet
	 */
	private void dijkstra(final NavigableSet<Vertex> set){
		Vertex one, two;

		while(!set.isEmpty()){

			one=set.pollFirst();
			if(one.distance() == Integer.MAX_VALUE)
				break;

			for(Map.Entry<Vertex, Integer> a : one.neighbours.entrySet()){
				two = a.getKey();

				final int newDistance = one.distance()+a.getValue();

				if(newDistance < two.distance()){
					set.remove(two);
					two.distance = newDistance;
					two.previous = one;
					set.add(two);
				}
			}
		}
	}

	/**
	 * Prints path to specified vertex
	 * @param string
	 */
	public void printPath(String end){
		if(!graph.containsKey(end)){
			System.out.printf("Vertex '%s' is not contained in graph\n", end);
			return;
		}

		graph.get(end).printPath();
		System.out.println();
	}

	/**
	 * Prints paths to all vertices
	 */
	public void printAllPaths(){
		for(Vertex a : graph.values()){
			a.printPath();
			System.out.println();
		}
	}
}