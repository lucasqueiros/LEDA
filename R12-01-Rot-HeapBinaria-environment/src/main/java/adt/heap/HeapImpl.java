package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o menor sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 2 < 3),
 * essa heap deixa os elementos menores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap. OU seja, voce deve considerar que a heap usa o comparator
	 * interno e se o comparator responde compare(x,y) < 0 entao o x eh menor
	 * e sobe na heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[])resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve usar o comparator
	 * para subir os elementos na heap.
	 */
	private void heapify(int position) {
		if (position >= 0 && position <= index) {
			int left = left(position);
			int right = right(position);

			int maxIndex = position;

			if (left <= this.index && this.comparator.compare(this.heap[left], this.heap[position]) > 0) {
				maxIndex = left;
			}
			if (right <= this.index && this.comparator.compare(this.heap[right], this.heap[maxIndex]) > 0) {
				maxIndex = right;
			}
		
			if (maxIndex != position) {
				Util.swap(heap, position, maxIndex);
				this.heapify(maxIndex);
			}
		}
	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}

		this.heap[++this.index] = element;

		int i = this.index;
		while (i > 0 && this.comparator.compare(this.heap[parent(i)], this.heap[i]) < 0) {
			Util.swap(heap, i, parent(i));
			i = parent(i);
		}
	}

	@Override
	public void buildHeap(T[] array) {
		this.heap = array.clone();
		this.index = array.length - 1;
		for (int i = array.length - 1 / 2; i >= 0; i--) {
			heapify(i);
		}
	}

	@Override
	public T extractRootElement() {
		T elem = this.rootElement();

		if (!isEmpty()) {
			this.heap[0] = this.heap[index];
			this.index--;
	
			this.heapify(0);
		}

		return elem;
	}

	@Override
	public T rootElement() {
		T result = null;

		if (!isEmpty()) {
			result = this.heap[0];
		}

		return result;
	}

	@Override
	public T[] heapsort(T[] array) {
		if (array != null) {
			buildHeap(array);
			if (this.rootElement().compareTo(this.heap[index]) > 0) {
				for (int i = array.length - 1; i >= 0; i--) {
					array[i] = extractRootElement();
				}
				this.heap = array.clone();
			} 
			else {
				for (int i = 0; i < array.length; i++) {
					array[i] = extractRootElement();
				}
				this.heap = array.clone();
			}
		}

		return heap;
	}

	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
