package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		if (leftIndex < 0 || rightIndex > array.length - 1 || leftIndex > rightIndex) return;

		while (leftIndex < rightIndex) {
			while (leftIndex < rightIndex) {
				boolean trocou = false;
				for (int j = leftIndex; j <= rightIndex - 1; j++) {
					if (array[j].compareTo(array[j + 1]) > 0) {
						Util.swap(array, j, j + 1);
						trocou = true;
					}
				}
				
				if (!trocou) return;
				rightIndex --;
			}
		}
	}
}
