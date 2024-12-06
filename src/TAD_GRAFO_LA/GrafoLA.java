package TAD_GRAFO_LA;

import java.awt.List;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import tadListaSimple.JeHaSimpleList;
import util.*;

public class GrafoLA<T extends Comparable> implements IGrafo<T> {

	public final static int MAX_VERTICES = 200;
	public final static boolean DIRIGIDO = true;
	public final static boolean NO_DIRIGIDO = false;
	private int numVertices;
	private int numAristas;
	private boolean dirigido;
	private T[] vertices;
	private JeHaSimpleList<Arista<T>> listaAristas;

	public GrafoLA(boolean dirigido) {
		vertices = (T[]) new Comparable[MAX_VERTICES];
		this.dirigido = dirigido;
		numAristas = 0;
		numVertices = 0;

		listaAristas = new JeHaSimpleList<Arista<T>>();
	}

	public int cantidadVertices() {
		return numVertices;
	}

	public int cantidadAristas() {
		return numAristas;
	}

	public void agregarVertice(int pos, T elemento) {
		vertices[pos] = elemento;
	}

	@Override
	public int verticeEnElGrafo(T vertice) {
		int retorno = -1;
		for (int i = 0; i < numVertices; i++) {
			if (vertice.compareTo(vertices[i]) == 0) {
				retorno = i;
			}
		}
		return retorno;
	}

