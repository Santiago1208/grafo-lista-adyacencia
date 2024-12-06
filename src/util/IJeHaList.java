package util;

public interface IJeHaList<T> {

	public void setFirst(T value);
	
	public boolean add(T value);

	public boolean isEmpty();
	
	public int getSize();
	
	public boolean delete(T value);
	
	public int getPosition(T value);
	
	public boolean rewrite(T value, T value1);
	
	public void printElements();
	
	public void clear();
	
	
}
