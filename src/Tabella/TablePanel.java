package Tabella;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import javax.swing.table.*;
import org.jfree.chart.JFreeChart;
import Esame.Esame;
import Esame.EsameComposto;
import Esame.EsameSemplice;

import java.util.*;

/**
 * Classe che implementa il pannello contenente la tabella e il pannello contenente le operazioni, gestendo gli ActionEvent.
 * 
 * @author Lorenzo D'Amato
 * @version 1.0
 */

public class TablePanel extends JPanel implements ActionListener{
	
	DecimalFormat df = new DecimalFormat("#.##");											//imposta il formato decimale
	
	Vector<Esame> Esami = new Vector<Esame>();												//il vettore degli esami da passare alla tabella
	Tabella ModelloTabella = new Tabella(Esami);											//crea il TableModel 
	TableRowSorter<Tabella> Filtratore = new TableRowSorter<Tabella>(ModelloTabella);		//crea il rowsorter per la impostato con i generics
	JTable TabellaEsami = new JTable(ModelloTabella);										//crea l'effettiva tabella esami che ha come modello ModelloTabella
	JPanel Operazioni = new PannelloOperazioni(this);										//crea il pannello operazioni con actionListener

	private static final long serialVersionUID = 1L;

	/** 
	 * Costruttore del pannello principale, aggiunge il pannello opzioni, uno ScrollPane per la tabella e un mouse listener che capta il doppio click su una riga contenente
	 * un esame composto, oltre che al rowsorter e il layout generale.
	 * 
	 * */
	
