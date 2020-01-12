import java.util.ArrayList;  // allowed for creating traversal lists
import java.util.List;       // required for returning List<K>

// TODO: Implement a Binary Search Tree class here
public class BST<K extends Comparable<K>,V> implements BSTADT<K, V> {

	// Tip: Use protected fields so that they may be inherited by AVL
	protected BSTNode<K,V> root;
	protected int numKeys; // number of keys in BST

	// Must have a public, default no-arg constructor
	public BST() { 
	}

	/*
	 * * Returns the keys of the data structure in sorted order.
	 */
	@Override
	public List<K> getPreOrderTraversal() {
		ArrayList<K> preOrderList = new ArrayList<K>(); 
		getPreOrderTraversalHelper(root, preOrderList);
		return preOrderList;
	}
	/**
	 * helper method of getPreOrderTraversal
	 */
	private void getPreOrderTraversalHelper(BSTNode<K,V> root, List<K> preOrderList) {
		if (root == null) {
			return;}
		preOrderList.add(root.getKey());
		getPreOrderTraversalHelper(root.getLeftNode(),preOrderList);
		getPreOrderTraversalHelper(root.getRightNode(),preOrderList);
	}

	/* 
	 * Returns the keys of the data structure in Post-order traversal order.
	 */
	@Override
	public List<K> getPostOrderTraversal() {
		ArrayList<K> listForTraversal = new ArrayList<K>(); 
		getPostOrderTraversalHelper(root, listForTraversal);
		return listForTraversal;
	}

	/**
	 * helper method of getPostOrderTraversal
	 */
	private void getPostOrderTraversalHelper(BSTNode<K,V> root,ArrayList<K> listForTraversal) {
		if (root == null) {
			return;}
		getPostOrderTraversalHelper(root.getLeftNode(), listForTraversal);
		getPostOrderTraversalHelper(root.getRightNode(),listForTraversal);
		listForTraversal.add(root.getKey());
	}

	/*
	 * Returns the keys of the data structure in sorted order.
	 */
	@Override
	public List<K> getInOrderTraversal() {
		ArrayList<K> listForTraversal = new ArrayList<K>();
		getInOrderTraversalHelper(root, listForTraversal);
		return listForTraversal;
	}

	/**
	 * helper method of getLevelOrderTraversal
	 */
	private void getInOrderTraversalHelper(BSTNode<K,V> root, ArrayList<K> orderTraversal) {
		if (root == null) {
			return;}
		
		getInOrderTraversalHelper(root.getLeftNode(), orderTraversal);
		orderTraversal.add(root.getKey());
		getInOrderTraversalHelper(root.getRightNode(), orderTraversal);
	}

	/**
	 * Returns the keys of the data structure in level-order traversal order.
	 */
	@Override
	public List<K> getLevelOrderTraversal() {
		ArrayList<K> listForLevelTraversal = new ArrayList<K>();
		if(root == null)
			return listForLevelTraversal;
		
		for(int i=1; i<=root.getNodeHeight(); i++) {
			getLevelOrderTraversalHelper(root,listForLevelTraversal, i);
		}
		
		return listForLevelTraversal;
	}

	private void getLevelOrderTraversalHelper(BSTNode<K,V> runner, ArrayList<K> listForLevelTraversal,  int level) {
		
		if(runner == null) {
			return;
		}
		if(level == 1) {
			listForLevelTraversal.add(runner.key);
			return;
		}
		else if (level > 1) {
			getLevelOrderTraversalHelper(runner.left, listForLevelTraversal, level-1);
			getLevelOrderTraversalHelper(runner.right, listForLevelTraversal, level-1);
		}
	}



