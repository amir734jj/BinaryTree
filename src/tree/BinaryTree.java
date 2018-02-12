package tree;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {

    class Node<T> {
		public Node<T> right;
		public Node<T> left;
		public T value;

		public Node(Node<T> left, T value, Node<T> right) {
			this.left = left;
			this.value = value;
			this.right = right;
		}

		public Node(T value) {
			this(null, value, null);
		}
	}

    private boolean balance;
	private Node<T> _root;
	private int _size;

	public BinaryTree() {
		_size = 0;
		balance = true;
	}

	public BinaryTree(boolean balance) {
        this();
        this.balance = balance;
    }
	
	public BinaryTree(T[] values) {
        this(Arrays.asList(values));
    }
	
	public BinaryTree(List<T> values) {
        this();
        addAll(values);
    }

    /**
     * Helper method used by naivelyBalanceTree
     * @param tree
     * @param list
     */
	public void naivelyBalanceTreeRecursive(BinaryTree<T> tree, List<T> list) {
		if (list.size() < 3) {
			tree.addAll(list);
		} else {
			int middle = list.size() / 2;

            tree.add(list.get(middle));

			naivelyBalanceTreeRecursive(tree, list.subList(0, middle));

			naivelyBalanceTreeRecursive(tree, list.subList(middle + 1, list.size()));
		}
	}

    /**
     * Naively balances the tree
     */
	public void naivelyBalanceTree() {
        if (Math.abs(rightHeight() - leftHeight()) > 1) {
            List<T> list = toList();

            BinaryTree<T> tree = new BinaryTree<T>(false);

            naivelyBalanceTreeRecursive(tree, list);

            _root = tree._root;
        } else{
            // tree is already balanced
        }
	}

    /**
     * Helper method used by toList
     * @param list
     * @param node
     */
	private void toListRecursive(List<T> list, Node<T> node) {
		if (node == null) {
			return;
		} else {
			toListRecursive(list, node.left);

			list.add(node.value);

			toListRecursive(list, node.right);
		}
	}

    /**
     * Converts binary tree to a list
     * @return
     */
	public List<T> toList() {
		List<T> list = new LinkedList<T>();

		toListRecursive(list, _root);

		return list;
	}

    /**
     * Helper method for Add
     * @param node
     * @param value
     */
	private void AddRecursive(Node<T> node, T value) {
		int compareVal = value.compareTo(node.value);

		if (compareVal <= 0) {
			if (node.left == null) {
				node.left = new Node<T>(value);
			} else {
				AddRecursive(node.left, value);
			}
		} else {
			if (node.right == null) {
				node.right = new Node<T>(value);
			} else {
				AddRecursive(node.right, value);
			}
		}
	}

    /**
     * Adds a element to the tree
     * @param value
     * @return
     */
	public T add(T value) {
		if (_size == 0) {
			_root = new Node<T>(value);
		} else {
			AddRecursive(_root, value);
		}

		_size++;

		// re-balance the tree
		if (balance && _size % 100 == 0) naivelyBalanceTree();

		return value;
	}

    /**
     * Adds all element in the list to the tree
     * @param list
     * @return
     */
	public List<T> addAll(List<T> list) {
		list.forEach(x -> add(x));

		return list;
	}

    /**
     * Returns tree size
     * @return
     */
	public int getSize() {
	    return _size;
    }

    /**
     * Returns left height of tree
     * @return
     */
    private int leftHeight() {
	    int height = 0;
	    Node<T> node = _root;

	    while(node != null) {
	        height++;
	        node = node.left;
        }

        return height;
    }

    /**
     * Returns right height of tree
     * @return
     */
    private int rightHeight() {
        int height = 0;
        Node<T> node = _root;

        while(node != null) {
            height++;
            node = node.right;
        }

        return height;
    }

    /**
     * Returns tree height
     * @return
     */
    public int height() {
        return Math.max(rightHeight(), leftHeight());
    }
}
