package util;

public class NodeSimple<T> {

	private NodeSimple<T> next;
	private T value;

	public NodeSimple(T value) {
		this.value=value;
	}

	public NodeSimple(){
		this.value=null;
	}
	public NodeSimple<T> getNext() {
		return next;
	}

	public void setNext(NodeSimple<T> next) {
		this.next =next;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T valor) {
		this.value = valor;
	}

}
