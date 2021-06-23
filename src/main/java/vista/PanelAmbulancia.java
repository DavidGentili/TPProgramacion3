package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import clinica.Ambulancia;

public class PanelAmbulancia extends JPanel implements Observer{
	
	private Ambulancia observado;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	

	public PanelAmbulancia() throws HeadlessException {
		setPreferredSize(new Dimension(300, 300));
    	setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ambulancia", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
    	setLayout(new BorderLayout(0, 0));
    	
    	this.scrollPane = new JScrollPane();
    	add(this.scrollPane, BorderLayout.CENTER);
    	
    	this.textArea = new JTextArea();
    	this.scrollPane.setViewportView(this.textArea);
	}
	
	public void setObservado(Ambulancia observado) {
		this.observado = observado;
	}

	/**
	 * Al notifyObservers hay q pasarle por parametro el estado
	 */
	@Override
	public void update(Observable o, Object arg) {
		Ambulancia a;
		if(o==this.observado) {
			a = (Ambulancia) o;
			this.textArea.append("La ambulancia ahora esta " + arg);
		}
		
	}

}
