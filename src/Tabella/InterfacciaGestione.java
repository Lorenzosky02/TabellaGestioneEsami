package Tabella;
import java.awt.Color;

import javax.swing.*;
	
/** 
 * Classe che genera il JFrame, ovvero la vera e propria interfaccia grafica
 * @author Lorenzo D'Amato
 * @version 1.0
 * */
public class InterfacciaGestione extends JFrame{	
	
	private static final long serialVersionUID = 1L;
	/** 
	 * Costruttore che crea l'interfaccia
	 * */
	public InterfacciaGestione() {
		
		setTitle("Gestione Esami");
		setSize(1000, 562);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
