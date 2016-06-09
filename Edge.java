/**
 * @(#)Edge.java
 * This class connects vertices and holds the distance between them
 * It has 4 methods, including
 * a constructor method,
 * a method for getting the distance,
 * and two methods for getting the distances
 * @author Jack Hughes
 * @version 1.00 2016/6/2
 */
import java.util.*;

public class Edge {

	/*
	 * v1,v2 - connected vertices
	 * distance - distance between the two vertices
	 */
	private final String v1, v2;
	private final int distance;

	/**
	 * Initializes Edge object
	 * @param vertex vertex distance
	 */
    public Edge(String vertex1, String vertex2, int distance) {
    	this.v1 = vertex1;
    	this.v2 = vertex2;
    	this.distance = distance;
    }

	/**
	 * Distance getter method
	 * @return  distance
	 */
    public int distance(){
    	return this.distance;
    }

	/**
	 * Vertex getter method
	 * @return vertex
	 */
    public String v1(){
    	return this.v1;
    }

	/**
	 * Vertex getter method
	 * @return vertex
	 */
    public String v2(){
    	return this.v2;
    }
}