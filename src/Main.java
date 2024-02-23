

import javax.swing.*;

import Tabella.InterfacciaGestione;
import Tabella.TablePanel;

/**  
 * Classe che implementa il metodo main per la creazione del Frame e l'aggiunta del panel principale
 *  */
public class Main {
	public static void main(String args[]) {
		JFrame frame = new InterfacciaGestione();
		TablePanel TablePanel = new TablePanel();
		
		
		frame.add(TablePanel);
		frame.setVisible(true);
	}
}
