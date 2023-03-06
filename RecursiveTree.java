import java.util.ArrayList;
import java.util.List;

public class RecursiveTree<T extends Comparable<T>> {
	private T initialValue;
	private RecursiveNode<T> rootNode;
	private int size = 0;

	public RecursiveTree() {
		// Just empty.
	}

	public RecursiveTree(T initialValue) {
		this.initialValue = initialValue;
		this.rootNode = new RecursiveNode<>(initialValue);
	}

	/**
	 * Check if the binary tree contains the specified value.
	 * 
	 * @param value Value to find.
	 * @return Result of the check.
	 */
	public boolean contains(T value) {
		if (isEmpty() || value == null) {
			return false;
		}
		if (initialValue == value) {
			return true;
		}
		return rootNode.contains(value);
	}

	/**
	 * Retrieve the smallest value of the binary tree.
	 * 
	 * @return Smallest value.
	 */
	public T getSmallest() {
		if (isEmpty()) {
			return null;
		}
		if (size() == 1) {
			return rootNode.getContent();
		}
		T temp = rootNode.getSmallest();
		if (temp == null) {
			return rootNode.getContent();
		}
		return temp;
	}

	/**
	 * Retrieve the biggest value of the binary tree.
	 * 
	 * @return Biggest value.
	 */
	public T getBiggest() {
		if (isEmpty()) {
			return null;
		}
		if (size() == 1) {
			return rootNode.getContent();
		}
		T temp = rootNode.getBiggest();
		if (temp == null) {
			return rootNode.getContent();
		}
		return temp;
	}

	/**
	 * Add specified value to the list.
	 * 
	 * @param value
	 * @return
	 */
	public boolean add(T value) {
		if (isEmpty()) {
			rootNode = new RecursiveNode<>(value);
			size++;
			return true;
		}
		return rootNode.insert(value);
	}

	/**
	 * Remove specified value.
	 * 
	 * @param value Value to remove.
	 * @return Result of the operation.
	 */
	public boolean remove(T value) {
		if (value == null || rootNode == null) {
			return false;
		}
		boolean temp = rootNode.remove(value);
		rootNode.removeNull();
		if (rootNode.getContent() == null) {
			rootNode = null;
		}
		return temp;
	}

	/**
	 * Add all values from the given list into binary tree.
	 * 
	 * @param values List with values to add.
	 * @return Result of the operation.
	 */
	public boolean addAll(List<T> values) {
		if (values == null) {
			return false;
		}
		for (T value : values) {
			if (value == null) {
				return false;
			}
			add(value);
		}
		return true;
	}

	/**
	 * Convert binary tree into list.
	 * 
	 * @return List of tree content.
	 */
	public List<T> toList() {
		List<T> temp = new ArrayList<>();
		valuesToList(rootNode, temp);
		return temp;
	}

	/**
	 * Add each value of the tree nodes into the list.
	 * 
	 * @param node Node to get the content from.
	 * @param list List which contains the content.
	 */
	private void valuesToList(RecursiveNode<T> node, List<T> list) {
		if (node == null) {
			return;
		}
		valuesToList(node.getLeftNode(), list);
		list.add(node.getContent());
		valuesToList(node.getRightNode(), list);
	}

	public boolean isEmpty() {
		return rootNode == null || rootNode.isEmpty();
	}

	public void clear() {
		rootNode = null;
		size = 0;
	}

	public int size() {
		if (isEmpty()) {
			return 0;
		}
		size = rootNode.size() + 1;
		return size;
	}
}
