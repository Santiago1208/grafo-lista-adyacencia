package util;

public class Arista<T> implements Comparable<Arista<T>>
{

	/*
	 * Atributo que define el peso de la arista
	 */
	private double peso;
	
	/*
	 * Vertice origen o inicio de la arista
	 */
	private T inicio;
	
	/*
	 * Vertice destino o fin de la arista
	 */
	private T fin;
	
	/**
	 * Metodo que crea una arista
	 * @param origen Vertice origen
	 * @param destino Vertice destino
	 * @param peso Peso de la arista
	 */
	public Arista(T origen, T destino, double peso){
		inicio=origen;
		fin=destino;
		this.peso=peso;
	}
	
	/**
	 * Metodo que modifica el vertice origen
	 * @param inicio nuevo vertice inicio
	 */
	public void cambiarInicio(T inicio){
		this.inicio=inicio;
	}
	
	/**
	 * Metodo que modifica el vertice final
	 * @param fin nuevo vertice final
	 */
	public void cambiarFin(T fin){
		this.fin=fin;
	}
	
	/**
	 * Metodo que retorna el vertice origen
	 * @return vertice inicio
	 */
	public T darInicio(){
		return inicio;
	}
	
	/**
	 * Metodo que retorna el vertice destino
	 * @return vertice final
	 */
	public T darFin(){
		return fin;
	}
	
	/**
	 * Metodo que retorna el peso de la arista
	 * @return peso de la arista
	 */
	public double darPeso(){
		return peso;
	}
	
	/**
	 * Metodo que indica si una arista es igual a otra
	 * @param otra la arista a comparar
	 * @return true si la arista es igual o tiene el mismo destino y origen
	 */
	public boolean esIgual ( Arista<T> otra)
	{
		return this.fin.equals(otra.fin) && this.inicio.equals(otra.inicio) && this.peso == otra.peso || this.fin.equals(otra.fin) && this.inicio.equals(otra.inicio);
	}

	/**
	 * Metodo que retorna la informacion de la arista
	 */
	public String toString()
	{
		return inicio + " , " + fin + " , " + peso;
	}


	@Override
	public int compareTo(Arista<T> arista)
	{
		if(this.darPeso() > arista.darPeso())
			
			return 1;
		
		else if(this.darPeso() < arista.darPeso())
			
			return -1;
		
		else
			
			return 0;
	}

}
