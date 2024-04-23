package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insertFirst(T element) {
		if(element!= null){
			if(this.isEmpty()){
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<>());
				((RecursiveDoubleLinkedListImpl<T>)this.getNext()).setPrevious(this);
				this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
			} else {
				RecursiveDoubleLinkedListImpl<T> list = new RecursiveDoubleLinkedListImpl<>();
				list.setData(this.getData());
				list.setPrevious(this);
				list.setNext(this.getNext());

				this.setNext(list);
				this.setData(element);

				((RecursiveDoubleLinkedListImpl<T>)list.getNext()).setPrevious(list);

			}

		}
	}

	@Override
	public void removeFirst() {
		if(!isEmpty()){
			if(this.getNext().isEmpty()){
				this.setData(null);
				this.setNext(new RecursiveDoubleLinkedListImpl<>());
			} else {
			this.setData(this.getNext().getData());
			this.setNext(this.getNext().getNext());
			}
		}
	}

	@Override
	public void removeLast() {
		if(!isEmpty()){
			if(this.getNext().isEmpty()){
				this.setData(null);
				this.setNext(null);
			} else {
				((RecursiveDoubleLinkedListImpl) this.getNext()).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
