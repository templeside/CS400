//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION ////////////////////
//
// Title: P3b
// Files: HashTable, MyProfiler
// Course:CS400, Spring2019        
//
// Author: Chanwoong Jhon
// Email:  cjhon@wisc.edu
// Lecturer's Name: Andrew L KUEMMEL (004)
// Due Date: 3/27/2019
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

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Class that contains HashTableADT and TreeMapADT
 * @author chanwoong Jhon
 * @param <K> key of the node
 * @param <V> Value of the node
 */
public class MyProfiler<K extends Comparable<K>, V> {

    HashTableADT<K, V> hashtable;
    TreeMap<K, V> treemap;
    
    /**
     * Instantiate your HashTable and Java's TreeMap
     */
    public MyProfiler() {
        // TODO: complete the Profile constructor
        // Instantiate your HashTable and Java's TreeMap
    	hashtable = new HashTable<K, V>(100, 0.75);
    	treemap = new TreeMap<K, V>();
    }
    
    /**
     * Insert K, V into both data structures
     * @param key that is going into K
     * @param value that is going into V
     * @throws IllegalNullKeyException 
     * @throws DuplicateKeyException
     */
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
        try {
    	//TODO: complete insert method
        // Insert K, V into both data structures
    	hashtable.insert(key, value);
    	treemap.put(key, value);
        }
        catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Usage: java MyProfiler <number_of_elements>");
            System.exit(1);
        }
    }
    
    /**
     * get value V for key K from data structures
     * @param key that the value is contained
     * @throws KeyNotFoundException 
     * @throws IllegalNullKeyException 
     */
    public void retrieve(K key) throws IllegalNullKeyException, KeyNotFoundException {
    	try {
    	hashtable.get(key);
    	treemap.get(key);
    	}
        catch (Exception e) {
        	e.printStackTrace();
            System.out.println("Usage: java MyProfiler <number_of_elements>");
            System.exit(1);
        }
        // TODO: complete the retrieve method
        // get value V for key K from data structures
    }
    
    public static void main(String[] args) {
        try {
            int numElements = Integer.parseInt(args[0]);
            MyProfiler<Integer, Integer> profile = new MyProfiler<Integer, Integer>();
            
            int i;
            for (i = 0; i<numElements;i++) {
            	
            	profile.insert(i, i );
            	profile.retrieve(i);
            }
            
            // TODO: complete the main method. 
            // Create a profile object. 
            // For example, Profile<Integer, Integer> profile = new Profile<Integer, Integer>();
            // execute the insert method of profile as many times as numElements
            // execute the retrieve method of profile as many times as numElements
            // See, ProfileSample.java for example.
            
        
            String msg = String.format("Inserted and retreived %d (key,value) pairs", numElements);
            System.out.println(msg);
        }
        catch (Exception e) {
        	e.printStackTrace();
        	System.out.println(e.getStackTrace());
            System.out.println("Usage: javfrebgegbrtrta MyProfiler <number_of_elements>");
            
            System.exit(1);
        }
    }
}
