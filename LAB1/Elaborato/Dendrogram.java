package Elaborato;

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
        if(level > tree.length) {
            System.out.println("Errore nella scrittura del dendrogramma");
        } else {
            tree[level] = c;
        }
    }

    /**
     * restituisce il ClusterSet del dendrogramma ad un determinato livello
     * @param level livello del dendrogramma
     * @return ClusterSet al livello level
     */
    ClusterSet getClusterSet(int level) {
        return tree[level];
    }
}
