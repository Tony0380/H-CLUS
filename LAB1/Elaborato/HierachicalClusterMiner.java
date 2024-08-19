package Elaborato;


class HierachicalClusterMiner {
	
	private Dendrogram dendrogram;

	
	
	
	HierachicalClusterMiner(int depth) {
		dendrogram= new Dendrogram(depth);
	
	}
	
	
	public String toString() {
		return dendrogram.toString();
	}
	
	String toString(Data data) {
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
	void mine(Data data, ClusterDistance distance) {
		ClusterSet cSet = new ClusterSet(data.getNumberOfExample());
		for(int i=0; i<data.getNumberOfExample(); i++) {
			Cluster c = new Cluster();
			c.addData(i);
			cSet.add(c);
		}
		dendrogram.setClusterSet(cSet, 0);
		for(int i=1; i < dendrogram.getDepth(); i++) {
			dendrogram.setClusterSet(cSet.mergeClosestClusters(distance, data),i);
		}
	}

	
}