	public TablePanel() {
		
		//alcuni esami inseriti manualmente "riempitivi"
		EsameSemplice analisi = new EsameSemplice("Analisi", 9, 18, "Lorenzo");
		EsameSemplice algebra = new EsameSemplice("Algebra", 9, 19, "Lorenzo");
		EsameSemplice statistica = new EsameSemplice("fisica", 9, 30, "Lorenzo");
		EsameComposto ProgOgg = new EsameComposto("PO", 9, "Lorenzo");
		ProgOgg.addParziale(4, 20.0);
		ProgOgg.addParziale(5, 27.5);
		statistica.setLode(true);
		
		Esami.add(analisi);
		Esami.add(algebra);
		Esami.add(statistica);
		Esami.add(ProgOgg);
		
		
		setLayout(new BorderLayout());
	

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for (int i = 0; i < TabellaEsami.getColumnCount(); i++) {
			TabellaEsami.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		}
		
		JScrollPane scrollPane = new JScrollPane(TabellaEsami);
		TabellaEsami.setRowSorter(Filtratore);
	
		add(Operazioni, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		
		TabellaEsami.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table =(JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					Esame selectedEsame = Esami.get(row);
					if(selectedEsame instanceof EsameComposto) {
						JOptionPane.showMessageDialog(null, ((EsameComposto)selectedEsame).getParzialiAsString());
					}
				}
			}
		});
	}

	
	/** 
	 * Metodo che implementa un actionListener per captare il pulsante premuto. Esegue determinate operazioni in base al pulsante
	 * @param e L'evento ascoltato
	 * 
	 * */

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		//crea degli option pane con cui l'utente può inserire i dati, con check di correttezza dei dati inseriti.
		if (e.getActionCommand() == "nuovo esito") {
			String nomeEsame = JOptionPane.showInputDialog("Inserisci il nome dell'esame:");
			if(nomeEsame.equals("")) this.dispatchEvent(e);
			
			String Studente = JOptionPane.showInputDialog("Inserisci il nome e il cognome dello studente:");
			if(Studente.equals("")) this.dispatchEvent(e);
			
			int cfu = Integer.parseInt(JOptionPane.showInputDialog("CFU previsti"));
			int checkparziali = JOptionPane.showConfirmDialog(null, "Sono previsti parziali?");
			switch(checkparziali) {
			case 0: 
				EsameComposto esameC = new EsameComposto(nomeEsame, cfu, Studente);
				int sommacfu = 0;
				int cont = 1;
				while(sommacfu < cfu) {
					int cfuparziale = Integer.parseInt(JOptionPane.showInputDialog("CFU parziale n." + cont));
					double votoparziale = Double.parseDouble(JOptionPane.showInputDialog("Voto parziale"));
					esameC.addParziale(cfuparziale, votoparziale);
					sommacfu += cfuparziale;
					cont++;
				}
				if (esameC.getVoto() == 30.0) {
					int lode = JOptionPane.showConfirmDialog(null, "Lode");
					switch(lode) {
						case 0: esameC.setLode(true); break;
						case 1: esameC.setLode(false); break;
						case 2: esameC.setLode(false); break;
					}
					Esami.add(esameC);
				}
				else if (esameC.getVoto() < 18.0) {
					JOptionPane.showMessageDialog(null, "Voto insufficiente, esito non aggiunto ");
					this.dispatchEvent(e);
				}
				else Esami.add(esameC);
				
				ModelloTabella.fireTableDataChanged();
				break;
			case 1:
				double voto = Double.parseDouble(JOptionPane.showInputDialog("Inserisci voto"));
				EsameSemplice esameS = new EsameSemplice(nomeEsame, cfu, voto, Studente);
				if (esameS.getVoto() == 30.0) {
					int lode = JOptionPane.showConfirmDialog(null, "Lode");
					switch(lode) {
						case 0: esameS.setLode(true); break;
						case 1: esameS.setLode(false); break;
						case 2: esameS.setLode(false); break;
					}
					Esami.add(esameS);
				}
				else if(esameS.getVoto() < 18) {
					JOptionPane.showMessageDialog(null, "Voto insufficiente, esito non aggiunto ");
					this.dispatchEvent(e);
				}
				else Esami.add(esameS);
				ModelloTabella.fireTableDataChanged();
				break;
			case 2:
				break;
			}
			
		}
		
		//qui il row filter viene settato e la tabella filtrata. SearchText è la stringa inserita dall'utente
		else if (e.getActionCommand() == "filtra") {
			String searchText = ((PannelloOperazioni) Operazioni).getTestoDaFiltrare();	
			FilterLogic filtro = new FilterLogic(searchText);
			if (searchText != "") {
				Filtratore.setRowFilter(filtro);
				TabellaEsami.revalidate();
			}
			
		}
		
		//calcola la media degli esami presenti su schermo
		else if (e.getActionCommand() == "calcola media") {
			double sommaVoti = 0;
			int sommaPesi = 0;
			for (int row = 0; row < TabellaEsami.getRowCount(); row++) {
				double voto = (double)TabellaEsami.getModel().getValueAt(TabellaEsami.convertRowIndexToModel(row), 3);
				int cfu = (int)TabellaEsami.getModel().getValueAt(TabellaEsami.convertRowIndexToModel(row), 2);
				sommaVoti += voto*cfu;
				sommaPesi += cfu;
			}
			((PannelloOperazioni) Operazioni).setMediaPesata("" + df.format(((double)((double)sommaVoti/(double)sommaPesi))));
			sommaVoti = 0; sommaPesi = 0;
		}
		
		//salva la tabella in un file di testo formattato
		else if (e.getActionCommand() == "Salva") {
			try {
				String nomefile = JOptionPane.showInputDialog(null, "Nome del file (senza .txt)");
				File file = new File("./" + nomefile + ".txt");
				if(!file.exists()) {
					file.createNewFile();
				}
				else {
					int sovrascrivere = JOptionPane.showConfirmDialog(null, "Esiste già un file con questo nome... Sovrascrivere il file?");
					switch(sovrascrivere) {
					case 0: file.delete(); file.createNewFile(); break;
					case 1: break;
					case 2: break;
					}
				}
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				
				
				for (int row = 0; row < TabellaEsami.getRowCount(); row++) {
					for (int col = 0; col < 5; col++) {
						bw.write(TabellaEsami.getModel().getValueAt(row, col).toString() + ",\t");
					}
					bw.write("\n");
				}
				bw.close();
				fw.close();
				JOptionPane.showMessageDialog(null, "dati salvati correttamente");
			}
			catch(Exception error) {
				
			}
		}
		
		//genera l'istogramma con i dati presenti su schermo 
		else if(e.getActionCommand() == "Istogramma") {
			int dim = (int)TabellaEsami.getRowCount();
			final double DataArray[] = new double[dim];
			double voto = 0;
			for (int row = 0; row < TabellaEsami.getRowCount(); row++) {
				voto = (double)TabellaEsami.getModel().getValueAt(TabellaEsami.convertRowIndexToModel(row), 3);
				DataArray[row] = voto;
			}
			try {
				
				((PannelloOperazioni)Operazioni).getHistogramChart("Istogramma di frequenze relativo ai voti", DataArray);
				JOptionPane.showMessageDialog(null, "dati salvati in Histogram.png");
				
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, "Erore nella generazione del grafico: " + e1.getMessage());
			}
			
			
		}
		
		//apre una finestra con la quale è possibile stampare la tabella
		else if(e.getActionCommand() == "Stampa") {
			try {
				MessageFormat header = new MessageFormat("Page {0,number,integer}");
			    TabellaEsami.print(JTable.PrintMode.FIT_WIDTH, header, null); 
			} catch (java.awt.print.PrinterException ex) {
				JOptionPane.showMessageDialog(null, "Erore: " + ex.getMessage());
			}
		}
		else if (e.getActionCommand() == "Importa") {
			String nomefile = JOptionPane.showInputDialog(null, "Nome del file (senza .txt)");
			
			
			try {
				FileReader file = new FileReader("./" + nomefile + ".txt");
				BufferedReader br = new BufferedReader(file);
			
				//Esami.clear();
				
				Object[] tableLines = br.lines().toArray();
				
				int cont = 0;
				while(cont < tableLines.length) {
					String[] elements = ((String) tableLines[cont]).trim().split(",");
					String nomeEsame = elements[0].trim();
					String Studente = elements[1].trim();
					
					int cfu = Integer.parseInt(elements[2].trim());
					double voto = Double.parseDouble(elements[3].trim());
					boolean lode = (elements[4].trim() == "si");
					
					if (nomeEsame.contains(" (C)")) {
						EsameComposto exam = new EsameComposto(nomeEsame.replace(" (C)", ""), cfu, Studente);
						exam.setVotoForced(voto);
						exam.setLode(lode);
						Esami.add(exam);
						
					}
					else {
						EsameSemplice exam = new EsameSemplice(nomeEsame, cfu, voto, Studente);
						exam.setLode(lode);
						Esami.add(exam);
					}
					cont ++;
					
				}
				br.close();
				ModelloTabella.fireTableDataChanged();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "Erore: File non trovato");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		//elimina una o più righe (fixare + righe)
		else if (e.getActionCommand() == "Elimina") {
			
			int rows[] = TabellaEsami.getSelectedRows();
			int dim = rows.length;
			if(rows.length != 0) {
				for (int i = 0; i < dim; i++) {
					Esami.removeElementAt(i);
					ModelloTabella.fireTableDataChanged();
				}
			}
		}
	}
}
