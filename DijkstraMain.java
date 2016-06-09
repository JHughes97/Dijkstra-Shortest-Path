/**
 * @(#)DijkstraMain.java
 * Given vertices of a graph and the distances between them,
 * this class prints the shortest path between each pair of vertices
 * @author Jack Hughes
 * @version 1.00 2016/6/2
 */
import java.io.*;
import java.util.*;

public class DijkstraMain {

	/**
	 * Main method reads in file containing vertices and the distances
	 * between them, then displays to console these values, and then the
	 * shortest distance between each pair of vertices
	 */
    public static void main(String[] args){
    	//Read in file
    	FileIO io = new FileIO();
    	String[] input = io.load("Graph.txt");
    	int size = input.length;

		//Put values in a 2d array and count number of edges
		int numEdges = 0;
    	int[][] values = new int[size][size];
    	for(int i = 1; i < size; i++){
    		for(int j = 1; j < size; j++){
    			values[i][j] = Integer.parseInt(input[i].split("\t")[j]);
    			if(values[i][j] != 0){
    				numEdges++;
    			}
    		}
    	}

		//Print vertices and distances between them
		for(int i = 0; i < size; i++){
			System.out.print(input[0].split("\t")[i]+"\t");
		}
		System.out.println();
    	for(int i = 1; i < size; i++){
    		System.out.print(input[i].split("\t")[0]+"\t");
    		for(int j = 1; j < size; j++){
    			System.out.print(values[i][j]+"\t");
    		}
    		System.out.println();
    	}
    	System.out.println("******************************************");

		//Put names of vertices in array
    	String[] columnLetters = input[0].split("\t");
    	String[] rowLetters = new String[size];
    	for(int i = 0; i < size; i++){
    		rowLetters[i] = input[i].split("\t")[0];
    	}

		//Put connected vertices and the length of their edges in array
		String[][] fromto = new String[2][numEdges];
		int[] distances = new int[numEdges];
		int index = 0;
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(values[i][j] != 0){
					fromto[0][index] = rowLetters[i];
					fromto[1][index] = columnLetters[j];
					distances[index] = values[i][j];
					index++;
				}
			}
		}

		//Print lengths of edges
		for(int i=0;i<numEdges;i++){
			System.out.println(fromto[0][i]+"  -  "+fromto[1][i]+"  :  "+distances[i]);
		}
		System.out.println("******************************************");

		//Create array of edges
		Edge[] edges = new Edge[numEdges];
		for(int i = 0; i < numEdges; i++){
			edges[i] = new Edge(fromto[0][i], fromto[1][i], distances[i]);
		}

		//Create graph using edge array
		Graph graph = new Graph(edges);

		//Print paths between vertices
		for(int i = 1; i < size; i++){
			//Set distances of edges using current starting point
			graph.dijkstra(rowLetters[i]);
			for(int j = 1; j < size; j++){
				if(i == j)
					continue;
				System.out.println("< "+rowLetters[i]+" - "+columnLetters[j]+" >\n");
				graph.printPath(columnLetters[j]);
				System.out.println("*************************");
			}
		}

    }

}