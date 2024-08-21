package src.eccezioni;

/**
 * Eccezione lanciata in caso si provi a unire i cluster più vicini di un ClusterSet contenente un solo
 * Cluster
 */
public class ImpossibleClusterMerge extends Exception{
    public ImpossibleClusterMerge() {}

    public ImpossibleClusterMerge(String msg) {
        super(msg);
    }
}
