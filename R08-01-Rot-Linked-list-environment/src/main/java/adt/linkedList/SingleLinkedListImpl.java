package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int cont = 0;

		if (!this.isEmpty()){
			SingleLinkedListNode<T> aux = getHead();

			while (!aux.isNIL()) {
				aux = aux.getNext();
				cont++;
			}
		}

		return cont;
	}

	@Override
	public T search(T element) {
		T search = null;

		if (!this.isEmpty()) {
			SingleLinkedListNode<T> aux = this.getHead();

			while (!aux.isNIL()) {
				if (aux.getData().equals(element)) return aux.getData();
				aux = aux.getNext();
			}
		}

		return search;
	}

	@Override
	public void insert(T element) {

		if(element != null) {
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T> (element, new SingleLinkedListNode<>());
			SingleLinkedListNode<T> aux = this.getHead();

			if (this.isEmpty()) {
				this.setHead(newNode);
			} else {
				while (!aux.getNext().isNIL()) {
					aux = aux.getNext();
				}

				aux.setNext(newNode);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty() && this.search(element) != null) {

			if (this.size() == 1) {
				this.setHead(new SingleLinkedListNode<>());
			} else {
				SingleLinkedListNode aux = this.getHead();

				while (!aux.getData().equals(element)) {
					aux = aux.getNext();
				}

				aux.setNext(aux.getNext().getNext());
			}
		}
	}

	@Override
	public T[] toArray() {
		Object[] tempArray = new Object[this.size() - 1];
		T[] array = (T[]) tempArray;

		SingleLinkedListNode aux = this.head;
		int cont = 0;

		while (!aux.isNIL()) {
			array[cont] = (T) aux.getData();
			cont++;
			aux = aux.getNext();
		}

		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
