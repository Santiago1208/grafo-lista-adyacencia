package tadListaSimple;

import util.*;

public class JeHaSimpleList<T> implements IJeHaList<T> {
	private int size;
	private NodeSimple<T> first;

	/**
	 * Crea la JeHaSimpleList agregando un objeto en la primera posicion
	 * 
	 * @param object
	 */

	/**
	 * 
	 */
	public JeHaSimpleList() {
		first = null;
		size = 0;
	}

	public JeHaSimpleList(T object) {
		if (first == null) {
			first = new NodeSimple<T>(object);
			size++;
		}
	}

	public T get(int position) {

		if (position >= size)
			throw new ArrayIndexOutOfBoundsException();

		NodeSimple<T> node = first;
		int counter = 0;
		while (node != null) {
			if (counter == position)
				return node.getValue();
			node = (NodeSimple<T>) node.getNext();
			counter++;
		}
		return null;
	}

	/**
	 * agrega un objeto a la JeHaSimpleList en la ultima posicion
	 */
	public boolean add(T object) {
		boolean returN = false;
		NodeSimple<T> ultimo = findLast();
		if (first == null) {
			first = new NodeSimple<T>(object);

		} else if (ultimo != null) {
			NodeSimple<T> nuevoUltimo = new NodeSimple<T>(object);
			ultimo.setNext(nuevoUltimo);
			returN = true;
		}
		size++;
		return returN;
	}

	/**
	 * retorna un false si la lista esta vacia
	 */
	public boolean isEmpty() {
		return (first == null) ? true : false;
	}

	/**
	 * retorna el primer elemento de la lista
	 * 
	 * @return
	 */
	public NodeSimple<T> getFirst() {
		return first;
	}

	/**
	 * establece o cambie el primer elemento de la lista, conservando el orden
	 * de la estructura
	 * 
	 * @param first
	 */

	public void setFirst(T value) {
		if (first != null) {
			first.setValue(value);
		}

	}

	public int getSize() {
		return size;
	}

	public NodeSimple<T> search(T value) {
		NodeSimple<T> findit = first;
		NodeSimple<T> retorno = null;
		boolean booolean = false;
		while (findit != null && !booolean) {
			if (findit.getValue().equals(value)) {
				booolean = true;
			}
			retorno = findit;
			findit = findit.getNext();
		}
		return (booolean) ? retorno : null;
	}

	public NodeSimple<T> findLast() {
		NodeSimple<T> last = first;
		while (last != null && last.getNext() != null) {
			last = last.getNext();
		}
		return last;
	}

	public boolean delete(T value) {
		boolean booolean = false;
		NodeSimple<T> element = search(value);
		NodeSimple<T> previous = getPreviousTo(value);
		if (previous == null) {
			first = null;

		} else if (element != null && previous != null) {
			previous.setNext(element.getNext());
			booolean = true;

		}
		size--;
		return booolean;
	}

	public NodeSimple<T> getPreviousTo(T element) {
		NodeSimple<T> object = first;
		NodeSimple<T> previousToObject = null;
		NodeSimple<T> returnN = null;
		while (object != null) {
			if (object.getValue().equals(element)) {
				returnN = previousToObject;
			}
			previousToObject = object;
			object = object.getNext();
		}
		return returnN;
	}

	/**
	 * retorna un entero con la posicion del nodo que contiene el valor "value"
	 */
	public int getPosition(T value) {
		int cont = 0;
		NodeSimple<T> nodo = first;
		boolean encontrado = false;
		while (nodo != null && !encontrado) {
			if ((nodo.getValue().equals(value))) {
				encontrado = true;
			}
			cont++;
			nodo = nodo.getNext();
		}
		return (!encontrado) ? -1 : cont;
	}

	@Override
	public boolean rewrite(T value, T value1) {
		boolean booolean = false;
		NodeSimple<T> element = search(value);

		if (element != null) {
			element.setValue(value1);
			booolean = true;
		}
		return booolean;
	}

	@Override
	public void printElements() {
		NodeSimple<T> element = first;
		while (element != null) {
			System.out.println(element.getValue());
			element = element.getNext();
		}
	}

	@Override
	public void clear() {
		first = null;
	}
}
