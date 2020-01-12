//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ////////////////////
//
// Title: P4
// Files: Graph, PackageManager, GraphTest, PackageManagerTest, CycleException, GraphADT, Package, PackageNotFoundException
// Course:CS400, Spring2019        
//
// Author: Chanwoong Jhon, Chaiyeen Oh
// Email:  cjhon@wisc.edu, coh26@wisc.edu
// Lecturer's Name: Andrew L KUEMMEL (004)
// Due Date: 4/19/2019
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X  Write-up states that pair programming is allowed for this assignment.
//   X  We have both read and understand the course Pair Programming Policy.
//   X  We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons: None
// Online Sources: None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Filename:   Graph.java
 * Project:    p4
 * Authors: Chanwoong Jhon, Chaiyeen Oh    
 * 
 * "Graph" class is an directed and unweighted graph implementation
 */
public class Graph implements GraphADT {
	HashMap<String, List<String>> mapList = null;

	/**
	 * Default no-argument constructor
	 */ 
	public Graph() {
		mapList = new HashMap<String, List<String>>();
	}

	/**
	 * Add new vertex to the graph.
	 *
	 * If vertex is null or already exists,
	 * method ends without adding a vertex or 
	 * throwing an exception.
	 * 
	 * Valid argument conditions:
	 * 1. vertex is non-null
	 * 2. vertex is not already in the graph 
	 * 
	 * @param vertex
	 */
	public void addVertex(String vertex) {
		if (vertex==null || mapList.containsKey(vertex)) {
			return;
		}
		mapList.put(vertex, new ArrayList<>());       
	}

	/**
	 * Remove a vertex and all associated 
	 * edges from the graph.
	 * 
	 * If vertex is null or does not exist,
	 * method ends without removing a vertex, edges, 
	 * or throwing an exception.
	 * 
	 * Valid argument conditions:
	 * 1. vertex is non-null
	 * 2. vertex is not already in the graph 
	 * 
	 * @param string vertex
	 */
	public void removeVertex(String vertex) {
		//If vertex is null or does not exist, return;
		if (vertex == null || !mapList.containsKey(vertex)) {
			return;
		}
		mapList.remove(vertex);

		for (Map.Entry<String,List<String>> entry : mapList.entrySet()) {
			entry.getValue().remove(vertex);
		}
	}

	/**
	 * Add the edge from vertex1 to vertex2
	 * to this graph. (edge is directed and unweighted)
	 * If either vertex does not exist, add the non-existing vertex to the graph and then create an edge.
	 * If the edge exists in the graph,
	 * no edge is added and no exception is thrown.
	 * 
	 * Valid argument conditions:
	 * 1. neither vertex is null
	 * 2. the edge is not in the graph
	 * 
	 * @param string vertex1, string vertex2
	 */
	public void addEdge(String vertex1, String vertex2) {
		//If either vertex does not exist, no edge is added and no exception is thrown.
		if(vertex1==null || vertex2 ==null) {
			return;
		}   

		if (mapList.containsKey(vertex1)) {
			//If the edge exists in the graph, no edge is added and no exception is thrown.
			if (mapList.get(vertex1).contains(vertex2))
				return;
			else {
				mapList.get(vertex1).add(vertex2);
			} 
		}
		if (!mapList.containsKey(vertex1)) {
			addVertex(vertex1);
			mapList.get(vertex1).add(vertex2);
		}
	}

	/**
	 * Remove the edge from vertex1 to vertex2
	 * from this graph.  (edge is directed and unweighted)
	 * If either vertex does not exist,
	 * or if an edge from vertex1 to vertex2 does not exist,
	 * no edge is removed and no exception is thrown.
	 * 
	 * Valid argument conditions:
	 * 1. neither vertex is null
	 * 2. both vertices are in the graph 
	 * 3. the edge from vertex1 to vertex2 is in the graph
	 * 
	 * @param String vertex1, String vertex2
	 */
	public void removeEdge(String vertex1, String vertex2) {
		//If either vertex does not exist, no edge is removed and no exception is thrown.
		if(vertex1==null || vertex2 ==null) {
			return;
		}

		if (mapList.containsKey(vertex1)) {
			//if an edge from vertex1 to vertex2 does not exist, no edge is added and no exception is thrown.
			if (mapList.get(vertex1).contains(vertex2)) {
				mapList.get(vertex1).remove(vertex2);
			}
			else {
				//edge is removed and no exception is thrown.
				return;
			}
		}
	}   

	/**
	 * Returns a Set that contains all the vertices
	 * 
	 * @return Set<String> if there is vertices, otherwise not
	 */
	public Set<String> getAllVertices() {      
		return mapList.keySet();
	}

	/**
	 * Get all the neighbor (adjacent) vertices of a vertex
	 * @param string vertex
	 * @return List<String> of all the adjacent vertices of given vertex
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {
		return mapList.get(vertex).subList(0, mapList.get(vertex).size());
	}

	/**
	 * Returns the number of edges in this graph.
	 * @return int of edges
	 */
	public int size() {
		int size=0;
		mapList.entrySet().iterator();
		for (Map.Entry<String,List<String>> entry : mapList.entrySet()) {
			size = size+ entry.getValue().size();
		}
		return size;
	}

	/**
	 * Returns the number of vertices in this graph.
	 * @return int number of vertices
	 */
	public int order() {
		return mapList.size();
	}
}