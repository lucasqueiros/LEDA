package sorting.simpleSorting;

import sorting.AbstractSorting;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (leftIndex < 0 || rightIndex > array.length - 1 || leftIndex > rightIndex) return;
								
		for (int i = leftIndex; i <= rightIndex; i++) {				//loop externo para repetir a seleção por todo o array

			int menorIndex = i;										//setamos o primeiro elemento como o menor

			for (int j = i + 1; j <= rightIndex; j++) {	//loop interno para varrer o array procurando o menor
				if (array[j].compareTo(array[menorIndex]) < 0) {	//condicional que verifica se o elemento analisado é menor que menorIndex
					menorIndex = j;									//elemento array[j] passa a ser o menor
				}
			}
			Util.util.swap(array, menorIndex, i);							//trocamos a posiçao de j para a posição de menor valor
		}
	}
}
