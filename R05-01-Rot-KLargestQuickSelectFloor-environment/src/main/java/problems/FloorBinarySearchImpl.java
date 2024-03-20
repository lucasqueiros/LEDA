package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		Integer floor = null;
		
		if(array.length > 0) {
			quickSort(array, 0, array.length - 1);
			floor = search(array, x, 0, array.length - 1);
		}

		return floor;
	}


	private Integer search(Integer[] array, Integer x, int leftIndex, int rightIndex) {
		Integer answer = null;

		if (leftIndex <= rightIndex) {
			int middle = (rightIndex + leftIndex) / 2;
			answer = array[middle];

			if (answer == x) {
				answer = array[middle];
			} else if (answer > x) {
				answer = search(array, x, leftIndex, middle - 1);
			} else {
				answer = search(array, x, middle + 1, rightIndex);
			}
		} else if (rightIndex != -1) {
			answer = array[rightIndex];
		}

		return answer;
	}

	private void quickSort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 0 && leftIndex >= 0 && rightIndex <= array.length - 1) {
			if (leftIndex < rightIndex) {
				int pivotIndex = partition(array, leftIndex, rightIndex);
				quickSort(array, leftIndex, pivotIndex - 1);
				quickSort(array, pivotIndex + 1, rightIndex);
			}
		}
	}

	private int partition(Integer[] array, int leftIndex, int rightIndex) {
		int pivotIndex =  leftIndex;
		int numTrocas = leftIndex;

		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i].compareTo(array[pivotIndex]) < 0) {
				Util.swap(array, numTrocas, i);
				i++;
			}
		}
		Util.swap(array, leftIndex, numTrocas);
		return numTrocas;
	}
}
