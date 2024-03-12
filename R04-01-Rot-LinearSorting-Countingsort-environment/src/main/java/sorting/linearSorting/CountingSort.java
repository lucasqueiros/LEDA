package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		int maxValue = Integer.MIN_VALUE;

		for (int i = 0; i < array.length; i++) {
			if (array[i] > maxValue) {
				maxValue = array[i];
			}
		}
		//instancia de C com tamanho = ao valor maximo, pois temos que ter uma posição para o 0;
		int[] C = new int[maxValue];

		//Contando a frequencia de array em C
		//nesse caso cada valor v vai para C[v], nao subtraimos um devido a possivel ocorrencia do 0;
		for (int i = 0; i < array.length; i++) {
			C[array[i]] += 1;
		}

		//soma cumulativa
		for(int i = 1; i < array.length; i++) {
			C[i] += C[i - 1];
		}

		//instanciar B
		int[] B = new int[array.length];

		//colocar os valores de array de acordo com C em B
		for(int i = 0; i < C.length; i++) {
			B[C[array[i]]] = array[i];
			C[i]--;
		}

	}
}
