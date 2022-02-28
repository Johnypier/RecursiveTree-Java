import java.util.List;

public class RecursiveTree<T extends Comparable<T>> {

	private T initialValue;
	private RecursiveNode<T> rootNode;
	private int length = 0;

	public RecursiveTree() {
	}

	public RecursiveTree(T initialValue) {
		this.initialValue = initialValue;
		this.rootNode = new RecursiveNode<T>(initialValue);
	}

	public boolean isEmpty() {
		if (rootNode == null) {
			return true;
		}
		return false;
	}

	public boolean contains(T value) {
		if (isEmpty()) {
			return false;
		}
		if (value == null) {
			return false;
		}
		if (initialValue == value) {
			return true;
		}
		return rootNode.contains(value);
	}

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

	public List<T> toList() {
		return null;
	}

	public boolean add(T value) {
		if (isEmpty()) {
			rootNode = new RecursiveNode<T>(value);
			length++;
			return true;
		}
		return rootNode.insert(value);
	}

	public boolean remove(T value) {
		if (value == null) {
			return false;
		}
		if (rootNode == null) {
			return false;
		}
		boolean temp = rootNode.remove(value);
		rootNode.removeNull();

		if (rootNode.getContent() == null) {
			rootNode = null;
		}
		return temp;
	}

	public boolean addAll(List<T> values) {
		if (values == null) {
			return false;
		}
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i) == null) {
				return false;
			}
			add(values.get(i));
		}
		return true;
	}

	public void clear() {
		rootNode = null;
		length = 0;
	}

	public int size() {
		if (isEmpty()) {
			return 0;
		}
		length = rootNode.size() + 1;
		return length;
	}
}
