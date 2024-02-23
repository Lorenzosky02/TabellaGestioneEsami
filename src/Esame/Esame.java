package Esame;

/** 
 * @author Lorenzo D'Amato
 * @version 1.0
 * classe astratta che rappresenta un esame generico sostenuto
 * */

public abstract class Esame {
	protected String nome;				/** nome dell'esame */
    protected int cfu;					/** Quanti cfu fornisce il sostenimento della prova */
    protected double voto;				/** Voto conseguito */
    protected boolean lode = false;		/** se l'esame è stato sostenuto con lode o meno */
    protected String studente;			/** nome dello studente*/
    
    /** costruttore (non assegna il voto e la lode perché dipendono dalla tipologia di esame sostenuto
     * @param nome: nome dell'esame.
     * @param cfu: numero di crediti.
     * @param studente: nome dello studente che ha sostenuto l'esame */
    public Esame(String nome, int cfu, String studente) {
        this.nome = nome;
        this.cfu = cfu;
        this.studente = studente;
    }
    
    /** /**
     * metodo da sovrascrivere per ottenere il voto dell'esame.
     *
     * @return voto dell'esame.
     */
    public abstract Double getVoto();
    
    /** metodi getter e setter: */
     
     /**
      * @return Il nome dell'esame.
      */
    public String getNome() {
    	return this.nome;
    }
    
    /**
     * @return Il numero di crediti per l'esame.
     */
    public int getCFU() {
    	return this.cfu;
    }
    
    /**
     * @return nome dello studente che ha sostenuto l'esame.
     */
    public String getStudente() {
    	return this.studente;
    }
    
    /**
     * @return Se l'esame è stato superato con lode.
     */
    public boolean getLode() {
    	return this.lode;
    }
    
    /**
     * Imposta il nome dell'esame.
     *
     * @param nome Il nuovo nome dell'esame.
     */
    public void setNome(String nome) {
    	this.nome = nome;
    }
    
    /**
     * Imposta il numero di crediti per l'esame.
     *
     * @param cfu Il nuovo numero di crediti per l'esame.
     */
    public void setCFU(int cfu) {
    	this.cfu = cfu;
    }
    
    /**
     * Imposta lo studente che ha sostenuto l'esame.
     *
     * @param nomestudente Il nuovo studente che ha sostenuto l'esame.
     */
    public void setStudente(String nomestudente) {
    	this.studente = nomestudente;
    }
    
    /**
     * Imposta il voto dell'esame. Serve per quando andiamo a modificare i dati direttamente sulla tabella
     *
     * @param voto Il nuovo voto dell'esame.
     */
    public void setVoto(double voto) {
    	this.voto = voto;
    }
    
    /**
     * Imposta se l'esame è stato superato con lode.
     *
     * @param lode Se l'esame è stato superato con lode.
     */
    public void setLode(boolean lode) {
    	this.lode = lode;
    }
}
