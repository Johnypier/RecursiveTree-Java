public class RecursiveNode<T extends Comparable<T>> {

	private T content;
	private RecursiveNode<T> leftNode;
	private RecursiveNode<T> rightNode;
	private int size = 0;

	public RecursiveNode(T content) {
		this.content = content;
		this.leftNode = null;
		this.rightNode = null;
	}

	public boolean insert(T value) {
		int temp = this.content.compareTo(value);
		if (this.content == value) {
			size++;
			return false;
		} else if (temp > 0) {
			if (this.leftNode != null) {
				size++;
				return this.leftNode.insert(value);
			} else {
				this.leftNode = new RecursiveNode<>(value);
				size++;
			}
			return true;
		} else {
			if (this.rightNode != null) {
				size++;
				return this.rightNode.insert(value);
			} else {
				this.rightNode = new RecursiveNode<>(value);
				size++;
			}
			return true;
		}
	}

	public boolean contains(T value) {
		int temp = this.content.compareTo(value);
		if (this.content == value) {
			return true;
		} else if (temp > 0) {
			if (this.leftNode != null) {
				return this.leftNode.contains(value);
			}
		} else {
			if (this.rightNode != null) {
				return this.rightNode.contains(value);
			}
		}
		return false;
	}

	public int size() {
		return size;
	}

	public T getSmallest() {
		if (leftNode == null) {
			return content;
		}
		return leftNode.getSmallest();
	}

	public T getBiggest() {
		if (rightNode == null) {
			return content;
		}
		return rightNode.getBiggest();
	}

	public boolean remove(T value) {
		if (value == null) {
			return false;
		}
		if (content.equals(value)) {
			if (rightNode != null) {
				content = rightNode.getSmallest();
				rightNode.remove(content);
			} else if (leftNode != null) {
				content = leftNode.getBiggest();
				leftNode.remove(content);
			} else {
				content = null;
			}
			return true;
		}
		if (content.compareTo(value) > 0 && leftNode != null) {
			return leftNode.remove(value);
		} else if (rightNode != null) {
			return rightNode.remove(value);
		}
		return false;
	}

	public void removeNull() {
		if (rightNode != null && rightNode.getContent() == null) {
			rightNode = null;
		}
		if (leftNode != null && leftNode.getContent() == null) {
			leftNode = null;
		}
		if (rightNode != null) {
			rightNode.removeNull();
		}
		if (leftNode != null) {
			leftNode.removeNull();
		}
	}

	protected boolean isEmpty() {
		if (leftNode == null && rightNode == null) {
			return true;
		}
		return false;
	}

	public T getContent() {
		return content;
	}
}
