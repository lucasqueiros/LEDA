package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return data == null;
	}

	@Override
	public int size() {
		return size(0);
	}

	private int size(int sum) {
		int result = sum;

		if (this.data == null) result = 0;
		else {
			result = 1 + this.getNext().size(sum);
		}
		return result;
	}

	@Override
	public T search(T element) {
		T result = null;

		if (!this.isEmpty() && element != null) {
			if (this.data.equals(element)) result = element;
			else {
				result = this.next.search(element);
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if(this.isEmpty()){
			this.setData(element);
			this.setNext(new RecursiveSingleLinkedListImpl<>());
		} else {
			this.getNext().insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if(element!=null && !this.isEmpty()){
			if(this.getData().equals(element)){
				if(this.getNext().isEmpty()){
					this.setData(null);
					this.setNext(this.getNext().getNext());
				} else{
					this.setData(this.getNext().getData());
					this.setNext(this.getNext().getNext());
				}
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[this.size()];
		toArray(result, 0);
		return result;
	}

	private void toArray(T[] array, int index) {
		if (this.data != null) {
			array[index] = this.data;
			toArray(array, index++);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
