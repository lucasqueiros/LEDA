package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BSTNode<T> node) {
		int height = -1;

		if (!node.isEmpty()) {
			height = 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
		return height;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		BSTNode<T> aux = node;

		if (!node.isEmpty() && !node.getData().equals(element)) {
			if (element.compareTo(node.getData()) < 0) {
				aux = search(element, (BSTNode<T>) node.getLeft());	
			} else {
				aux = search(element, (BSTNode<T>) node.getRight());
			}
		}

		return aux;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(element, root);
		}
	}

	private void insert(T element, BSTNode<T> node){
		if(node.isEmpty()) {
			node.setData(element);
			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);
			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);
		} else {
			if (element.compareTo(node.getData()) > 0) {
				insert(element, (BSTNode<T>) node.getRight());
			} else {
				insert(element, (BSTNode<T>) node.getLeft());
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (!isEmpty()) return maximum(this.root);
		return null;
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if (!isEmpty()) return minimum(this.root);
		return null;
	}

	private BSTNode<T> minimum (BSTNode<T> node) {
		if(node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		BSTNode<T> aux = null;

		if (element != null && !node.isEmpty() && !node.getRight().isEmpty()) {
			aux = minimum((BSTNode<T>) node.getRight());
		} else {
			aux = sucessor(element, (BSTNode<T>) node.getParent());
		}
		return aux;
	}

	private BSTNode<T> sucessor(T element, BSTNode<T> node) {
		BSTNode<T> aux = null;

		if (node == null || node.getData().compareTo(element) > 0) {
			aux = node;
		} else {
			aux = predecessor(element, (BSTNode<T>) node.getParent());
		}
		return aux;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		BSTNode<T> aux = null;

		if (element != null && !node.isEmpty()) {
			if (!node.getLeft().isEmpty()) {
				aux = maximum((BSTNode<T>) node.getLeft());
			} else {
				aux = predecessor(element, (BSTNode<T>) node.getParent());
			}
		}
		return aux;
	}

	private BSTNode<T> predecessor(T element, BSTNode<T> node) {
		BSTNode<T> aux = null;
		
		if (node == null || node.getData().compareTo(element) < 0) {
			aux = node;
		} else {
			aux = predecessor(element, (BSTNode<T>) node.getParent());
		}
		return aux;
	}


	@Override
	public void remove (T element) {
		if (element != null) {
			BSTNode<T> node = this.search(element);

			if (!node.isEmpty()) {
				if (node.isLeaf()) { // Caso 1: nó a ser removido é folha
					node.setData(null);
					node.setLeft(null);
					node.setRight(null);
				} else if (node.getRight().isEmpty() || node.getLeft().isEmpty()) { // Caso 2: nó a ser removido tem um filho
					BSTNode<T> childNode = node.getRight().isEmpty() ? (BSTNode<T>) node.getLeft() : (BSTNode<T>) node.getRight();
					if (this.root.equals(node)) {
						this.root = childNode;
						this.root.setParent(null);
					}
					else {
						childNode.setParent(node.getParent());
						if (node.getParent().getLeft().equals(node))
							node.getParent().setLeft(childNode);
						else
							node.getParent().setRight(childNode);
					}
				} else { // Caso 3: nó a ser removido tem dois filhos
					T sucessor = this.sucessor(node.getData()).getData();
					this.remove(sucessor);
					node.setData(sucessor);
				}
			}
		}
	}

	@Override
	public T[] preOrder() {
		ArrayList<T> arr = new ArrayList<>();

		preOrder(arr, this.root);

		return (T[]) arr.toArray(new Comparable[this.size()]);
	}

	private void preOrder(ArrayList<T> arr, BSTNode<T> node) {
		if (!node.isEmpty()) {
			arr.add(node.getData());
			preOrder(arr, (BSTNode<T>) node.getLeft());
			preOrder(arr, (BSTNode<T>) node.getRight());
		}
	}


	@Override
	public T[] order() {
		ArrayList<T> arr = new ArrayList<>();
		order(arr, this.root);

		return (T[]) arr.toArray(new Comparable[this.size()]);
	}

	private void order(ArrayList<T> arr, BSTNode<T> node) {
		if (!node.isEmpty()) {
			order(arr, (BSTNode<T>) node.getLeft());
			arr.add(node.getData());
			order(arr, (BSTNode<T>) node.getRight());
		}
	}

	@Override
	public T[] postOrder() {
		ArrayList<T> arr = new ArrayList<>();
		postOrder(arr, this.root);

		return (T[]) arr.toArray(new Comparable[this.size()]);
	}

	private void postOrder(ArrayList<T> arr, BSTNode<T> node) {
		if (!node.isEmpty()) {
			postOrder(arr, (BSTNode<T>) node.getLeft());
			postOrder(arr, (BSTNode<T>) node.getRight());
			arr.add(node.getData());
		}
	}
	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
