public class Data {
    /** Dataset */
    Example data[];
    /** Numero di esempi nel Dataset */
    int numberOfExamples;

    /** Avvalora un oggetto data predefinito (fornito dal docente) */
    Data(){
		//data
		
		data = new Example [5];
		Example e=new Example(3);
		e.set(0, 1.0);
		e.set(1, 2.0);
		e.set(2, 0.0);
		data[0]=e;
		
		e=new Example(3);
		e.set(0, 0.0);
		e.set(1, 1.0);
		e.set(2, -1.0);
		data[1]=e;
		
		e=new Example(3);
		e.set(0, 1.0);
		e.set(1, 3.0);
		e.set(2, 5.0);
		data[2]=e;

		e=new Example(3);
		e.set(0, 1.0);
		e.set(1, 3.0);
		e.set(2, 4.0);
		data[3]=e;

		e=new Example(3);
		e.set(0, 2.0);
		e.set(1, 2.0);
		e.set(2, 0.0);
		data[4]=e;

		// numberOfExamples		
		numberOfExamples=5;		 
	}

    /**
     * restituisce il numero di esempi nel Dataset
     * @return numero di esempi memorizzati in data
     */
    public int getNumberOfExample() {
        return this.numberOfExamples;
    }

    /**
     * restituisce data[exampleIndex]
     * @param exampleIndex indice di un esempio memorizzato in data
     * @return l’esempio memorizzato in data[exampleIndex]
     */
    public Example getExample(int exampleIndex) {
        return this.data[exampleIndex];
    }

    /**
     *  restituisce la matrice triangolare superiore delle distanze
     * @return matrice triangolare superiore delle distanze Euclidee calcolate tra gli  esempi memorizzati in data.
     * Tale matrice va avvalorata usando il metodo distance di Example
     */
    public double[][] distance () {
        //TODO
    }
}
