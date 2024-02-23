package Esame;

/**
 * Classe che rappresenta un esame semplice, estendendo la classe astratta Esame.
 * 
 * @author Lorenzo D'Amato
 * @version 1.0
 */
public class EsameSemplice extends Esame{
	
	/**
     * Costruisce un nuovo oggetto EsameSemplice.
     *
     * @param nome: Il nome dell'esame.
     * @param cfu: Il numero di crediti per l'esame.
     * @param voto: Il voto dell'esame.
     * @param studente: nome dello studente che ha sostenuto l'esame.
     */
	
	public EsameSemplice(String nome, int cfu, double voto, String studente) {
		super(nome, cfu, studente);
		this.voto = voto;
	}
	
	/**
     * Ottiene il voto dell'esame. Se il voto è maggiore o uguale a 30, restituisce 30.0.
     * il caso in cui il voto sia minore di 18 viene gestito direttamente in TablePanel
     * @return Il voto dell'esame.
     */
	
	@Override
	public Double getVoto() {
		if(this.voto >= 30) {
			return 30.0;
		}
		return this.voto;
	}
	
	 /**
     * Imposta la lode. Se il voto è inferiore a 30, la lode viene impostata di default a false.
     *
     * @param lode Se l'esame è stato superato con lode.
     */
	
	public void setLode(boolean lode) {
		if(this.getVoto() >= 30) {
			this.lode = lode;
		}
		else {
			this.lode = false;
		}
	}
}
