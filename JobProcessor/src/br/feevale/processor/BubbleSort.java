package br.feevale.processor;

import java.util.List;

public class BubbleSort {
	
	private List<Integer> listaOrdenada;
	private boolean ordenado;
	
	public static BubbleSort build() {
		return new BubbleSort();
	}
	
	public void execute (List<Integer> inteiros) {
		ordenado = true;
		for (int i = inteiros.size(); i >= 1; i--) {
			for (int j = 1; j < i; j++) {
				if (inteiros.get(j - 1) > inteiros.get(j)) {
					int aux = inteiros.get(j);
					inteiros.set(j, inteiros.get(j - 1));
					inteiros.set(j -1, aux);
					ordenado = false;
				} 
			}
		}
		
		listaOrdenada = inteiros;
	}

	public boolean isOrdenado() {
		return ordenado;
	}
	
	public List<Integer> getListaOrdenada () {
		return listaOrdenada;
	}

}
