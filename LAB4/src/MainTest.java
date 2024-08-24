package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import src.clustering.HierachicalClusterMiner;
import src.data.Data;
import src.distance.*;
import src.exceptions.NoDataException;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Data data;
		try {
			data = new Data("exampletab");
		} catch (NoDataException e) {

			System.out.println("Tabella vuota, generazione di una collezione di esempi random.");
			data = new Data();
		}
		System.out.println(data);
		
		double [][] distancematrix=data.distance();
		System.out.println("Distance matrix:\n");
		for(int i=0;i<distancematrix.length;i++) {
			for(int j=0;j<distancematrix.length;j++)
				System.out.print(distancematrix[i][j]+"\t");
			System.out.println("");
		}

		HierachicalClusterMiner clustering;

		System.out.println("Scoprire un nuovo HierachicalClusterMiner o caricarlo da File?: ");
		System.out.println("1. Scorprine uno nuovo");
		System.out.println("2. Caricalo da file");
		System.out.print("Scelta: ");
		int choice = Keyboard.readInt();

		if(choice == 1) {
			System.out.print("Inserisci la profondita del dendrogramma da costruire: ");
			int k = Keyboard.readInt();
			clustering = new HierachicalClusterMiner(k);
			System.out.print("Inserisci il nome / percorso del file su cui salvarlo: ");
			String fileName = Keyboard.readString();

                    try {
                        clustering.salva(fileName);
                    } catch (IOException ex) {
						System.out.println("Errore nella scrittura su file, l'oggetto non è stato salvato.");
                    }

		} else if(choice == 2) {

			System.out.print("Inserisci il nome / percorso del file da cui caricare il dendrogramma: ");
			String fileName = Keyboard.readString();

			try {

				clustering = HierachicalClusterMiner.loaHierachicalClusterMiner(fileName);

			} catch (FileNotFoundException e) {
				
				System.out.println("Errore nella lettura da file, file non trovato.");
				System.out.println("Generazione di un dendrogramma di dimensione massima.");
				clustering = new HierachicalClusterMiner(data.getNumberOfExample());

			} catch (ClassNotFoundException e) {

				System.out.println("Errore nella lettura da file, l'oggetto non è stato caricato. Sembra che su file sia salvato altro.");
				System.out.println("Generazione di un dendrogramma di dimensione massima.");
				clustering = new HierachicalClusterMiner(data.getNumberOfExample());

			} catch (IOException e) {

				System.out.println("Errore nella lettura da file, l'oggetto non è stato caricato.");
				System.out.println("Generazione di un dendrogramma di dimensione massima.");
				clustering = new HierachicalClusterMiner(data.getNumberOfExample());

			}

		} else {
			System.out.println("Mi dispiace, le opzioni erano 1 e 2");
			System.out.println("Generazione di un dendrogramma di dimensione massima.");
			clustering = new HierachicalClusterMiner(data.getNumberOfExample());
		}

		System.out.println("Decidi che algoritmo di distanza utilizzare per calcolare il dendrogramma: ");
		System.out.println("1. Single Link Distance");
		System.out.println("2. Average Link Distance");
		System.out.print("Scelta: ");
		choice = Keyboard.readInt();

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
