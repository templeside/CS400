//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P1
// Files: DataStructureADTTest, DS_My
// Course:CS400, Spring2019        
//
// Author: Chanwoong Jhon
// Email:  cjhon@wisc.edu
// Lecturer's Name: Andrew L KUEMMEL (004)
// Due Date: 2/7/2019
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
/**
 * This class represents a linked list ADT.
 * @author charles
 */
public class DS_My implements DataStructureADT {

    private InnerLinkedNode head;
    public int size;
    
    /**
     * A node for linked list 
     * @author Chanwoong Jhon
     */
	public class InnerLinkedNode{
		private Comparable key;
		private Object value;
		private InnerLinkedNode next;
		
		/**
		 * Cunstructor method to make a InnerLinkedNode.
		 * @param k key of data
		 * @param v value of data
		 */
		public InnerLinkedNode(Comparable k, Object v) {
			this.key = k;
			this.value = v;
			next = null;
			
		}
		public InnerLinkedNode(Comparable k, Object v,InnerLinkedNode next) {
		    this.key = k;
		    this.value = v;
		    this.next = next;
		}
		
		/**
		 * get key of linked node
		 * @return key value of InnerLinkedNode
		 */
		public Comparable getKey() {
		    return key;
		  }
		
		/**
		 * get value of linked node
		 * @return value
		 */
		public Object getValue(){
			return value;
		}
		

		/**
		 * get the next data
		 * @return next InnerLinkedNode
		 */
		public InnerLinkedNode getNext() {
			return next;
		}
		
		/**
		 * set a next inner linked node
		 * @param next Inner Linked Node
		 */
		public void setNext(InnerLinkedNode next) {
			this.next = next;
		}
		
	}
	
	/**
	 * constructor methos. instantiate a new linked list in my case.
	 */
    public DS_My() {
    	head = null;
    	size = 0;
    }

    @Override
    public void insert(Comparable k, Object v) {
    	
    	
    	//If key is null, throws IllegalArgumentException("null key");
    	if (k == null) {
    		throw new IllegalArgumentException("null key");
    	}
    	
    	//can accept and insert null values.
    	InnerLinkedNode newNode = new InnerLinkedNode(k, v);
    	
    	if (head == null) {
    		//System.out.println(Key());//
    		head = newNode;
    		size++;
    		return;
    		}
    	//if key is already in data structure, throws RuntimeException("duplicate key");
    	InnerLinkedNode runner = head;
    	
    	
    	
    	while(runner != null) {
    		System.out.println(runner.getKey());//
    		if(runner.getKey().equals(k))
    			{throw new RuntimeException("duplicate Key");}
    		runner=runner.getNext();
    	}
		System.out.println("-----------------");//

    	
    	//add to data structure
    	newNode.setNext(head);
    	head = newNode;  	
      	size++;
    }

    @Override
    public boolean remove(Comparable k) {
        // If key is null, throws IllegalArgumentException("null key") without decreasing size    	
    	if(k == null)
    		throw new IllegalArgumentException("null key");
    	
        // If key is found, Removes the key from the data structure and decreases size
    	InnerLinkedNode runner = head;
    	InnerLinkedNode previous = null;
    	
    	//If head node is to be deleted
    	if(runner.getKey().equals(k)) {
    		head = head.getNext();
    		size--;
    		return true;
    		}
    	//If k is in the middle of the linked list
    	while(runner.getNext() != null) {
    		previous = runner;
			runner = runner.getNext();
			
    		if(runner.getKey().equals(k)) {
    			previous.setNext(runner.getNext());
    			size--;
    			return true;
    		}    			
    	}
    	
        // If key is not found, returns false.
    	return false;
    }

    
    @Override
    public boolean contains(Comparable k) {
    	InnerLinkedNode runner = head;
    	
    	while(runner != null) {
    		if(runner.getKey().equals(k))
    			return true;
    		else
    			runner = runner.getNext();
    	}
    	
        return false;
    }

    @Override
    public Object get(Comparable k) {
        // If key is null, throws IllegalArgumentException("null key")
    	if (k == null)
    		throw new IllegalArgumentException("null key");

        // Returns the value associated with the specified key
    	if (contains(k)) {
    		InnerLinkedNode runner = head;
    		while(runner.getNext() !=null) {
    			if (runner.getKey().equals(k))
    				return runner.getValue();
    		}
    	}

        // get - does not remove key or decrease size

        return null;
    }

    @Override
    public int size() {
    	
    	return size;
        // Returns the number of elements in the data structure
    }

}
