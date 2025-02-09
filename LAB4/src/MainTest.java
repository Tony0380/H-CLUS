package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import src.clustering.HierachicalClusterMiner;
import src.data.Data;
import src.distance.*;
import src.exceptions.NoDataException;


//*********************************************************************************************************************
//	Accortezze per poter utilizzare il programma:
//
//	Avere il servizio mySQL avviato sulla macchina su cui si sta eseguendo il programma e sulla porta 3306
//	Aver precedentemente creato un user nel DBMS di		Username: MapUser	e	Password: map
//	Aver precedentemente creato un Database con		Nome: MapDB		e opportune tabelle da cui estrapolare gli esempi.
//	Nel caso si vogliano cambiare questi parametri è necessario modificarli negli attributi della classe DbAccess
//
//
//	Aver inserito il file "mysql-connector-java-8.0.17.jar" Presente nel package database tra le librerie.
//	(Esso contiene il driver MYSQL-JDBC)
//*********************************************************************************************************************
public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Data data = null;
		boolean loadedData = false;
		do { 

			try {

				System.out.print("Inserisci il nome della tabella da cui recuperare gli Esempi: ");
				String tableName = Keyboard.readString();
				data = new Data(tableName);
				loadedData = true;

			} catch (NoDataException e) {
	
				System.out.println(e.getMessage());
				
			}
	
		} while (!loadedData);



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
