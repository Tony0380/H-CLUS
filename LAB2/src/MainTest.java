package src;

import src.clustering.HierachicalClusterMiner;
import src.data.Data;
import src.distance.*;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Data data =new Data();
		System.out.println(data);
		int k;
		System.out.print("Inserisci la profondita del dendrogramma da costruire: ");
		k = Keyboard.readInt();
		HierachicalClusterMiner clustering=new HierachicalClusterMiner(k);
		
		double [][] distancematrix=data.distance();
		System.out.println("Distance matrix:\n");
		for(int i=0;i<distancematrix.length;i++) {
			for(int j=0;j<distancematrix.length;j++)
				System.out.print(distancematrix[i][j]+"\t");
			System.out.println("");
		}

		System.out.println("Decidi che algoritmo di distanza utilizzare per calcolare il dendrogramma: ");
		System.out.println("1. Single Link Distance");
		System.out.println("2. Average Link Distance");
		System.out.print("Scelta: ");
		int choice = Keyboard.readInt();

		if(choice == 1) {
			System.out.println("Single link distance");
			ClusterDistance distance=new SingleLinkDistance();
			clustering.mine(data,distance);
			System.out.println(clustering);
			System.out.println(clustering.toString(data));
		} else if (choice == 2) {
			System.out.println("Average link distance");
			ClusterDistance distance=new AverageLinkDistance();
			clustering.mine(data,distance);
			System.out.println(clustering);
			System.out.println(clustering.toString(data));
		} else {
			System.out.println("Mi dispiace, le opzioni erano 1 e 2");
		}
		
		
		
	}

}
