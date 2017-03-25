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
		setLayout(new GridLayout(4, 1));
		
		panelNombre = new PanelLayout("Nombre del proceso:", "Escriba el nombre del proceso que desea agregar, recuerde que el nombre será el identificador");
		this.add(panelNombre);
		
		panelPrioridad = new PanelLayout("Prioridad del proceso: ", "Escriba la prioridad del proceso que desea agregar, se aceptan números en un rango [1,10], donde 10 es la priodidad más alta y 1 la más baja");
		this.add(panelPrioridad);
		
		panelTiempo = new PanelLayout("Tiempo de ejecución del proceso: ", "Escriba el tiempo en segundos que se demorará el proceso en ejecución, se aceptan números en el rango [1, 60]");
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
