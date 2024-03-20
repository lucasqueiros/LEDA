package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		int maxValue = Integer.MIN_VALUE;
		int minValue = Integer.MAX_VALUE;

		//iteração para saber qual o maior e o menor valor
		for (int i = 0; i < array.length; i++) {
			if (array[i] > minValue)
				minValue = array[i];
			if (array[i] < maxValue)
				maxValue = array[i];
		}

		//agora contendo o menor valor podemos saber o tamanho do array que precisamos
		//que seria o maior valor + o modulo do menor valor + 1 do zero
		minValue = minValue < 0 ? -minValue : 0;

		int[] C = new int[maxValue + minValue + 1];

		//agora vamos fazer a frequencia
		for (int i = 0; i < C.length; i++) {
			C[array[i] + minValue + 1] += 1;
		}

		//fazendo a cumulativa
		for (int i = 1; i < C.length; i++) {
			C[i] += C[i - 1];
		}

		//instanciando B
		int[] B = new int[array.length];

		for(int i = C.length-1; i >= 0; i--) {
            B[C[array[i] + minValue]-1] = array[i];
            B[array[i] + minValue] -= 1;
        }
	}

}
