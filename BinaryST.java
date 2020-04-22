import java.util.ArrayList;
import java.util.Stack;

public class BinaryST
{
	// member fields and methods
	
	int BSTsize = 0;
	Node root = null;
	int BSTdistinctSize = 0;
	int totalRecurrences = 0;
	
	public BinaryST()
	{
		// implementation
		
	}

	/**
	 * This method builds the Binary search tree given vaid string array
	 * @param s: input array to be processed and added to binary search tree
	 */
	public BinaryST(String[] s)
	{
		for(String st : s){
			this.add(st);
		}
	}
	/**
	 * Method calculates distinct size {without recurrences} of the binary tree
	 * @return Returns the number of distinct nodes present in the binary tree
	 */
	public int distinctSize()
	{
		if(totalRecurrences == 0)
			return this.BSTsize;
		// implementation
		return (this.BSTsize-this.totalRecurrences);
	}

	/**
	 * This method returns the total size of the entire binary tree with recurrences
	 * @return
	 */
	public int size()
	{
		// implementation
		return this.BSTsize;
	}

	/**
	 * This method caluclate the max height in the current built tree by traversing
	 * left and right subtree and then returning the max of the two whichever one has the longer traversal time
	 * @return Max height of an unbalanced tree
	 */
	public int height()
	{
		// implementation
		//O(h) implimentation since we are at most travering the longest path and then finding the max.
		return Math.max(this.maxHeight(root.right), this.maxHeight(root.left));
	}

	/**
	 *
	 * @param p: given a starting node, method calcualtes the total heigh of the given subtree
	 * @return Returns the height traversed the given subtree
	 */
	private int maxHeight(Node p){
	
		  if (p == null) return 0;
		  int left_height = maxHeight(p.left);
		  int right_height = maxHeight(p.right);
		  
		  return (left_height > right_height) ? left_height + 1 : right_height + 1;
	}

	/**
	 * This method adds a given string to the bianry search tree adds the values that are less than the root to the left
	 * and then the values that are greater than the tree are added ot the right of the tree
	 * Runs in O(h) time since it will needs to traverse the correct nodes
	 *
	 * @param s: String to add
	 */
	public void add(String s)
	{
		String e = s;
		
		if (e == null || s.length() == 0)
			throw new NullPointerException("String is null");
		
		if(this.BSTsize == 0){
			root = new Node(e);
			this.BSTsize++;
			return;
		}

		boolean checkToAdd = addHelper(root, e);
		
		if(checkToAdd) {
			this.BSTsize++;
			return;
		}
		else
			return;
	}

	/**
	 * This recursive method adds the node to the either the right or the left subtree of the bianry search tree
	 * given the Java internal comareTo method(Lexicographic comparision)
	 *This runs in O(h) runtime
	 *
	 * @param nodeToAdd Node to add to the binary search tree
	 * @param s: String to be added to the binary tree, only used for comparision
	 * @return
	 */
	private boolean addHelper(Node nodeToAdd,String s){

		Node temp = null;
		
		int compareValue = s.compareTo(nodeToAdd.data);
		
		if(compareValue > 0){
			if(nodeToAdd.right == null){
				temp = new Node(s);
				 nodeToAdd.right = temp;
				 temp.parent = nodeToAdd;
				 nodeToAdd.incrementCount();
				 return true;
			}
			else{
				boolean checkAdd = addHelper(nodeToAdd.right, s);
				if(checkAdd)
						nodeToAdd.incrementCount();
					else
						return checkAdd;
				return checkAdd;
			}
		}
		else if (compareValue < 0){
			if(nodeToAdd.left == null){
				temp = new Node(s);
				nodeToAdd.left = temp;
				temp.parent = nodeToAdd;
				nodeToAdd.incrementCount();
				return true;
				
			}else{
				boolean checkAdd = addHelper(nodeToAdd.left, s);
				if(checkAdd)
						nodeToAdd.incrementCount();
					else
						return checkAdd;
				return checkAdd;
			}
		}
		else{
			if(nodeToAdd.right == null){
				nodeToAdd.incrimentRecurrence();
				this.totalRecurrences++;
				return true;
			}
			else if(nodeToAdd.left == null){
				nodeToAdd.incrimentRecurrence();
				this.totalRecurrences++;
				return true;
			}
			else{
				boolean checkAdd = addHelper(nodeToAdd.right,s);
				if(checkAdd)
					if(nodeToAdd.recurrence == 0)
						nodeToAdd.incrementCount();
					else
						return checkAdd;
				return checkAdd;
			}
		}

		
	}

