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
	private PanelLayout panelBloqueo;
	private PanelLayout panelBoton;
	
	//private PanelBoton panelBoton;
	private Procesador procesador;
	
	
	public PanelProceso(Procesador procesador, ActionListener listener){
		this.procesador = procesador;
		setLayout(new GridLayout(5, 1));
		
		panelNombre = new PanelLayout(listener, "Nombre del proceso:", "Escriba el nombre del proceso que desea agregar, recuerde que el nombre ser� el identificador", TipoPanel.texto);
		this.add(panelNombre);
		
		panelBloqueo = new PanelLayout(listener, "¿El proceso debe bloquearse? ", "Selección si el proceso que se esta ingresando debe bloquearse", TipoPanel.select);
		this.add(panelBloqueo);
		
		panelPrioridad = new PanelLayout(listener, "Prioridad del proceso: ", "Escriba la prioridad del proceso que desea agregar, se aceptan n�meros en un rango [1,10], donde 10 es la priodidad m�s alta y 1 la m�s baja", TipoPanel.texto);
		this.add(panelPrioridad);
		
		panelTiempo = new PanelLayout(listener, "Tiempo de ejecucion del proceso: ", "Escriba el tiempo en segundos que se demorar� el proceso en ejecuci�n, se aceptan n�meros en el rango [1, 60]", TipoPanel.texto);
		this.add(panelTiempo);
		
		
		//panelBoton = new PanelBoton(listener);
		panelBoton = new PanelLayout(listener, "", "", TipoPanel.boton);
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
	
	
	

	public PanelLayout getPanelBloqueo() {
		return panelBloqueo;
	}

	public void setPanelBloqueo(PanelLayout panelBloqueo) {
		this.panelBloqueo = panelBloqueo;
	}

	public PanelLayout getPanelBoton() {
		return panelBoton;
	}

	public void setPanelBoton(PanelLayout panelBoton) {
		this.panelBoton = panelBoton;
	}

	public Procesador getProcesador() {
		return procesador;
	}

	public void setProcesador(Procesador procesador) {
		this.procesador = procesador;
	}

	public void limpiarTexto(){
            panelNombre.limpiarTexto();
            panelPrioridad.limpiarTexto();
            panelTiempo.limpiarTexto();
        }

}
