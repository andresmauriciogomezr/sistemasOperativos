package GUI;

import java.awt.BorderLayout;
import main.Procesador;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelProceso extends JPanel{
	private PanelLayout panelNombre;
	private PanelLayout panelPrioridad;
	private PanelLayout panelTiempo;
	
	private PanelBoton panelBoton;
	private Procesador procesador;
	
	
	public PanelProceso(Procesador procesador, ActionListener listener){
		this.procesador = procesador;
		setLayout(new GridLayout(6, 1));
		
		panelNombre = new PanelLayout("Nombre del proceso:", "sisas");
		this.add(panelNombre);
		
		panelPrioridad = new PanelLayout("Prioridad del proceso: ", "sisas");
		this.add(panelPrioridad);
		
		panelTiempo = new PanelLayout("Tiempo de ejecución del proceso: ", "sisas");
		this.add(panelTiempo);
		
		
		panelBoton = new PanelBoton(listener);	
		this.add(panelBoton);
	}

	public PanelLayout getPanelNombre() {
		return panelNombre;
	}

	public void setPanelNombre(PanelLayout panelNombre) {
		this.panelNombre = panelNombre;
	}

	public PanelLayout getPanelPrioridad() {
		return panelPrioridad;
	}

	public void setPanelPrioridad(PanelLayout panelPrioridad) {
		this.panelPrioridad = panelPrioridad;
	}

	public PanelLayout getPanelTiempo() {
		return panelTiempo;
	}

	public void setPanelTiempo(PanelLayout panelTiempo) {
		this.panelTiempo = panelTiempo;
	}

	public PanelBoton getPanelBoton() {
		return panelBoton;
	}

	public void setPanelBoton(PanelBoton panelBoton) {
		this.panelBoton = panelBoton;
	}

	public Procesador getProcesador() {
		return procesador;
	}

	public void setProcesador(Procesador procesador) {
		this.procesador = procesador;
	}

	

}
