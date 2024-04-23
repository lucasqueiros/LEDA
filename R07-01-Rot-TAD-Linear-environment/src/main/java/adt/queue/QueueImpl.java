package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		return this.array[0];
	}

	@Override
	public boolean isEmpty() {
		return (this.tail == -1);
	}

	@Override
	public boolean isFull() {
		return (this.tail == this.array.length - 1);
	}

	private void shiftLeft() {
		for (int i = 1; i < this.array.length; i++) {
			if (this.array[i] != null && this.array[i - 1] == null) {
				this.array[i - 1] = this.array[i];
				this.tail--;
			}
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) throw new QueueOverflowException();
		else {
			this.array[this.tail++] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) throw new QueueUnderflowException();
		else {
			T aux = this.array[this.tail];
			this.tail--;
			return aux;
		}
	}

}
