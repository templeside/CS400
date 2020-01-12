// A BST search tree that maintains its balance using AVL rotations.
public class AVL<K extends Comparable<K>,V> extends BST<K, V> {

   //field
   private BSTNode<K, V> tempLeft;
   private BSTNode<K, V> tempRight;
   private int BalancedFactorNumber;
   private BSTNode<K, V> tempKey;
   private BSTNode<K, V> node;
   private K key;
   private BSTNode<K, V> temp;
   private BSTNode<K, V> minimum;


   public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
      if(key == null) {
         throw new IllegalNullKeyException();         
      }
      if(contains(key)) {
         throw new DuplicateKeyException();
      }
      //insert
      if(numKeys == 0) { // empty tree
         root = new BSTNode<K,V>(key,value);      
         numKeys++;
         root.setHeight(1);         
         return;
      }
      else{//Add the key,value pair to the data structure and increase the number of keys.
         insertHelper(key,value,root);
      }   
   }
   
   private BSTNode<K, V> insertHelper(K key, V value, BSTNode<K,V> node) {
      if(key.compareTo(node.key) < 0) { // key is smaller
         if(node.getLeftNode() == null) {// no left child
            node.setLeftNode(new BSTNode<K,V>(key,value));
            numKeys++;
            }
         else {
            insertHelper(key,value,node.left);
         }
      } // recursive 
      else if(key.compareTo(node.key)>0){ //key is bigger -> right
         if(node.right == null) { 
            node.setRightNode(new BSTNode<K,V>(key,value));      
            numKeys++;
         }
         else {
            insertHelper(key,value,node.getRightNode());
         }
         
         node.setHeight(1+ Math.max(node.getLeftNode().getNodeHeight(), node.getRight().getHeight()));
         
         
         // rotate to keep the tree balanced
         if(node.getBalanceFactor() <= -2) {
            if(node.getRightNode().getBalanceFactor() >0) {
               node.setRightNode(RightRotate(node.getRightNode()));
            }
            else {
               node = LeftRotate(node);
            }
         }
         else if(node.getBalanceFactor() >= 2) {
            if(node.getLeftNode().getBalanceFactor() < 0) {
               node.setLeftNode(LeftRotate(node.getLeftNode()));
            }
            else {
               node = RightRotate(node);
            }
         }
      }
      return node;
   }
   

   
   private BSTNode<K,V> RightRotate(BSTNode<K,V> node){
      tempKey = node.getLeftNode();
      node.setLeftNode(tempKey.right);
      tempKey.setRightNode(node);
      
      node.setHeight(1+Math.max(node.getLeftNode().getNodeHeight(), node.getRight().getHeight()));
      tempKey.setHeight(1+Math.max(tempKey.getLeftNode().getNodeHeight(), tempKey.getRight().getHeight()));

      return tempKey;
   }
   
   private BSTNode<K,V> LeftRotate(BSTNode<K,V> node){
      tempKey = node.getRightNode();
      node.setRightNode(tempKey.left);
      tempKey.setLeftNode(node);
      
      node.setHeight(1+Math.max(node.getLeftNode().getNodeHeight(), node.getRight().getHeight()));
      tempKey.setHeight(1+Math.max(tempKey.getLeftNode().getNodeHeight(), tempKey.getRight().getHeight()));

      return tempKey;
   }

   
   
   
   
   public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
      if(key==null) { // nothing in the node
         throw new IllegalNullKeyException();
      }
      if(!contains(key)) { // not found in the tree
         throw new KeyNotFoundException();
      }
      else {
         removeHelper(key, root);
         return true;
      }
   }
   
   //want to remove "key"
   /**
    * first find if the node equals to the key; recursion will occur in the process
    * then use difference case (total of 3 cases) for its removal
    * 
    * @param key
    * @param node
    * @return
    */
   private BSTNode<K,V> removeHelper(K key, BSTNode<K,V> node) {
      this.node = node;
      this.key = key;
      
      if(node.key.compareTo(key) == 1) { // if node key is smaller than the node ( need to go left  - recursive)
         removeHelper(key,node.getLeftNode());
         numKeys--;
      }
      if(node.key.compareTo(key) == -1) { // node key is bigger than the node ( need to go right )
         removeHelper(key, node.getRightNode());
         numKeys--;
      }
      else if (node.key.compareTo(key) == 0){ // found the node that I want to remove
         if(node.getLeftNode() == null && node.getRightNode() == null) { // no right & left child -> just remove
            node = null;
            numKeys--;
         }
         if(node.getLeftNode() != null && node.getRightNode() == null) { // no right but LEFT -> connect & remove
            temp = node.getLeftNode();
            node.setLeftNode(node);
            node = temp;      
            numKeys--;
         }
         if(node.getLeftNode() == null && node.getRightNode() != null) { // no left but RIGHT -> connect & remove
            temp = node.getRightNode();
            node.setRightNode(node);
            node = temp;
            numKeys--;
         }
         if(node.getLeftNode() != null && node.getRightNode() != null) { // 2 nodes 
            //find the min in right since it still has to be bigger than its left
            minimum = recursiveMinimum(node);
            // switch min & found node
            temp = minimum;
            node = temp;
            minimum = null;// delete node in min spot
            numKeys--;
         }   
         
         //update node height
         node.setHeight(1+ Math.max(node.getLeftNode().getNodeHeight(), node.getRight().getHeight()));
         
         
         // rotate to keep the tree balanced
         if(node.getBalanceFactor() <= -2) {
            //Right
            if(node.getRightNode().getBalanceFactor() >0) { //Right Left Case
               node.setRightNode(RightRotate(node.getRightNode())); 
            }
            else {
               node = LeftRotate(node); // Right Right Case
            }
         }
         
         else if(node.getBalanceFactor() >= 2) {
            //Left
            if(node.getLeftNode().getBalanceFactor() < 0) { // Left Right Case
               node.setLeftNode(LeftRotate(node.getLeftNode()));
            }
            else {
               node = RightRotate(node);// Left Left Case
            }
         }
      }
      return node;   
   }
   
   
   private BSTNode<K,V> recursiveMinimum(BSTNode<K,V> node){
      if(node.getLeftNode() == null) { // no more recursion if no left node --found
         return node; 
      }
      else {
         recursiveMinimum(node.getLeftNode());
      }
      return node;
   }

   
}