	/* (non-Javadoc)
	 * @see DataStructureADT#insert(java.lang.Comparable, java.lang.Object)
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
		if(key==null) {
			throw new IllegalNullKeyException();
		}
		if(contains(key) == true) {
			throw new DuplicateKeyException();
		}
		if(numKeys == 0) {
			
			root = new BSTNode<K,V>(key,value);
			
			numKeys++;
			root.setHeight(getHeight());
		}
		else{			//Add the key,value pair to the data structure and increase the number of keys.
			insertHelper(key,value,root);
			root.setHeight(getHeight());
			
		}
	}


	/**
	 * Add the key,value pair to the data structure and increase the number of keys.
	 * @param key	that i want to insert
	 * @param value	that i want to insert
	 * @param node	my current root in the BST.
	 */
	private void insertHelper(K key, V value, BSTNode<K,V> node) {
		System.out.println(key);
		if(key.compareTo(node.key) < 0) { // if the key is smaller than node 
			if(node.getLeftNode() == null) {// no left child
				node.setLeftNode(new BSTNode<K,V>(key,value));
				node.setHeight( node.getNodeHeight());
				numKeys++;
			}
			else {
				insertHelper(key,value,node.getLeftNode());
			}
		}
		else if(key.compareTo(node.key)>0){ // if the key is bigger than the node
			if(node.right == null) { 
				node.setRightNode(new BSTNode<K,V>(key,value));
				node.setHeight( node.getNodeHeight());
		
				numKeys++;
			}
			else {
				insertHelper(key,value,node.getRightNode());
			}
		}      
	}

	/* find the BST and remove if the node is in the BST

	 * @see DataStructureADT#remove(java.lang.Comparable)
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
		//If key is null, throw IllegalNullKeyException
		if(key == null)
			throw new IllegalNullKeyException();

		//If key is not found, throw KeyNotFoundException().
		else if (contains(key) ==false)
			throw new KeyNotFoundException();

		//If key is found, remove the key,value pair from the data structure and decrease num keys.
		else if (contains(key) == true) {
			removeHelper(root, key, null);
			return true;
			}
		else
			return false;
	}

	private void removeHelper(BSTNode<K,V> node, K keyToDelete, BSTNode<K,V> parentNode) {		
		//key 랑 node랑 같을때
		if(node.key.compareTo(keyToDelete) == 0) {		
			
			if(node.left ==null && node.right == null)
				return;
			
			//when n has only left child		
			if (node.getLeftNode() !=null && node.getRightNode()==null) {
				//if no parent,
				if (parentNode == null)
					root = node.left;
				//if parent가 left일때
				else if (parentNode.key.compareTo(node.key)>0) {
					parentNode.setRightNode(node.left);
				}
				//if parent right일떄
				else {
					parentNode.setLeftNode(node.left);
				}
				
			}

			//when n has only right child		
			else if (node.getLeftNode() ==null && node.getRightNode()!=null) { 
				//if no parent,
				if (parentNode == null)
					root = node.right;
				
				//if parent가 left일때
				else if (parentNode.key.compareTo(node.key)>0) {
					parentNode.setLeftNode(node.right);
				}
				
				//if parent right일떄
				else {
					parentNode.setRightNode(node.right);
				}
			}

			//when have both child
			else if (node.getLeftNode() !=null && node.getRightNode()!=null) {
				//parent 가 null 일떄
				if(parentNode == null) {
					InorderPredecessor(node.left).right = node.right;
					root = InorderPredecessor(node.left).right;
					}
				
				//parent 가 left 일떄
				else if (parentNode.key.compareTo(node.key)>0) {
					InorderPredecessor(node.left).right = node.right;
					parentNode.setLeftNode(InorderPredecessor(node.left));
					}

				//when parent's right child is the node
				else {
					InorderPredecessor(node.left).right = node.right;
					parentNode.setRightNode(InorderPredecessor(node.left));
					}
				}
			}
		//작을떄
		else if(node.key.compareTo(keyToDelete)>0)
			removeHelper(node.left, keyToDelete,node);
		//클떄
		else
			removeHelper(node.right, keyToDelete,node);
	}
	
	/**
	 * Return the inorder predecessor
	 * @param root that i want to find the inorderpredecessor
	 * @param level checking does it in the node.
	 * @return return inorder predecessor of the node. 
	 */
	private BSTNode<K,V> InorderPredecessor(BSTNode<K,V> root) {
		if (root==null)
			return root;
		return InorderPredecessor(root.right); 
		}
	
	/**
	 * Return the Successor predecessor
	 * @param root that i want to find the InorderSuccessor
	 * @param level checking does it in the node.
	 * @return return inorder Successor of the node. 
	 */
	private BSTNode<K,V> InorderSuccessor(BSTNode<K,V> root) {
		if (root==null)
			return root;
		return InorderSuccessor(root.left); 
		}

