package src.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Data implements Iterable<Example>{
    /** Dataset */
    private List<Example> data = new ArrayList<>();

    /** Avvalora un oggetto data predefinito (fornito dal docente) */
    public Data(){
		//data
		
		Example e=new Example();
		e.add(1.0);
		e.add(2.0);
		e.add(0.0);
		data.add(e);
		
		e=new Example();
		e.add(0.0);
		e.add(1.0);
		e.add(-1.0);
		data.add(e);
		
		e=new Example();
		e.add(1.0);
		e.add(3.0);
		e.add(5.0);
		data.add(e);

		e=new Example();
		e.add(1.0);
		e.add(3.0);
		e.add(4.0);
		data.add(e);

		e=new Example();
		e.add(2.0);
		e.add(2.0);
		e.add(0.0);
		data.add(e);	 
	}

    /**
     * Implementazione del metodo virtuale iterator presente nell'interfaccia Iterable
     * @return Iteratore per la Collection di tipo ArrayList data
     */
    public Iterator<Example> iterator(){
        return data.iterator();
    }

    /**
     * Restituisce il numero di esempi nel Dataset
     * @return numero di esempi memorizzati in data
     */
    public int getNumberOfExample() {
        return data.size();
    }

    /**
     * Restituisce l'esempio nel dataset in posizione passata come parametro
     * @param exampleIndex indice di un esempio memorizzato in data
     * @return l’esempio memorizzato in nella posizione passata
     */
    public Example getExample(int exampleIndex) {
        return data.get(exampleIndex);
    }

    /**
     * Restituisce la matrice triangolare superiore delle distanze
     * @return matrice triangolare superiore delle distanze Euclidee calcolate tra gli esempi memorizzati in data.
     * Tale matrice va avvalorata usando il metodo distance di Example
     */
    public double[][] distance () {
        double [][] distanceMatrix = new double[getNumberOfExample()][getNumberOfExample()];
        for(int i = 0; i < getNumberOfExample(); i++) {
            for(int j = i; j < getNumberOfExample(); j++) {
                distanceMatrix[i][j] = this.getExample(i).distance(getExample(j));
            }
        }
        return distanceMatrix;
    }

    /**
     * Polimorfismo ad hoc per il metodo toString della classe Object realizzato per la classe Data.
     * Crea una stringa in cui memorizza gli esempi memorizzati nell’attributo data, opportunamente enumerati.
     * Restituisce tale stringa
     * @return stringa che modella lo stato dell'oggetto
     */
    public String toString() {
        StringBuilder str = new StringBuilder();

        Iterator<Example> i = iterator();
        int tag = 0; //Utilizzato per stampare il numero ordinale

        while(i.hasNext()) {
            str.append(tag);
            str.append(":");
            str.append(i.next());
            str.append("\n");
            tag++;
        }
        return str.toString();
    }

    public static void main(String args[]){
		Data trainingSet=new Data();
		System.out.println(trainingSet);
		double [][] distancematrix=trainingSet.distance();
		System.out.println("Distance matrix:\n");
		for(int i=0;i<distancematrix.length;i++) {
			for(int j=0;j<distancematrix.length;j++)
				System.out.print(distancematrix[i][j]+"\t");
			System.out.println("");
		}
	}
}
