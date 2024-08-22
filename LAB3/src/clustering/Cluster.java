package src.clustering;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import src.data.Data;

public class Cluster implements Iterable<Integer>, Cloneable{	

	private Set<Integer> clusteredData = new TreeSet<>();

    /**
     * Implementazione del metodo virtuale iterator presente nell'interfaccia Iterable
     * @return Iteratore per la Collection di tipo TreeSet clusteredData
     */
	public Iterator<Integer> iterator() {
		return clusteredData.iterator();
	}
	
	/**
	 * Aggiunge un indice al Cluster
	 * @param id indice da aggiungere
	 */
	public void addData(int id) {
		clusteredData.add(id);
	}
		
	/**
	 * Restituisce la dimensione del cluster
	 * @return dimensione del cluster
	 */
	public int getSize() {
		return clusteredData.size();
	}
	
	/**
	 * Implementazione del metodo clone della classe {@code Object}
	 * @return Oggetto clonato
	 * @throws CloneNotSupportedException Se l'oggetto non supporta l'interfaccia Clonable
	 */
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("Errore: L'oggetto non può essere clonato, restituzione di un oggetto nullo");
		}
		return o;
	}
	
	/**
	 * crea un nuovo cluster che è la fusione dei due cluster pre-esistenti
	 * @param c Cluster da fondere col cluster {@code this}
	 * @return Cluster risultante dalla fusione di {@code c & this}
	 */
	public Cluster mergeCluster (Cluster c)
	{
		Cluster newCluster = (Cluster) this.clone();
		Iterator<Integer> i = c.iterator();
		while(i.hasNext()) {
			newCluster.clusteredData.add(i.next());
		}
		return newCluster;
	}
	
	
	public String toString() {		
		String str="";
		Iterator<Integer> i = this.iterator();

		while(i.hasNext()) {
			str+=i.next();
			if(i.hasNext()) {
				str+=",";
			}
		}

		return str;	
	}
	
	public String toString(Data data){
		String str="";
		
		for(Integer id : clusteredData)
			str+="<"+data.getExample(id)+">";				
		
		return str;
		
	}
	


}