	/* (non-Javadoc)
	 * Returns the value associated with the specified key
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
		//If key is null, throw IllegalNullKeyException 
		if (root.key == null)
			throw new IllegalNullKeyException();

		//If key is not found, throw KeyNotFoundException().
		if(contains(key)==false)
			throw new KeyNotFoundException();	

		return getHelper(root, key);
	}

	/**
	 * Helper of the 'get' method
	 * @param node that the algorithm use
	 * @param key that the user want to find
	 * @return V of the value associated with the specified key
	 */
	private V getHelper(BSTNode<K,V> node, K key) {
		if(node.getKey().compareTo(key) == 0)
			return node.value;
		if(node.key.compareTo(key)>0)
			return getHelper(node.right, key);
		if(node.key.compareTo(key)<0)
			return getHelper(node.left, key);
		return null;
	}

	/*
	 * Returns true if the key is in the data structure
	 */
	@Override
	public boolean contains(K key) throws IllegalNullKeyException {
		if(key==null) {
			throw new IllegalNullKeyException();
		}
		return containsHelper(key,root);
	}

	private boolean containsHelper(K key, BSTNode<K,V> node) {
		while (node !=null) {
			if(node.getKey().compareTo(key)==0) {
				return true;
			}
			if (node.getKey().compareTo(key)>0) {
				return containsHelper(node.key, node.getRightNode());
			}
			if (node.getKey().compareTo(key)<0) {
				node = node.getLeftNode();
			}
		}
		return false;
	}
	/*
	 *Returns the number of key,value pairs in the data structure
	 */
	@Override
	public int numKeys() {
		return numKeys;
	}

	/* 
	 * Returns the key that is in the root node of this BST.
	 * return null if root is null, or return the key that is in the root  
	 */
	@Override
	public K getKeyAtRoot() {
		if (root == null)
			return null;
		else
			return root.getKey();
	}

	/**
	 * Tries to find a node with a key that matches the specified key.
	 * If a matching node is found, it returns the returns the key that is in the left child.
	 * If the left child of the found node is null, returns null.
	 * param is the key A key to search for
	 * returns the key that is in the left child of the found key
	 */
	@Override
	public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
		if (key==null) throw new IllegalNullKeyException();
		BSTNode<K,V> runner = root;
		while (runner !=null) {
			if (runner.getKey().compareTo(key)>0)
				runner = runner.left;
			if(runner.getKey().compareTo(key)<0)
				runner = runner.right;
			if(runner.getKey().compareTo(key)==0)
				return runner.getLeftNode().getKey();
		}
		throw new KeyNotFoundException();		//when there is no left child 
	}

	/**
	 * Tries to find a node with a key that matches the specified key.
	 * If a matching node is found, it returns the returns the key that is in the right child.
	 * If the right child of the found node is null, returns null.
	 * param is the key A key to search for
	 * returns the key that is in the right child of the found key
	 */
	@Override
	public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {

		if(key == null) throw new IllegalNullKeyException();
		BSTNode<K,V> runner = root;
		while (runner !=null) {
			if (runner.getKey().compareTo(key)>0)
				runner = runner.left;
			if(runner.getKey().compareTo(key)<0)
				runner = runner.right;
			if(runner.getKey().compareTo(key)==0)
				return runner.getRightNode().getKey();
		}
		throw new KeyNotFoundException();		//when there is no left child

	}

	/**Returns the height of this BST.
	 * H is defined as the number of levels in the tree.
	 * 
	 * Else return 1 + max( height(root.left), height(root.right) )
	 */
	@Override
	public int getHeight() {		
		return getHeightRecursive (root);

	}

	/**
	 * Helping method of the method "getHeight." Returns the height of this BST
	 * @param root
	 * @return height of the BST
	 **/

	private int getHeightRecursive(BSTNode<K,V> root) {
		if (root == null) 													//If root is null, return 0
			return 0;
		if (root.getLeftNode()==null && root.getRightNode()==null)			//If root is a leaf, return 1
			return 1;
		return 1 + Math.max(getHeightRecursive(root.left) , getHeightRecursive(root.right));
	}
	
	protected void printSideways() {
	       System.out.println("------------------------------------------");
	       recursivePrintSideways(root, "");
	       System.out.println("------------------------------------------");
	   }

	   // private recursive helper method for printSideways above
	   protected void recursivePrintSideways(BSTNode<K,V> current, String indent) {
	   if (current != null) {
	           recursivePrintSideways(current.right, indent + "    ");
	           System.out.println(indent + current.key + "," + current.height ); 
	           recursivePrintSideways(current.left, indent + "    ");
	       }
	   }
}




