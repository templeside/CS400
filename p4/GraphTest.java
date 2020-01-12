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

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * GraphTest is testing all the methods from Graph.java class
 * 
 * @author Chanwoong Jhon & Chaiyeen Oh
 *
 */
class GraphTest {

	/**
	 * Tests that addVertex() add a vertex into the graph. 
	 */
	@Test
	public void test000_addVertex() {
		
		try {
			
			Graph graph = new Graph();
			graph.addVertex("A");		
			
			if (graph.mapList.containsKey("A"))
				return;
			else
				fail("graph and checker should be same. addVertex does not work");
		}
		catch(Exception e) {
			fail("graph and checker should be same. addVertex does not work");
		};
	}
	
	/**
	 * Tests that removeVertex() remove a vertex into the graph. 
	 */
	@Test
	public void test001_removeVertex() {
		try {
			Graph graph = new Graph();
			HashMap checker = new HashMap();
			
			graph.addVertex("A");
			graph.removeVertex("A");
			
			if (graph.mapList.equals(checker))  
				return;
			else
				fail("graph and checker should be same. getAllVertices does not work");
		}
		catch(Exception e) {
			fail("graph and checker should be same. getAllVertices does not work");
		};
		
	}
	/*
	 * Tests that removeVertex() remove a vertex into the graph.
	 */
	@Test
	public void test002_addEdge() {
		try {
			Graph graph = new Graph();
			graph.addVertex("A");
			graph.addEdge("A", "B");
					
			if (graph.mapList.get("A").get(0).equals("B"))
				return;
			else
				fail("graph and checker should be same. addEdge does not work");
		}
		catch(Exception e) {
			fail("graph and checker should be same. addEdge does not work");
		};
	}
	
	/**
	 * Tests that removeEdge() add a vertex into the graph. 
	 */
	@Test
	public void test003_removeEdge() {
		try {
			Graph graph = new Graph();
			String c = "C"	;
			
			List<String>checker = new ArrayList<String>();
			checker.add("B");
			
			graph.addVertex("A");
			graph.addEdge("A", "B");
			graph.addEdge("A", c);
			graph.removeEdge("A", c);
						
			if (graph.mapList.get("A").equals(checker))
				return;
			else
				fail("graph and checker should be same");
		}
		catch(Exception e) {
			fail("graph and checker should be same");
		};
	}
	
	/**
	 * Tests that getAllVertices() add a vertex into the graph.
	 */
	@Test
	public void test004_getAllVertices() {
		try {
			Graph graph = new Graph();
			Set<String> checker = new HashSet<String>();
			checker.add("A");
			checker.add("B");
			checker.add("C");
			checker.add("D");
			
			graph.addVertex("A");
			graph.addVertex("B");
			graph.addVertex("C");
			graph.addVertex("D");		
			
			
			if (graph.getAllVertices().equals(checker))
				return;
			else
				fail("graph and checker should be same. getAllVertices does not work");
		}
		catch(Exception e) {
			fail("graph and checker should be same. getAllVertices does not work");
		};
	}
	
	/**
	 * Tests that getAdjacentVerticesOf() add a vertex into the graph.
	 */
	@Test
	public void test005_getAdjacentVerticesOf() {
		try {
			Graph graph = new Graph();
			List<String> checker = new ArrayList<String>();
			checker.add("B");
			checker.add("C");
			checker.add("D");
			
			graph.addVertex("A");
			graph.addEdge("A", "B");
			graph.addEdge("A", "C");
			graph.addEdge("A", "D");
			graph.addVertex("B");
			graph.addVertex("C");
			graph.addVertex("D");		
			
			if (graph.getAdjacentVerticesOf("A").equals(checker))
				return;
			else
				fail("graph and checker should be same. getAllVertices does not work");
		}
		catch(Exception e) {
			fail("graph and checker should be same. getAllVertices does not work");
		};
		
	}
	
	
}
