package src.distance;

import java.util.Iterator;
import src.clustering.Cluster;
import src.data.*;

public class AverageLinkDistance implements ClusterDistance {
    public double distance(Cluster c1, Cluster c2, Data d) {

        double average = 0;

        Iterator<Integer> i1 = c1.iterator();
        Iterator<Integer> i2 = c2.iterator();

        while(i1.hasNext()) {

            Example e1 = d.getExample(i1.next());

            while(i2.hasNext()) {

                average += e1.distance(d.getExample(i2.next()));

            }
        }
        return (average/(c1.getSize()*c2.getSize()));
    }
}
