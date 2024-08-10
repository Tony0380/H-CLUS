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
    
    /**
     * caso di polimorfismo ad hoc per il metodo toString della classe Object per il parametro di tipo Example
     * @return stringa contenente la rappresentazione in stringa di caratteri sotto forma di [ V0, V1 ... Vn ] dove Vi sono valori dell'esempio
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder ("[ ");
        for(int i = 0; i < this.example.length; i++) {
            str.append(this.example[i]);
            str.append(" ");
        }
        str.append("]");
        return str.toString();
    }
}
