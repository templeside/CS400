//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ////////////////////
//
// Title: P4
// Files: Graph, PackageManager, GraphTest, PackageManagerTest, CycleException, 
// GraphADT, Package, PackageNotFoundException
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


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * Filename:   PackageManager.java
 * Project:    p4
 * Authors:    Chanwoong Jhon and Chaiyeen Oh
 * 
 * PackageManager is used to process json package dependency files
 * and provide function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own
 * entry in the json file.  
 * 
 * Package dependencies are important when building software, 
 * as you must install packages in an order such that each package 
 * is installed after all of the packages that it depends on 
 * have been installed.
 * 
 * For example: package A depends upon package B,
 * then package B must be installed before package A.
 * 
 * This program will read package information and 
 * provide information about the packages that must be 
 * installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with
 * our own Test classes.
 */
public class PackageManager {

	private Graph graph;

	/*
	 * Package Manager default no-argument constructor.
	 */
	public PackageManager() {
	}

	/**
	 * Takes in a file path for a json file and builds the
	 * package dependency graph from it. 
	 * 
	 * @param jsonFilepath the name of json data file with package dependency information
	 * @throws FileNotFoundException if file path is incorrect // how to throw this???????????
	 * @throws IOException if the give file cannot be read     // not try/catch???????
	 * @throws ParseException if the given json cannot be parsed 
	 */
	public void constructGraph(String jsonFilepath) throws FileNotFoundException, IOException, ParseException {
		graph = new Graph();

		// parsing file "jsonFilepath"   
		Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
		// typecast Object obj --> JSONObject jsonObject
		JSONObject jsonObject = (JSONObject) obj; 
		// get packages
		JSONArray jArray = (JSONArray) jsonObject.get("packages"); // package array

		// iterate packages
		Iterator<JSONObject> itr1 = jArray.iterator(); // for each {}

		while(itr1.hasNext()) { // first {}
			// NAME
			JSONObject each =  (JSONObject)itr1.next(); // {}
			String name = (String) each.get("name");
			this.graph.addVertex(name); // add name as a vertex ****             

			// DEPENDENCIES
			JSONArray dep = (JSONArray) each.get("dependencies");
			Iterator depItr = (Iterator) dep.iterator();

			while(depItr.hasNext()) {
				String depvertext = (String) depItr.next();
				graph.addVertex(depvertext);
				graph.addEdge(name, depvertext);
			}           
		}
	}

	/**
	 * Helper method to get all packages in the graph.
	 * 
	 * @return Set<String> of all the packages
	 */
	public Set<String> getAllPackages() {
		return graph.getAllVertices();       
	}

	/**
	 * Given a package name, returns a list of packages in a
	 * valid installation order.  
	 * 
	 * Valid installation order means that each package is listed 
	 * before any packages that depend upon that package.
	 * 
	 * @return List<String>, order in which the packages have to be installed
	 * 
	 * @throws CycleException if you encounter a cycle in the graph while finding
	 * the installation order for a particular package. Tip: Cycles in some other
	 * part of the graph that do not affect the installation order for the 
	 * specified package, should not throw this exception.
	 * 
	 * @throws PackageNotFoundException if the package passed does not exist in the 
	 * dependency graph.
	 * 
	 * @param pkg which is the specific vertex the user would want the order of
	 */
	public List<String> getInstallationOrder(String pkg) throws CycleException, PackageNotFoundException {
		if(!graph.getAllVertices().contains(pkg)) {
			throw new PackageNotFoundException(); 
		}
		LinkedList<String> stack = new LinkedList<>();
		LinkedList<String> installation_Order = new LinkedList<String>(); 
		List<String> visited_list = new ArrayList<String>(); 

		helpermethod(pkg, visited_list, stack);

		stack.addFirst(pkg);

		while(stack.peekFirst()!=null) {
			String popped = stack.pop();
			if(!installation_Order.contains(popped)) {
				installation_Order.addFirst(popped);
			}
		}
		return installation_Order; // returns installation order
	}

