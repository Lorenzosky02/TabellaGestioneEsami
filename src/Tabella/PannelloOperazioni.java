package Tabella;
 
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

/**
 * Classe che rappresenta il pannello in alto delle operazioni disponibili, bottoni e textfield.
 * 
 * @author Lorenzo D'Amato
 * @version 1.0
 */

public class PannelloOperazioni extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ActionListener listener;
	
	private JButton BottoneAggiungiEsito;
	private JButton BottoneFiltra;
	private JTextField daFiltrare;
	private JButton CalcolaMedia;
	private JTextField ContainerMedia;
	private JButton Salva;
	private JButton istogramma;
	private JButton Stampa;
	private JButton Importa;
	private JButton Elimina;
	
	
	/**
     * Costruisce un nuovo oggetto PannelloOperazioni di tipo JPanel.
     *
     * @param Listener Il listener delle azioni dei pulsanti o textfield.
     */
	
	public PannelloOperazioni(ActionListener Listener) {	
		
		this.listener = Listener;
		
        BottoneAggiungiEsito = new JButton("nuovo esito");
        BottoneFiltra = new JButton("filtra");
        daFiltrare = new JTextField(10);
        CalcolaMedia = new JButton("calcola media");
        ContainerMedia = new JTextField(5);
        ContainerMedia.setEditable(false);
        Salva = new JButton("Salva");
        istogramma = new JButton("Istogramma");
        Stampa = new JButton("Stampa");
        Importa = new JButton("Importa");
        Elimina = new JButton("Elimina");
        
        
        BottoneAggiungiEsito.addActionListener(listener);
        BottoneFiltra.addActionListener(listener);
        daFiltrare.addActionListener(listener);
        CalcolaMedia.addActionListener(listener);
        ContainerMedia.addActionListener(listener);
        Salva.addActionListener(listener);
        istogramma.addActionListener(listener);
        Stampa.addActionListener(listener);
        Importa.addActionListener(listener);
        Elimina.addActionListener(Listener);
 
        
        
        add(Salva);
        add(BottoneAggiungiEsito);
        add(Elimina);
        add(BottoneFiltra);
        add(daFiltrare);
        add(CalcolaMedia);
        add(ContainerMedia);
        add(istogramma);
        add(Stampa);
        add(Importa);
        
	}
	
	
	/**
     * Restituisce il testo da filtrare dal jtextfield.
     *
     * @return Il testo da filtrare.
     */
	public String getTestoDaFiltrare() {
		return daFiltrare.getText();
	}
	
	/**
     * Una volta calcolata la media la inserisce nel ContainerMedia.
     *
     * @param media: la media da inserire.
     */
	public void setMediaPesata(String media) {
		ContainerMedia.setText(media);
	}
	
	/**
     * Crea l'istogramma dei voti e lo salva su un file png.
     *
     * @param name Il nome dell'istogramma.
     * @param array Il dataset da cui dover costruire l'istogramma
     * @throws IOException Se si verifica un errore di input/output per quanto riguarda l'immagine.
     */
	public void getHistogramChart(String name, double[] array) throws IOException {
		String plotTitle = name;
		String xAxisLabel = "Voti";
		String yAxis = "Frequenze";
		
		HistogramDataset dataSet = new HistogramDataset();
		dataSet.addSeries("Voti", array, 60);
	  
        JFreeChart Histogram = ChartFactory.createHistogram(plotTitle, xAxisLabel, yAxis, dataSet);
        
        ChartUtils.saveChartAsPNG(new File("./histogram.png"), Histogram, 600, 400);
   
	}
}
