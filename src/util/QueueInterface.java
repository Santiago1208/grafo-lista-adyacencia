package util;

public interface QueueInterface<T> {
	public boolean isEmpty();
	public void enqueue(T e);
	public void dequeue();
	public T getFront();
	public void clear();
	
}