	/**
	 *This method retures True if the given string exists in the given binary search tree
	 *
	 * @param s: String to search in the binary tree
	 * @return: True if the string exists in the binary tree
	 */
	public boolean search(String s)
	{
		Node curpos = root;

		while (curpos != null)
		{
			String key = null;

			key = curpos.data;

			int cv = s.compareTo(key);

			if (cv == 0) 
				return true;

			if (cv > 0) 
				curpos = curpos.right;

			else 
				curpos = curpos.left;
		}
		
		return false;
	}

	/**
	 * This method returns the number of times a Node's recurrence occures
	 *
	 * @param s: String to search the recurrence of
	 * @return: > 0 if there are more than one node of string s
	 */
	public int frequency(String s)
	{
		Node hold = this.findNode(s);
		if(hold == null)
			return 0;
		return hold.recurrence;
	}

	/**
	 * This method removes a given string from the binary tree if it exits in the binary tree
	 *
	 * @param s: String to be deleted from the tree
	 * @return: Returns True if the remove was sucessfull or false if the node does not exists
	 */
	public boolean remove(String s)
	{
		// implementation

		if ( s == null )
			return false;
		Node toDel = null;

		toDel = findNode(s);

		if (toDel == null)
			return false;

		if(toDel.recurrence > 0) {
			toDel.decrimentRecurrence();
			this.totalRecurrences--;
			this.BSTsize--;
			return true;
		}

		delete( toDel );
		this.BSTsize--;
		return true;

	}

	/**
	 * Helper method for the delete function, given a found node to deltel it will detele and update count and parent accordingly
	 * @param aNode: Node to delete
	 */
	private void delete(Node aNode) {
		if ( aNode == null )
			throw new NullPointerException("delete");

		Node toDel = aNode;

		if ( toDel.left() != null && toDel.right() != null )
		{

			Node nextNodeToLink = (Node) successor(toDel);
			toDel.data = nextNodeToLink.data();
			toDel = nextNodeToLink;
		}

		// at this point we know toDel has at most one child.
		if (toDel.left() != null){ // has left child
			if ( toDel == root )
			{
					root = (Node) toDel.left();
					if (toDel.left() != null) {
						toDel.left.decrementCount();
						toDel.left.parent = null;
					}

			}

			else
			{
					if (toDel.parent().left() == toDel) {
						toDel.parent.decrementCount();
						toDel.parent.left = (Node) (toDel.left());
					} else {
						toDel.parent.decrementCount();
						toDel.parent.right = (Node) (toDel.left());
					}

					if (toDel.left() != null) {
						toDel.left.decrementCount();
						toDel.left.parent = (Node) (toDel.parent());
					}

			}
		}
		//same as last but with right?
		else{ // has right child or null
			if ( toDel == root )
			{
					root = (Node) toDel.right();
					if (toDel.right() != null) {
						toDel.right.decrementCount();
						toDel.right.parent = (null);
					}

			}
			else
			{
					if (toDel.parent().left() == toDel) {
						toDel.parent.decrementCount();
						toDel.parent.left = (Node) (toDel.right());
					} else {
						toDel.parent.decrementCount();
						toDel.parent.right = (Node) (toDel.right());
					}

					if (toDel.left() != null) {
						toDel.left.decrementCount();
						toDel.left.parent = (Node) (toDel.parent());
					}

			}

		}

	} // It takes O(h) time.

	/**
	 * Helper method for the entire program, this method will return a Node given a string to search for
	 * Helps in deleting nodes by doing the searching in O(h) time
	 * @param item
	 * @return
	 */
	private Node findNode(String item) {

		Node curpos = root;

		while (curpos != null)
		{
			String key = null;
			key = ((curpos.data));


			int cv = item.compareTo(key);

			if (cv == 0)
				return curpos;

			if (cv > 0)
				curpos = curpos.right;

			else
				curpos = curpos.left;
		}

		return null;

	} // It takes O(h) time, where h is the height of the tree.

	/**
	 * This helper method helps to find the correct sucessor for a gievn node, this helps in deleting since
	 * we need to find the sucessor to the node being delted and then replace it with that node and then update the
	 * previous Parent nodes accordingly
	 * @param node
	 * @return
	 */
	private Node successor(Node node) {
		// TODO

		node = (Node)node;

		if (node.right() != null)
		{
			Node next = (Node) node.right();
			while (next.left != null)
			{
				next = (Node) next.left();
			}
			return next;


		}
		Node parentNode = (Node) node.parent();
		Node childNode = (Node) node;
		while ( parentNode!=null && parentNode.right() == childNode )
		{
			childNode = parentNode;
			parentNode = (Node) parentNode.parent();
		}
		return parentNode;
	}