	/**
	 * Helper method for getInstallationOrder 
	 * This method will have recursion
	 * 
	 * @param pkg
	 * @param visited_list
	 * @param stack
	 * @throws CycleException
	 */
	private void helpermethod(String pkg, List<String> visited_list, LinkedList<String> stack) throws CycleException {
		Iterator<String> depList = graph.getAdjacentVerticesOf(pkg).iterator();
		visited_list.add(pkg);
		while(depList.hasNext()) { // as long as the iterator has next value
			String var = depList.next();
			if (!visited_list.contains(var)) {
				helpermethod(var, visited_list, stack); // recur helper method until there isn't any dependencies left
			} else {
				throw new CycleException();
			}
		}
		visited_list.remove(pkg);
		stack.addFirst(pkg); // linked list formed stack
	}    

	/**
	 * Given two packages - one to be installed and the other installed, 
	 * return a List of the packages that need to be newly installed. 
	 * 
	 * For example, refer to shared_dependecies.json - toInstall("A","B") 
	 * If package A needs to be installed and packageB is already installed, 
	 * return the list ["A", "C"] since D will have been installed when 
	 * B was previously installed.
	 * 
	 * @return List<String>, packages that need to be newly installed.
	 * 
	 * @throws CycleException if you encounter a cycle in the graph while finding
	 * the dependencies of the given packages. If there is a cycle in some other
	 * part of the graph that doesn't affect the parsing of these dependencies, 
	 * cycle exception should not be thrown.
	 * 
	 * @throws PackageNotFoundException if any of the packages passed 
	 * do not exist in the dependency graph.
	 * 
	 * @param newpkg, and installedpkg both in string form
	 */
	public List<String> toInstall(String newPkg, String installedPkg) throws CycleException, PackageNotFoundException {
		List<String> already = getInstallationOrder(installedPkg);
		List<String> result = getInstallationOrder(newPkg);
		result.removeAll(already);
		return result;
	}

	/**
	 * Return a valid global installation order of all the packages in the 
	 * dependency graph.
	 * 
	 * assumes: no package has been installed and you are required to install 
	 * all the packages
	 * 
	 * returns a valid installation order that will not violate any dependencies
	 * 
	 * @return List<String>, order in which all the packages have to be installed
	 * @throws CycleException if you encounter a cycle in the graph
	 * @throws PackageNotFoundException  PackageNotFoundException if any of the packages passed 
	 * do not exist in the dependency graph.
	 */
	public List<String> getInstallationOrderForAllPackages() throws CycleException, PackageNotFoundException  {
		List<String> result = new ArrayList<>();
		for(String vertice: graph.getAllVertices()) {

			if (!result.contains(vertice)) {
				List<String> list = getInstallationOrder(vertice);
				for (String s : list) {
					if (!result.contains(s)) {
						result.add(s);
					}
				}
			}
		}
		return result;
	}

	/**
	 * Find and return the name of the package with the maximum number of dependencies.
	 * 
	 * Tip: it's not just the number of dependencies given in the json file.  
	 * The number of dependencies includes the dependencies of its dependencies.  
	 * But, if a package is listed in multiple places, it is only counted once.
	 * 
	 * Example: if A depends on B and C, and B depends on C, and C depends on D.  
	 * Then,  A has 3 dependencies - B,C and D.
	 * 
	 * @return String, name of the package with most dependencies.
	 * @throws CycleException if you encounter a cycle in the graph
	 * @throws PackageNotFoundException 
	 */
	public String getPackageWithMaxDependencies() throws CycleException, PackageNotFoundException {
		List<String>list = new ArrayList<>(graph.getAllVertices());
		Iterator listIterator = list.iterator();
		listIterator.next();
		String maximum = "";
		int numberOfMaximumDependencies = -1;
		int numberOfCurrentDependencies=-1;

		//exception handler
		if(graph.size()==0)
			return null;
		else {
			for(String vertice: list) {
				numberOfCurrentDependencies = getInstallationOrder(vertice).size();
				if(numberOfMaximumDependencies<numberOfCurrentDependencies) {
					maximum = vertice;
					numberOfMaximumDependencies =numberOfCurrentDependencies; 
				}
			}    		
			return maximum;
		}    	
	}

	/**
	 * driver method for this class
	 * @param args
	 */
	public static void main (String [] args) {
		System.out.println("PackageManager.main()");
	}
}