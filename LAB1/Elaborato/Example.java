package Elaborato;

public class Example {
    
    /**
     * Vettore di valori reali
     */
    private Double [] example;

    /**
     * Inizializza example come vettore di dimensione lenght
     * @param length dimensione esempio
     */
    Example(int length) {
        example = new Double[length]; 
    }

    /**
     * Modifica example inserendo v in posizione index
     * @param index posizione del valore
     * @param v valore
     */
    public void set(int index, Double v) {
        example[index] = v;
    }

    /**
     * Restituisce example[index]
     * @param index posizione di example
     * @return valore memorizzato in example[index]
     */
    public Double get(int index) {
        return example[index];
    }

    /**
     * Calcola la distanza euclidea tra this.example e newE.example
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
     * Polimorfismo ad hoc per il metodo toString della classe Object realizzato per la classe Example
     * @return stringa contenente la rappresentazione in stringa di caratteri sotto forma di {@code [ V0, V1 ... Vn ]} dove Vi sono valori dell'esempio
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder ("[");
        for(int i = 0; i < this.example.length; i++) {
            str.append(this.example[i]);
            if(i != this.example.length-1) {
                str.append(",");
            }
        }
        str.append("]");
        return str.toString();
    }
}
