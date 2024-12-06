package util;

import tadListaSimple.JeHaSimpleList;

public interface IGrafo<T extends Comparable> {

	public void agregarArista(T el1, T el2, double w);

	public void agregarVertice(int pos, T vertice);

	public boolean formanArista(T inic, T fin);

	public int verticeEnElGrafo(T vertice);

	public int cantidadVertices();

	public int cantidadAristas();

	public boolean esDirigido();

	public T[] listaConVertices();

	public JeHaSimpleList<Arista<T>> listaConAristas();

	public JeHaSimpleList<T> verticesAdyacentesA(T elemento);

	public JeHaSimpleList<Arista<T>> aristasAdyacentes(T vertice);

	public boolean caminoEntreVertices(T el1, T el2);

	public boolean esConexo();

	public boolean uniOFullConexo();

	public boolean grafoAciclico();

	public JeHaSimpleList<T> grafoEnAmplitud(T inicio);

	public JeHaSimpleList<T> grafoEnProfundidad(T inicio);

}
