import tree.BinaryTree;

public class Main {

	public static void main(String[] args) {
		
		// initialize
		BinaryTree<Integer> tree = new BinaryTree<Integer>();

		// add random numbers
		for(int i = 0; i < 200; i++) {
			tree.add(i);
		}

		// print the list
		System.out.println(tree.toList());
		
		// balance the tree
		tree.naivelyBalanceTree();
		
		// print the tree again
		System.out.println(tree.toList());
	}
}
