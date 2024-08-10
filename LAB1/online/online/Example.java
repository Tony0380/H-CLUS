public class Example {
    
    /**
     * vettore di valori reali
     */
    private Double [] example;

    /**
     * Inizializza example come vettore di dimensione lenght
     * @param length : dimensione esempio
     */
    public Example(int length) {
        example = new Double[length]; 
    }

    /**
     * modifica example inserendo v in posizione index
     * @param index posizione del valore
     * @param v valore
     */
    public void set(int index, Double v) {
        example[index] = v;
    }

    /**
     * restituisce example[index]
     * @param index posizione di example
     * @return valore memorizzato in example[index]
     */
    public Double get(int index) {
        return example[index];
    }

    /**
     * Calcola la distanza euclidei tra this.example e new.example
     * @param newE instanza di example
     * @return restituisce il valore calcolato
     */
    public Double distance(Example newE) {
        Double eucDis = 0.0;
        int i = 0;
        while(i < this.example.length) {
            eucDis = eucDis + ((this.example[i] - newE.example[i]) * (this.example[i] - newE.example[i]));
            i++;
        }
        return eucDis;
    }

}
