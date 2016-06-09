/**
 * @(#)Vertex.java
 * This class holds data for vertices
 * It has 5 methods, including
 * a constructor method
 * a method for printing the path to the vertex,
 * a comparator method,
 * a method for adding a neighbouring vertex
 * a method for getting the distance
 * @author Jack Hughes
 * @version 1.00 2016/6/2
 */
import java.util.*;

public class Vertex implements Comparable<Vertex> {

	/*
	 * name - the name of the vertex
	 * distance - the distance to the vertex
	 * previous - previous vertex in the path
	 * neighbours - all connected vertices
	 */
	public final String name;
	public int distance = Integer.MAX_VALUE;
	public Vertex previous = null;
	public final Map<Vertex, Integer> neighbours = new HashMap<>();

	/**
	 * Initializes Vertex object
	 * @param string
	 */
    public Vertex(String name) {
    	this.name = name;
    }

	/**
	 * Prints path to the vertex
	 */
	public void printPath(){
		if(this == this.previous){
			System.out.printf("%s", this.name);
		}else if(this.previous == null){
			System.out.printf("%s(unreached)", this.name);
		}else{
			this.previous.printPath();
			System.out.printf(" --> %s(%d)", this.name, this.distance);
		}
	}

	/**
	 * Compares two vertices based on distance
	 * @param vertex
	 * @return -1 0 1
	 */
	public int compareTo(Vertex two){
		if(this.distance > two.distance())
			return 1;
		else if(this.distance < two.distance())
			return -1;
		else
			return 0;
	}

	/**
	 * Adds neighbouring vertex
	 * @param vertex distance
	 */
	public void addNeighbour(Vertex v, int distance){
		this.neighbours.put(v,distance);
	}

	/**
	 * Distance getter method
	 * @return distance
	 */
	public int distance(){
		return this.distance;
	}
}