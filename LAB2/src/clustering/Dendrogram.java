package src.clustering;

import src.data.Data;

public class Dendrogram {
    /**Rappresentazione dei livelli del dendrogramma */
    private ClusterSet tree[];

    /**
     * crea un vettore di dimensione depth con cui inizializza tree.
     * @param depth profondità del dendrogramma
     */
    Dendrogram(int depth) {
        tree = new ClusterSet[depth];
    }

    /**
     * memorizza c nella posizione level di tree
     * @param c nuovo ClusterSet da memorizzare
     * @param level livello del dendrogramma dove memorizzare il ClusterSet
     */
    void setClusterSet(ClusterSet c, int level) {
        tree[level] = c;
    }

    /**
     * restituisce il ClusterSet del dendrogramma ad un determinato livello
     * @param level livello del dendrogramma
     * @return ClusterSet al livello level
     */
    ClusterSet getClusterSet(int level) {
        return tree[level];
    }

    /**
     * restituisce la profondità del dendrogramma
     * @return profondità del dendrogramma
     */
    int getDepth() {
        return tree.length;
    }

    public String toString() {
        String v="";
        for (int i=0;i<tree.length;i++)
            v+=("level"+i+":\n"+tree[i]+"\n");
        return v;
    }

    String toString(Data data) {
        String v="";
        for (int i=0;i<tree.length;i++)
            v+=("level"+i+":\n"+tree[i].toString(data)+"\n");
        return v;
    }
}
