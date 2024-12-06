package control;

import TAD_GRAFO_LA.GrafoLA;
import tadListaSimple.JeHaSimpleList;
import util.Arista;

public class ensayo {

	private static int[] a;

	public static void main(String[] args) {

		GrafoLA<String> grafoPrueba = new GrafoLA<String>(false);

		grafoPrueba.agregarArista("A", "D", 5);
		grafoPrueba.agregarArista("D", "A", 5);
		grafoPrueba.agregarArista("D", "F", 6);
		grafoPrueba.agregarArista("F", "D", 6);
		grafoPrueba.agregarArista("A", "B", 7);
		grafoPrueba.agregarArista("B", "A", 7);
		grafoPrueba.agregarArista("B", "E", 7);
		grafoPrueba.agregarArista("E", "B", 7);
		grafoPrueba.agregarArista("B", "C", 8);
		grafoPrueba.agregarArista("C", "B", 8);
		grafoPrueba.agregarArista("F", "G", 11);
		grafoPrueba.agregarArista("G", "F", 11);

		System.out.println("aristas " + grafoPrueba.cantidadAristas());
		System.out.println("vertices " + grafoPrueba.cantidadVertices());

		/*
		 * prueba el recorrido en amplitud
		 */
		System.out.println("--------------amplitud---------------");
		JeHaSimpleList<String> a = grafoPrueba.grafoEnAmplitud("A");
		if (a == null) {
			System.out.println("fail en amplitud");
		} else {
			for (int i = 0; i < a.getSize(); i++) {
				System.out.println(a.get(i));
			}
		}

	}

}
