//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: DataStructureADTTest
// Files: DataStructureADTTest, DS_My
// Course:CS400, Spring2019        
//
// Author: Chanwoong Jhon
// Email:  cjhon@wisc.edu
// Lecturer's Name: Andrew L KUEMMEL(002)
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
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String,String>> { 
	

	
	private T dataStructureInstance;
	
	protected abstract T createInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
	}
	
	
	@Test
	void test00_empty_ds_size() {
		if (dataStructureInstance.size() != 0)
		fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}
	
	@Test
	void test01_after_insert_one_size_is_one() {
		dataStructureInstance.insert("key" , "value");
		if (dataStructureInstance.size() !=1)
			fail("data structure should contain one value, with size=1, but size is "+dataStructureInstance.size());
	}
	
	@Test
	void test02_after_insert_one_remove_one_size_is_0() {
		dataStructureInstance.insert("key", "value");
		dataStructureInstance.remove("key");
		if(dataStructureInstance.size() !=0)
			fail("data structure should not contain value, so size should be 0. But size is "+dataStructureInstance.size());
	}
	
	@Test
	void test03_duplicate_exception_is_thrown() {
		try {
			dataStructureInstance.insert("a", "valueA");
			dataStructureInstance.insert("b", "valueB");
			dataStructureInstance.insert("a", "valueC");
			fail("data structure has duplicated message. should have RuntimeException");

		}
		catch(RuntimeException e){
			return;
		}
	}
	
	@Test
	void test04_remove_returns_false_when_key_not_present(){
		boolean b;
		dataStructureInstance.insert("a", "valueA");
		dataStructureInstance.insert("b", "valueB");
		dataStructureInstance.insert("c", "valueC");
		b= dataStructureInstance.remove("d");
		if (b != false)
			fail("data structure has to return false. but return "+b);
	}

	@Test	
	void test05_after_insert_three_size_is_three(){
		dataStructureInstance.insert("a", "valueA");
		dataStructureInstance.insert("b", "valueB");
		dataStructureInstance.insert("c", "valueC");
		if(dataStructureInstance.size() !=3)
			fail("data structure size has to be three. but size is "+dataStructureInstance);
	}
	
	@Test	
	void test06_when_key_value_is_null(){
		try {
		dataStructureInstance.insert( null, "valueA");
		fail("data structure should throw IllegalArgumentException.");
		}
		catch(IllegalArgumentException e) { 
			return;
		}
		
	}
	@Test	
	void test07_when_contains_Hi_as_a_key_return_true(){
		dataStructureInstance.insert("Hi", "valueC");
		if (dataStructureInstance.contains("Hi"))
			return;
		else
			fail("contains method should return true. but the method returns " +dataStructureInstance.contains("Hi"));
	}
	
}
