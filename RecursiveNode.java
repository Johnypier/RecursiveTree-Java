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

	/**
	 * Insert the specified value into the binary tree.
	 * 
	 * @param value Value to insert.
	 * @return Status of the insertion.
	 */
	public boolean insert(T value) {
		int temp = this.content.compareTo(value);
		if (this.content == value) {
			size++;
			return false;
		}
		if (temp > 0) {
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

	/**
	 * Check if the specified value is contained in the binary tree.
	 * 
	 * @param value Value to find.
	 * @return Result of the check.
	 */
	public boolean contains(T value) {
		int temp = this.content.compareTo(value);
		if (this.content == value) {
			return true;
		}
		if (temp > 0) {
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

	/**
	 * Retrieve the biggest value of the binary tree.
	 * 
	 * @return Biggest value.
	 */
	public T getSmallest() {
		if (leftNode == null) {
			return content;
		}
		return leftNode.getSmallest();
	}

	/**
	 * Retrieve the biggest value of the binary tree.
	 * 
	 * @return Biggest value.
	 */
	public T getBiggest() {
		if (rightNode == null) {
			return content;
		}
		return rightNode.getBiggest();
	}

	/**
	 * Remove the specified value from the binary tree.
	 * 
	 * @param value Value to remove.
	 * @return Result of the operation.
	 */
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

	/**
	 * Remove all nodes which have null content.
	 */
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
		return leftNode == null && rightNode == null;
	}

	public int size() {
		return size;
	}

	public T getContent() {
		return content;
	}

	public RecursiveNode<T> getLeftNode() {
		return leftNode;
	}

	public RecursiveNode<T> getRightNode() {
		return rightNode;
	}
}
