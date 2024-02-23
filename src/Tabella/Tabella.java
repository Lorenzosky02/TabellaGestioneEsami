package Tabella;

import javax.swing.table.AbstractTableModel;

import Esame.*;

import java.util.*;

/**
 * Classe che rappresenta il TableModel a partire da cui verrà creata la jtable.
 * 
 * @author Lorenzo D'Amato
 * @version 1.0
 */
public class Tabella extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	
	private Vector<Esame> esami = null;											//vettore di esami che rappresenta le righe della tabella
	
	private String[] colonne = {"Esame", "Studente", "CFU", "Voto", "Lode"};	//nome dei campi della tabella
	
	/** 
	 * Costruttore della tabella che ha come righe il vettore esami
	 * @param esami Il vettore di tipo Esame che definisce la tabella
	 * */
	public Tabella(Vector<Esame> esami) {
		this.esami = esami;
	
	}
	
	/**
	 * Restituisce il numero di colonne.
	 * @return Il numero di colonne.
	 */
	public int getColumnCount() {
		return colonne.length;
	}
	
	
	/**
	 * Restituisce il numero di righe.
	 * @return Il numero di righe.
	 */
	public int getRowCount() {
		return esami.size();
	}
	
	/**
	 * Restituisce il valore alla posizione specificata, distinguendo tra esame semplice e composto.
	 * @param row La riga.
	 * @param col La colonna.
	 * @return Il valore alla posizione specificata.
	 */
	public Object getValueAt(int row, int col) {
		if(esami.elementAt(row) instanceof EsameSemplice) {
			switch(col) {
			case 0: return ((EsameSemplice)esami.elementAt(row)).getNome();
			case 1: return ((EsameSemplice)esami.elementAt(row)).getStudente();
			case 2: return ((EsameSemplice)esami.elementAt(row)).getCFU();
			case 3: return ((EsameSemplice)esami.elementAt(row)).getVoto();
			case 4: if( ((EsameSemplice)esami.elementAt(row)).getLode() ) {
				return "si";
			}
			else return "no";
			default: return "errore";
			
			}
		}
		else {
			switch(col) {
				case 0: return ((EsameComposto)esami.elementAt(row)).getNome() + " (C)";
				case 1: return ((EsameComposto)esami.elementAt(row)).getStudente();
				case 2: return ((EsameComposto)esami.elementAt(row)).getCFU();
				case 3: return ((EsameComposto)esami.elementAt(row)).getVoto();
				case 4: if( ((EsameComposto)esami.elementAt(row)).getLode() ) {
					return "si";
				}
				else return "no";
				default: return "errore";
				}
			
			}
		
	}
	
	/**
	 * Restituisce il nome della colonna.
	 * @param col La colonna.
	 * @return Il nome della colonna.
	 */
	public String getColumnName(int col) {
		return colonne[col];
	}
	
	
	/**
	 * Restituisce la classe della colonna.
	 * @param col La colonna.
	 * @return la classe della colonna.
	 */
	public Class<?> getColumnClass(int col) {
		return getValueAt(0, col).getClass();
	}
	
	/**
	 * Tutte le celle sono editabili...
	 * @param col La colonna.
	 * @param row La riga
	 * @return vero.
	 */
	public boolean isCellEditable(int row, int col) {
		return true;
	}
	
	/**
	 * Setta il valore nella cella (row, col) modificato manualmente dall'utente.
	 * @param value Il valore scelto dall'utente.
	 * @param row La riga
	 * @param col La colonna
	 */
	public void setValueAt(Object value, int row, int col) {
		if (col == 0) {
			((Esame) esami.elementAt(row)).setNome((String)value); ;
		}
		if (col == 1) {
			((Esame) esami.elementAt(row)).setStudente((String)value); 
		}
		if (col == 2) {
			((Esame) esami.elementAt(row)).setCFU((int)value); 
		}
		if (col == 3) {
			((Esame) esami.elementAt(row)).setVoto((double)value); 
			
		}
		if (col == 4) {
			if(((String)value).equals("si") && esami.elementAt(row).getVoto() == 30.0) {
				((Esame)esami.elementAt(row)).setLode(true);
			}
			else {
				((Esame) esami.elementAt(row)).setLode(false);
			}
			
		}
		fireTableDataChanged();
	}
	
}
