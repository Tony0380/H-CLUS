package src;

import java.io.*;
import java.net.*;
import src.clustering.HierachicalClusterMiner;
import src.data.Data;
import src.distance.*;
import src.exceptions.*;
public class serverOneClient extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public serverOneClient(Socket s) throws IOException {
        socket = s;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());

        // se una qualsiasi delle chiamate precedenti solleva una
        // eccezione, il processo chiamante è responsabile della 
        // chiusura del socket. Altrimenti lo chiuderà il thread 
        start(); // Chiama run()
    }

    /**
     * Ricevuto il nome della taballa del DB da cui prendere gli esempi,
     * crea l'oggetto di tipo Data e lo restituisce
     * @return Data presi dal Database e dalla tabella specificata dal client
     * @throws IOException Ecccezione di I/O
     */
    private Data receiveDataClient() throws IOException, ClassNotFoundException {
        String data = (String) in.readObject();
        System.out.println("Ricevuto: " + data);
        out.writeObject("OK");

        Data datas = null;
		boolean loadedData = false;

        do {
            try {
                datas = new Data(data);
                loadedData = true;
            } catch (NoDataException e) {
                System.out.println(e.getMessage());
            }
        } while (!loadedData);
        return datas;
    }

    /**
     * Carica il dendrogramma da file sul server
     * @throws IOException Eccezione di I/O
     * @throws ClassNotFoundException Eccezione di classe non trovata
     */
    private void loadDedrogramFromFileOnServer() throws IOException, ClassNotFoundException {
        String filename = (String) in.readObject();
        System.out.println("Ricevuto: " + filename);
        
        HierachicalClusterMiner hcm = HierachicalClusterMiner.loaHierachicalClusterMiner(filename);
        out.writeObject(hcm);
    }

    /**
     * Esegue il clustering gerarchico
     * @param data Data da clusterizzare
     * @throws IOException Eccezione di I/O
     * @throws ClassNotFoundException Eccezione di classe non trovata
     */
    private void mineDendrogram(Data data) throws IOException, ClassNotFoundException {
        HierachicalClusterMiner hcm = new HierachicalClusterMiner((int) in.readObject());
        int choice = (int) in.readObject();
        try {
            if(choice == 1) {
                ClusterDistance distance = new SingleLinkDistance();
                hcm.mine(data, distance);
            } else if(choice == 2) {
                ClusterDistance distance = new AverageLinkDistance();
                hcm.mine(data, distance);
            } else {
                throw new InvalidChoiceException("Scelta non valida");
            }
            out.writeObject("OK");
            out.writeObject(hcm);

            hcm.salva((String) in.readObject());
        } catch (InvalidChoiceException e) {
            System.out.println(e.getMessage());
        }

    } 

    public void run() {
        int check;
        Data data = null;
        try {
            check = (int) in.readObject();
            if(check == 0) {
                data = receiveDataClient();
            } else {
                throw new InvalidChoiceException("Errore nella connessione");
            }
            check = (int) in.readObject();
            if (check == 1) {
                mineDendrogram(data);
            } else if (check == 2) {
                loadDedrogramFromFileOnServer();
            } else {
                throw new InvalidChoiceException("Errore nella connessione");
            }
        } catch (InvalidChoiceException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}