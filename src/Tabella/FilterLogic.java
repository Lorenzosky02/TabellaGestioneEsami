package Tabella;

import javax.swing.RowFilter;

/**
 * Classe per gestire la logica di filtraggio sulla tabella, in maniera case insensitive
 * 
 * @author Lorenzo D'Amato
 * @version 1.0
 */

public class FilterLogic extends RowFilter<Object, Object> {
	private String searchText;	//campo di ricerca del filtro
	
	/** costruttore di un oggetto di tipo RowFilter, case insensitive 
	 * @param searchText: l'esame o lo studente da dover cercare */
	public FilterLogic(String searchText) {
		this.searchText = searchText.toLowerCase();
	}
	
	/**
     * Determina se una riga della tabella deve essere mostrata.
     *
     * @param entry: la stringa da controllare.
     * @return true se la stringa è presente nel primo o secondo campo di una row (nome esame e studente), false altrimenti.
     */
	public boolean include(Entry<?, ?> entry) {
		return entry.getStringValue(0).toLowerCase().indexOf(searchText) >= 0 | 
				entry.getStringValue(1).toLowerCase().indexOf(searchText) >= 0;
		
	}
}
