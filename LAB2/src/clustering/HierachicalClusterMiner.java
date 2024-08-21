package src.clustering;

import src.data.Data;
import src.distance.ClusterDistance;
import src.eccezioni.InvalidDepthException;

public class HierachicalClusterMiner {
	
	private Dendrogram dendrogram;

	
	
	
	public HierachicalClusterMiner(int depth) {
		dendrogram= new Dendrogram(depth);
	
	}
	
	
	public String toString() {
		return dendrogram.toString();
	}
	
	public String toString(Data data) {
		return dendrogram.toString(data);
	}

	/**
	 * crea il livello base (livello 0) del dendrogramma che 
	 * contiene  l’istanze  di  ClusterSet  che  rappresenta  ogni  esempio  in  un  cluster 
	 * separato; per tutti i livelli successivi del dentrogramma (level>=1 e level < 
	 * dendrogram.getDepth()) costruisce l’istanza di ClusterSet che realizza la fusione dei 
	 * due  cluster  più  vicini  nella  istanza  del  ClusterSet  memorizzata  al  livello  level-1  del 
	 * dendrogramma  (usare  mergeClosestClusters  di  ClusterSet); memorizza l’istanza di 
	 * ClusterSet ottenuta per fusione nel livello level del dendrogramma.
	 * @param data Esempi su cui lavorare
	 * @param distance Algoritmo di distanza tra cluster con cui lavorare
	 */
	public void mine(Data data, ClusterDistance distance) {
		
		try {
			if(dendrogram.getDepth() > data.getNumberOfExample()) {
				throw new InvalidDepthException("profondità errata: "+dendrogram.getDepth()+" > "+data.getNumberOfExample());
			}
	
			ClusterSet cSet = new ClusterSet(data.getNumberOfExample());
			for(int i=0; i<data.getNumberOfExample(); i++) {
				Cluster c = new Cluster();
				c.addData(i);
				cSet.add(c);
			}
			dendrogram.setClusterSet(cSet, 0);
			for(int i=1; i < dendrogram.getDepth(); i++) {
				cSet = cSet.mergeClosestClusters(distance, data);
				dendrogram.setClusterSet(cSet,i);
			}
		} catch (InvalidDepthException e) {
			System.out.println(e.getMessage());
			System.out.println("Ricalcolo il dendrogramma col numero massimo di livelli possibili:");
			dendrogram = new Dendrogram(data.getNumberOfExample());
			this.mine(data, distance);
		}
	}

	
}