	/**
	 *Prints the values bottom up, in a sorted fashion
	 * Traverses the right and left subtree and then adds their value if we no right or left child is found
	 * left child will be printed first since this is increasing order print
	 * @return
	 */
	public String[] inOrder()
	{
		ArrayList<Node> result = new ArrayList<Node>();

		if(root !=null){
			InOrderRecursiveHelper((Node)root,result);
		}
		String[] toRet = new String[result.size()];

		for(int i = 0; i < result.size();i++){
			toRet[i] = result.get(i).getData();
		}

		return toRet;
	}

	/**
	 * This helper method adds all the values to the given result tree and then returns it to the method above
	 *
	 * @param p starting node
	 * @param result All elements in inOrder fashion
	 */
	private void InOrderRecursiveHelper(Node p, ArrayList<Node> result){

		if(p.left()!=null)
			InOrderRecursiveHelper(p.left,result);

		result.add(new Node(p.getData()));

		if(p.right()!=null)
			InOrderRecursiveHelper(p.right,result);
	}

	/**
	 * By using a stack which is FIFO it will store all the nodes visited and then print it out is a FIFO stack order
	 * This method runs in O(n) time since it needs to visit all the nodes regardless
	 * @return Returns in the
	 */
	public String[] preOrder()
	{
		ArrayList<Node> returnList = new ArrayList<Node>();
		if(root == null){
			return ((String[])returnList.toArray());
		}


		Stack<Node> stack = new Stack<Node>();
		stack.push((Node) root);

		while(!stack.empty()){

			Node n = stack.pop();
			returnList.add(new Node(n.getData()));

			if(n.right() != null){
				stack.push((Node)n.right);
			}
			if(n.left() != null){
				stack.push((Node)n.left);
			}

		}
		String[] toret =new String[returnList.size()];

		for(int i = 0; i < returnList.size(); i++){
			toret[i] = returnList.get(i).getData();
		}
		return toret;
	}

	/**
	 * This method computes the RankOf or all the elements that are smaller than string s which means all
	 * elements to the left of a node
	 * This method runs in O(h) time
	 * @param s
	 * @return
	 */
	public int rankOf(String s)
	{
		//Node dummy = this.findNode(s);
		Node rankRoot = root;
		int toRet = rankHelper(root, s);
		return toRet == 0? -1 : toRet;

	}

	/**
	 * This helper method runs through the entire array and returns the number of nodes that are less than this
	 * Could return Node.left.count, but doing this ignores all the leaf nodes present as the lowest level
	 * the children need to be counted towards the rank of string
	 *
	 * @param r: node to traverse
	 * @param s: String to match
	 * @return: Returns the rank of the String s
	 */
	private int rankHelper(Node r, String s){

		Node b = r;
		String key = s;
		int count = 0;
		if (b == null) return 0;
		if (key.compareTo(b.data()) == 1) count++;
		return count + rankHelper(b.left(), key) + rankHelper(b.right(), key);

	}
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
	    toStringRec(root, sb, 0);
	    return sb.toString();
	}
	
	private void toStringRec(Node n, StringBuilder sb, int depth)
	  {
	    for (int i = 0; i < depth; ++i)
	    {
	      sb.append("  ");
	    }
	    
	    if (n == null)
	    {
	      sb.append("-\n");
	      return;
	    }
	    
	    if (n.left != null || n.right != null)
	    {
	      sb.append("+ ");
	    }
	    else
	    {
	      sb.append("- ");
	    }
	    sb.append(n.data.toString());
	    sb.append("\n");
	    if (n.left != null || n.right != null)
	    {
	      toStringRec(n.left, sb, depth + 1);   
	      toStringRec(n.right, sb, depth + 1);
	    }
	  }
}
final class Node {
	public int count = 0;
	public String data;
	public Node right, left, parent;
	public int recurrence = 0;
	
	// TODO add private fields here
	
	Node(String data) {
		// TODO
		this.data = data;
		this.count = 0;

	}
	
	public int count() {

		return count;
	}

	public void incrementCount() {
		this.count++;
	}
	public void decrementCount() {
		this.count--;
	}

	public void setCount(int count){
		this.count = count;
	}

	public String data() {
		// TODO

		return data;
	}

	public Node left() {
		// TODO
		return left;
	}

	public Node parent() {
		// TODO
		return parent;
	}

	public Node right() {
		//TODO
		return right;
	}

	public void incrimentRecurrence(){
		this.recurrence++;
	}
	public void decrimentRecurrence(){
		this.recurrence--;
	}

	public void setRight(Node right){
		this.right = (Node) right;
	}

	public void setLeft(Node left){
		this.left = (Node)left;
	}

	public void setParent(Node parent){
		this.parent = (Node)parent;
	}

	public String getData(){
		StringBuilder sb = new StringBuilder();
		sb.append(data);
		for(int i = 0; i < this.recurrence; i++){
			sb.append(" "+this.data);
		}
		return sb.toString();
	}
	@Override
	public String toString() {
		return ""+data;
	}
}
