package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {

			int middle = (leftIndex - rightIndex) / 2;
			sort(array, leftIndex, middle);
			sort(array, middle + 1, rightIndex);

			merge(array, leftIndex, rightIndex, middle);
		}
	}

	public void merge(T[] array, int leftIndex; int rightIndex, int middle) {

		int[] helper = new int[array.length];
		for (int i = leftIndex; i <= rightIndex; i++) {
			helper[i] = array[i];
		}

		int i = left;
		int j = middle;
		int k = left;

		while (i<= middle && j <= right) {
			if (helper[i] < helper[j]) {
				array[k] = helper[i];
				i++;
			} else {
				array[k] = helper[j];
				j++;
			}
		}
		k++;

		while(i <= middle) {
			array[k] = helper[i];
			i++;
			k++;
		}

	}
}
