package tadCola;

import util.NodeSimple;
import util.QueueInterface;

public class Queue<T> implements QueueInterface<T>{

	private NodeSimple<T> front;
	private NodeSimple<T> back;

	public Queue(){
		front = null;
		back = null;
	}

	@Override
	public boolean isEmpty() {
		return front == null;
	}

	@Override
	public void enqueue(T e) {
		NodeSimple<T> nuevo = new NodeSimple<T>(e);
		if(isEmpty()){
			front = back = nuevo;
		} else {
			back.setNext(nuevo);
			back = nuevo;
		}		
	}


	@Override
	public void dequeue() {
		if (!isEmpty()) {
			NodeSimple<T> temp = front.getNext();
			front = temp;
		}
	}

	@Override
	public T getFront() {
		T value = null; 
		if (!isEmpty()) {
			value = front.getValue();
		}
		return value;
	}

	@Override
	public void clear() {
		front = null;
		back = null;
	}

}
