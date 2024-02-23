package Esame;

import java.text.*;


import java.util.*;
/**
 * Classe che rappresenta un esame composto, estendendo la classe astratta Esame.
 * 
 * @author Lorenzo D'Amato
 * @version 1.0
 */
public class EsameComposto extends Esame {

	private Vector<Integer> CfuParziali = new Vector<Integer>(); //vettore di interi per i cfu di ciascun parziale
	private Vector<Double> votiParziali = new Vector<Double>();	 //vettore di double per i voti di ciascun parziale
	private boolean isforced = false;  							 //gestisce la modifica diretta dalla tabella del voto di un esame composto (invece di calcolarlo dai parziali sostenuti
	DecimalFormat df = new DecimalFormat("#.##"); 				 //formattazione per i double
	
	/**
     * Costruisce un nuovo oggetto EsameComposto.
     *
     * @param nome Il nome dell'esame.
     * @param cfu Il numero di crediti per l'esame.
     * @param Studente Lo studente che ha sostenuto l'esame.
     */
	public EsameComposto(String nome, int cfu, String Studente) {
		super(nome, cfu, Studente);
		
	}
	
	
	/**
     * Aggiunge un parziale all'esame.
     *
     * @param cfu I crediti del parziale.
     * @param voto Il voto del parziale.
     */
	public void addParziale(Integer cfu, Double voto) {
		CfuParziali.add(cfu);
		votiParziali.add(voto);
	}
	
	
	/**
     * Restituisce una stringa formattata con i dettagli dei parziali.
     *
     * @return Una stringa con i dettagli dei parziali.
     */
	public String getParzialiAsString() {
		String result = " ";
		for (int i=0; i < votiParziali.size(); i++) {
			
			result = result.concat("Parziale " + (i+1) +":  Voto: " + votiParziali.elementAt(i).toString() + " Peso (in %): " 
			+ df.format(((double)((double)CfuParziali.elementAt(i)/(double)this.cfu))) + "\n");
		}
		return result;
	}
	
	
	/**
     * Calcola il voto dell'esame. Se il voto non è stato forzato, calcola la media pesata dei parziali.
     *
     * @return Il voto dell'esame.
     */
	@Override
	public Double getVoto() {
		if(!this.isforced) {
			Integer sommaPesi = 0;
	        Double sommaVoti = 0.0;
	        this.voto = 0.0;
	        
	        for (int i = 0; i < votiParziali.size(); i++) {
	        	sommaVoti += votiParziali.elementAt(i) * CfuParziali.elementAt(i);
	        	sommaPesi += CfuParziali.elementAt(i);
	        }
	        voto = (double)Math.round(sommaVoti / sommaPesi);
	        if(voto >= 30) {
	        	return 30.0;
	        }
		}
		
        return voto;
	}
	
	
	/**
     * Imposta il voto dell'esame forzandolo, quando lo modifichiamo sulla tabella.
     *
     * @param voto: Il voto forzato.
     */
	public void setVotoForced(double voto) {
		this.isforced = true;
		this.voto = voto;
	}
	
	
	/**
     * Imposta se l'esame è stato superato con lode, ovviamente solo se il voto è 30.
     *
     * @param lode Se l'esame è stato superato con lode.
     */
	public void setLode(boolean lode) {
		if(lode == true && this.getVoto() >= 30) {
			this.lode = true;
		}
		else {
			this.lode = false;
		}
	}
	
}
