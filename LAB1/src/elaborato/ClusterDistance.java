package src.elaborato;

import src.clustering.Cluster;

public interface ClusterDistance {
		double distance(Cluster c1, Cluster c2, Data d);
}