	@Override
	public void agregarArista(T inicio, T fin, double peso) {
		if (!formanArista(inicio, fin)) {
			if (dirigido) {
				agregarAristaEnGrafoD(inicio, fin, peso);
			} else {
				agregarAristaEnGrafoND(inicio, fin, peso);
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void agregarAristaEnGrafoND(T verticeInicio, T verticeFin,
			double peso) {
		/*
		 * agrega los vertices al conjunto de vertices del grafo, verificando su
		 * existencia (verticeEnElGrafo retorna -1 si el vertice no esta en el
		 * grafo) Luego re asigna los valores a los cuales fueron agregados,
		 * para modificar la matriz de adyacencias
		 */
		int verticesInicioIs = verticeEnElGrafo(verticeInicio);
		int verticeFinIs = verticeEnElGrafo(verticeFin);

		if (verticesInicioIs == -1 && verticeFinIs == -1) {

			agregarVertice(numVertices++, verticeInicio);
			agregarVertice(numVertices++, verticeFin);
			verticesInicioIs = verticeEnElGrafo(verticeInicio);
			verticeFinIs = verticeEnElGrafo(verticeFin);

			System.out.println("ningun vertice estaba " + verticesInicioIs
					+ " @ " + verticeFinIs + " @ " + numVertices);

		} else if (verticesInicioIs == -1 && verticeFinIs != -1) {

			agregarVertice(numVertices, verticeInicio);
			verticesInicioIs = numVertices;
			numVertices++;

			System.out.println("solo el de fin estaba");
		} else if (verticesInicioIs != -1 && verticeFinIs == -1) {

			agregarVertice(numVertices, verticeFin);
			verticeFinIs = numVertices;
			numVertices++;

			System.out.println("solo el de inicio estaba");
		}
		listaAristas.add(new Arista<T>(verticeInicio, verticeFin, peso));
		numAristas += 2;
	}

	@Override
	public boolean formanArista(T inic, T fin) {
		boolean encontrado = false;
		if (listaAristas.getSize() > 0) {
			if (dirigido == true) {
				for (int i = 0; i < listaAristas.getSize() && !encontrado; i++) {
					Arista<T> a = listaAristas.get(i);
					if (a.darInicio().equals(inic) && a.darFin().equals(fin))
						encontrado = true;
				}
			} else {
				for (int i = 0; i < listaAristas.getSize() && !encontrado; i++) {
					Arista<T> a = listaAristas.get(i);
					if (a.darInicio().equals(inic) && a.darFin().equals(fin)
							|| a.darInicio().equals(fin)
							&& a.darFin().equals(inic))
						encontrado = true;
				}
			}
		} else {
			encontrado = false;
		}
		return encontrado;
	}

	public boolean esDirigido() {
		return dirigido;
	}

	@Override
	public T[] listaConVertices() {
		return vertices;
	}

	@Override
	public JeHaSimpleList<Arista<T>> listaConAristas() {
		return listaAristas;
	}

	@Override
	public JeHaSimpleList<Arista<T>> aristasAdyacentes(T vertice) {
		JeHaSimpleList<Arista<T>> ady = new JeHaSimpleList<Arista<T>>();
		for (int i = 0; i < listaAristas.getSize(); i++) {
			Arista<T> a = listaAristas.get(i);
			if (a.darInicio().equals(vertice))
				ady.add(a);
		}
		return ady;

	}

	public double darPesoArista(T origen, T destino) {
		if (verticeEnElGrafo(origen) != -1 && verticeEnElGrafo(destino) != -1) {
			for (int i = 0; i < listaAristas.getSize(); i++) {
				Arista<T> a = listaAristas.get(i);
				if (a.darInicio().equals(origen) && a.darFin().equals(destino))
					return a.darPeso();
			}
		}
		return -1;
	}

	@Override
	public JeHaSimpleList<T> verticesAdyacentesA(T elemento) {
		JeHaSimpleList<T> ady = new JeHaSimpleList<T>();
		for (int i = 0; i < listaAristas.getSize(); i++) {
			Arista<T> a = listaAristas.get(i);
			if (a.darInicio().equals(elemento))
				ady.add(a.darFin());
		}
		return ady;
	}

	@Override
	public boolean caminoEntreVertices(T el1, T el2) {
		return false;
	}

	@Override
	public boolean esConexo() {
		return false;
	}

	@Override
	public boolean uniOFullConexo() {
		return false;
	}

	@Override
	public boolean grafoAciclico() {
		boolean retorno = true;
		for (int i = 0; i < listaAristas.getSize(); i++) {
			Arista<T> arista = listaAristas.get(i);
			if (arista.darInicio().compareTo(arista.darFin()) == 0)
				retorno = false;
		}
		return retorno;
	}

	@Override
	public JeHaSimpleList<T> grafoEnAmplitud(T inicio) {
		JeHaSimpleList<T> respuesta = new JeHaSimpleList<T>(), visitados = new JeHaSimpleList<T>();
		LinkedList<T> cola = new LinkedList<T>();
		cola.addLast(inicio);
		while (!cola.isEmpty()) {
			T elemento = cola.removeFirst();
			if (respuesta.search(elemento) == null) {
				visitados.add(elemento);
				respuesta.add(elemento);
				JeHaSimpleList<T> adyacentes = verticesAdyacentesA(elemento);
				if (!adyacentes.isEmpty()) {
					for (int j = 0; j < adyacentes.getSize(); j++) {
						cola.addLast(adyacentes.get(j));
						visitados.add(adyacentes.get(j));
					}
				}
				if (respuesta.getSize() == numVertices)
					break;
			}
		}
		return respuesta;
	}

	@Override
	public JeHaSimpleList grafoEnProfundidad(T inicio) {
		JeHaSimpleList<T> respuesta = new JeHaSimpleList<T>(), visitados = new JeHaSimpleList<T>();
		Stack<T> pila = new Stack<T>();
		pila.push(inicio);
		while (!pila.isEmpty()) {
			T elemento = pila.pop();
			if (respuesta.search(elemento) == null) {
				visitados.add(elemento);
				respuesta.add(elemento);
				JeHaSimpleList<T> adyacentes = verticesAdyacentesA(elemento);
				if (!adyacentes.isEmpty()) {
					for (int j = adyacentes.getSize() - 1; j >= 0; j--) {
						pila.push(adyacentes.get(j));
						visitados.add(adyacentes.get(j));
					}
				}
				if (respuesta.getSize() == numVertices)
					break;
			}
		}
		return respuesta;
	}

	private void agregarAristaEnGrafoD(T verticeInicio, T verticeFin,
			double peso) {
		int verticesInicioIs = verticeEnElGrafo(verticeInicio);
		int verticeFinIs = verticeEnElGrafo(verticeFin);

		if (verticesInicioIs == -1 && verticeFinIs == -1) {
			agregarVertice(numVertices++, verticeInicio);
			agregarVertice(numVertices++, verticeFin);
			verticesInicioIs = verticeEnElGrafo(verticeInicio);
			verticeFinIs = verticeEnElGrafo(verticeFin);

			System.out.println("ningun vertice estaba " + verticesInicioIs
					+ " @ " + verticeFinIs + " @ " + numVertices);

		} else if (verticesInicioIs == -1 && verticeFinIs != -1) {

			agregarVertice(numVertices, verticeInicio);
			verticesInicioIs = numVertices;
			numVertices++;

			System.out.println("solo el de fin estaba");
		} else if (verticesInicioIs != -1 && verticeFinIs == -1) {

			agregarVertice(numVertices, verticeFin);
			verticeFinIs = numVertices;
			numVertices++;

			System.out.println("solo el de inicio estaba");
		}
		listaAristas.add(new Arista<T>(verticeInicio, verticeFin, peso));
		numAristas++;
	}

	public void eliminarVertice(T vertice) {
		if (numVertices > 0) {
			try {
				int pos = verticeEnElGrafo(vertice);
				auxDelete(pos);
				numVertices--;
				JeHaSimpleList<Arista<T>> eliminar = new JeHaSimpleList<Arista<T>>();
				for (int i = 0; i < listaAristas.getSize(); i++) {
					Arista<T> a = listaAristas.get(i);
					if (a.darFin().equals(vertice)
							|| a.darInicio().equals(vertice)) {
						eliminar.add(a);
					}
				}
				for (int i = 0; i < eliminar.getSize(); i++) {
					Arista<T> a = eliminar.get(i);
					listaAristas.delete(a);
					numAristas--;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void auxDelete(int pos) {
		vertices[pos] = null;
	}

	public void eliminarArista(T ini, T fin) {
		Arista<T> eliminar = new Arista<T>(ini, fin, -1);
		if (dirigido == true) {
			for (int i = 0; i < listaAristas.getSize(); i++) {
				Arista<T> a = listaAristas.get(i);
				if (a.esIgual(eliminar))
					try {
						listaAristas.delete(a);
						numAristas--;
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		} else {
			for (int i = 0; i < listaAristas.getSize(); i++) {
				Arista<T> a = listaAristas.get(i);
				if (a.esIgual(eliminar) || a.darInicio().equals(fin)
						&& a.darFin().equals(ini))
					try {
						listaAristas.delete(a);
						numAristas--;
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}

	}

}
