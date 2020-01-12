import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BSTTest extends DataStructureADTTest {
	 
	BST<String,String> bst;
	BST<Integer,String> bst2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// The setup must initialize this class's instances 
		// and the super class instances.
		// Because of the inheritance between the interfaces and classes,
		// we can do this by calling createInstance() and casting to the desired type
		// and assigning that same object reference to the super-class fields.
		dataStructureInstance = bst = createInstance();
		dataStructureInstance2 = bst2 = createInstance2();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = bst = null;
		dataStructureInstance2 = bst2 = null;
	}

	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance()
	 */
	@Override
	protected BST<String, String> createInstance() {
		return new BST<String,String>();
	}

	/* (non-Javadoc)
	 * @see DataStructureADTTest#createInstance2()
	 */
	@Override
	protected BST<Integer, String> createInstance2() {
		return new BST<Integer,String>();
	}

	/**
	 * Test that empty trees still produce a valid but empty traversal list
	 * for each of the four traversal orders.
	 */
	@Test
	void testBST_001_empty_traversal_orders() {
		try {

			List<String> expectedOrder = new ArrayList<String>();

			// Get the actual traversal order lists for each type		
			List<String> inOrder = bst.getInOrderTraversal();
			List<String> preOrder = bst.getPreOrderTraversal();
			List<String> postOrder = bst.getPostOrderTraversal();
			List<String> levelOrder = bst.getLevelOrderTraversal();

//			 UNCOMMENT IF DEBUGGING THIS TEST
			System.out.println("   EXPECTED: "+expectedOrder);
			System.out.println("   In Order: "+inOrder);
			System.out.println("  Pre Order: "+preOrder);
			System.out.println(" Post Order: "+postOrder);
			System.out.println("Level Order: "+levelOrder);

			assertEquals(expectedOrder,inOrder);
			assertEquals(expectedOrder,preOrder);
			assertEquals(expectedOrder,postOrder);
			assertEquals(expectedOrder,levelOrder);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 002: "+e.getMessage());
		}

	}

	/**
	 * Test that trees with one key,value pair produce a valid traversal lists
	 * for each of the four traversal orders.
	 */
	@Test
	void testBST_002_check_traversals_after_insert_one() {

		try {

			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);
			bst2.insert(10,"ten");
			if (bst2.numKeys()!=1) 
				fail("added 10, size should be 1, but was "+bst2.numKeys());
			List<Integer> inOrder = bst2.getInOrderTraversal();
			List<Integer> preOrder = bst2.getPreOrderTraversal();
			List<Integer> postOrder = bst2.getPostOrderTraversal();
			List<Integer> levelOrder = bst2.getLevelOrderTraversal();

			// UNCOMMENT IF DEBUGGING THIS TEST
			System.out.println("   EXPECTED: "+expectedOrder);
			System.out.println("   In Order: "+inOrder);
			System.out.println("  Pre Order: "+preOrder);
			System.out.println(" Post Order: "+postOrder);
			System.out.println("Level Order: "+levelOrder);

			assertEquals(expectedOrder,inOrder);
			assertEquals(expectedOrder,preOrder);
			assertEquals(expectedOrder,postOrder);
			assertEquals(expectedOrder,levelOrder);

		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 003: "+e.getMessage());
		}

	}
	

	/**
	 * Test that the in-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * In-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_003_check_inOrder_for_balanced_insert_order() {
		// insert 20-10-30 BALANCED
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");

			// expected inOrder 10 20 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getInOrderTraversal();
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}
	
	/**
	 * Test that the pre-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * Pre-Order traversal order: 20-10-30
	 */
	@Test
	void testBST_004_check_preOrder_for_balanced_insert_order() {
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");

			// expected inOrder 20 10 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(20);   // V
			expectedOrder.add(10);   // L
			expectedOrder.add(30);   // R

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getPreOrderTraversal();
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 004: "+e.getMessage());
		}
	}

	/**
	 * Test that the post-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * Post-Order traversal order: 10-30-20
	 */
	@Test
	void testBST_005_check_postOrder_for_balanced_insert_order() {
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");

			// expected inOrder 10 30 20
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(30);   // R
			expectedOrder.add(20);   // V

			// GET POST-ORDER and check
			
			List<Integer> actualOrder = bst2.getPostOrderTraversal();
			System.out.println("actual post order is "+actualOrder);
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 005: "+e.getMessage());
		}
	}

	/**
	 * Test that the level-order traversal order is correct if the 
	 * items are entered in a way that creates a balanced BST
	 * 
	 * Insert order: 20-10-30
	 * Level-Order traversal order: 20-10-30
	 */
	@Test
	void testBST_006_check_levelOrder_for_balanced_insert_order() {
		try {
			bst2.insert(20,"1st key inserted");
			bst2.insert(10,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");

			// expected levelOrder 20 10 30
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(20);   // V
			expectedOrder.add(10);   // L
			expectedOrder.add(30);   // r

			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getLevelOrderTraversal();
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 006: "+e.getMessage());
		}		
	}

	/**
	 * Test that the in-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30-40
	 * In-Order traversal order: 10-20-30-40
	 */
	@Test
	void testBST_007_check_inOrder_for_not_balanced_insert_order() {
		try {
			bst2.insert(10,"1st key inserted");
			bst2.insert(20,"2nd key inserted");
			bst2.insert(30,"3rd key inserted");
			bst2.insert(40,"4rd key inserted");
			
			// expected inOrder 10 20 30 40
			
			List<Integer> expectedOrder = new ArrayList<Integer>();
			expectedOrder.add(10);   // L
			expectedOrder.add(20);   // V
			expectedOrder.add(30);   // R
			expectedOrder.add(40);   // R
			
			// GET IN-ORDER and check
			List<Integer> actualOrder = bst2.getLevelOrderTraversal();
			System.out.println(actualOrder);
			assertEquals(expectedOrder,actualOrder);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Unexpected exception 007: "+e.getMessage());
		}		
	}

	/**
	 * Test that the pre-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * Pre-Order traversal order: 10-20-30
	 */
	@Test
	void testBST_008_check_preOrder_for_not_balanced_insert_order() {
		
		// TODO implement this test
		fail("not yet implemented");

	}

	/**
	 * Test that the post-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * Post-Order traversal order: 30-20-10
	 */
	@Test
	void testBST_009_check_postOrder_for_not_balanced_insert_order() {
		
		// TODO implement this test
		fail("not yet implemented");

	}

	/**
	 * Test that the level-order traversal order is correct if the 
	 * items are entered in a way that creates an un-balanced BST
	 * 
	 * Insert order: 10-20-30
	 * Level-Order traversal order: 10-20-30 (FIXED ON 2/14/18)
	 */
	@Test
	void testBST_010_check_levelOrder_for_not_balanced_insert_order() {
		
		// TODO implement this test
		fail("not yet implemented");

		
	}
	
	
	// TODO: Add your own tests
	
	// Add tests to make sure that get and remove work as expected.
	// Does the height of the tree reflect it's actual and its expected height?
	// Use the traversal orders to check.
	
	// Can you insert many and still "get" them back out?
	
	// Does delete work?  
	// When the key is a leaf?  node with one left child?
	// node with one right child?  node with two children?
	
	// Write replacement value did you choose? 
	// in-order precessor?  in-order successor?
	// How can you test if it is replaced correctly?
	


}
