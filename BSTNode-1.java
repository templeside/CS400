// Students may use and edit this class as they choose
// Students may add or remove or edit fields, methods, constructors for this class
// Students may inherit from an use this class in any way internally in other classes.
// Students are not required to use this class. 
// BUT, IF YOUR CODE USES THIS CLASS, BE SURE TO SUBMIT THIS CLASS
//
// RECOMMENDED: do not use public or private visibility modifiers
// package level access means that all classes in the package can access directly.
//
// Classes that use this type:  <TODO, list which if any classes use this type>
class BSTNode<K,V> {
	
	K key;
	V value;
	BSTNode<K,V> left;
	BSTNode<K,V> right;
	int balanceFactor;
	int height;
	

	/**
	 * @param key
	 * @param value
	 * @param leftChild
	 * @param rightChild
	 */
	BSTNode(K key, V value, BSTNode<K,V>  leftChild, BSTNode<K,V> rightChild) {
		this.key = key;
		this.value = value;
		this.left = leftChild;
		this.right = rightChild;
		this.height = 0;
		this.balanceFactor = 0;
	}
	
	BSTNode(K key, V value) { this(key,value,null,null); }
	
	/**
	 * Returns the key that is in the NSTNode.
	 * @return key of this node;
	 */
	public K getKey() {
		return key;
	}
	
	/**
	 * Returns the left child of the current Node
	 * @return if left exists, return left child of the current Node or returns null if the child is not exist. 
	 */
	public BSTNode<K,V> getLeftNode(){
		if (left == null)
			return null;
		else
			return left;
	}
	
	/**
	 * Returns the left child of the current Node
	 * @return if left exists, return left child of the current Node or returns null if the child is not exist. 
	 */
	public BSTNode<K,V> getRightNode(){
		if (left == null)
			return null;
		else
			return right;
	}
	
	/**
	 * Returns the height of the node.
	 * @return number that the height is in. 
	 */
	public int getNodeHeight() {
		return height;
	}
	
	/*
	 * Returns the balance factor of the node.
	 * @return number that the height is in.
	 */
	public int getBalanceFactor() {
		return balanceFactor; 
	}
	
	/**
	 * setter method of instance "height"
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Setter method of instance "left"
	 * @param node that user insert 
	 */
	public void setLeftNode(BSTNode<K,V> node) {
		left = node;
	}
	
	/**
	 * Setter method of instance "right"
	 * @param node that user insert 
	 */
	public void setRightNode(BSTNode<K,V> node) {
		right = node;
	}
}
