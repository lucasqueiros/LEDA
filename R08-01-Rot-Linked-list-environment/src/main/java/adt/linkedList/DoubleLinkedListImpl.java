package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	
	protected DoubleLinkedListNode<T> head;

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode newNode = new DoubleLinkedListNode<T>(element, null , null);
		
		if (this.isEmpty()) {
			this.head = newNode;
			this.last = newNode; 
		} else {
			newNode.setNext(this.getHead());
			newNode.setPrevious(new DoubleLinkedListNode<>());
			this.setHead(newNode);
		}
	}

	@Override
	public void removeFirst() {
		if (this.size() == 1) {
			this.last = new DoubleLinkedListNode<>();
			this.head = this.last;
		} else {
			this.head = (DoubleLinkedListNode) head.getNext();
			this.head.setPrevious(new DoubleLinkedListNode<>());	
		}

	}

	@Override
	public void removeLast() {
		DoubleLinkedListNode aux = this.head;

		if (this.size() == 1) {
			this.removeFirst();
		} else {
			this.last = this.getLast().getPrevious();
			this.getLast().setNext(new DoubleLinkedListNode<>());
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
