package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if (leftIndex < 0 || rightIndex > array.length - 1 || leftIndex > rightIndex) return;		

		for (int i = leftIndex + 1; i <= rightIndex; i++) {											//loop externo que replica o algoritmo para todo o array

			int j = i;																				

			while (j > 0 && array[j].compareTo(array[j - 1]) < 0) {									//loop interno que realiza inserção ordenada
				Util.util.swap(array, j, j - 1);
				j--;
			}

		}
	}
}
