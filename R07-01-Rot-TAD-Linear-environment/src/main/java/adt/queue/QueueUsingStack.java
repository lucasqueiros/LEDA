package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}

		try {
			stack1.push(element);
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}

		T result = null;

		stackToAnother(stack1, stack2);
		try {
			result = stack2.pop();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
		stackToAnother(stack2, stack1);
		
		return result;
	}

	@Override
	public T head() {
		T result = null;

		stackToAnother(stack1, stack2);
		result = stack2.top();
		stackToAnother(stack2, stack1);

		return result;
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}

	private void stackToAnother(Stack<T> source, Stack<T> target) {
		try {
			while (!source.isEmpty()) {
				target.push(source.pop());
			}

		} catch (StackOverflowException e) {
			e.printStackTrace();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
	}


}
