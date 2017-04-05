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
	private PanelLayout panelDestruir;
	private PanelLayout panelSuspender;
	private PanelLayout panelPrioridad;
	private PanelLayout panelCambioPrioridad;
	private PanelLayout panelTiempo;
	private PanelLayout panelBloqueo;
	private PanelLayout panelComunicacion;
	private PanelLayout panelBoton;
	
	//private PanelBoton panelBoton;
	private Procesador procesador;
	
	
	public PanelProceso(Procesador procesador, ActionListener listener){
		this.procesador = procesador;
		setLayout(new GridLayout(9, 1)); // El numero de filas, el numero de columnas
		
		panelNombre = new PanelLayout(listener, "Nombre del proceso:", "Escriba el nombre del proceso que desea agregar, recuerde que el nombre ser� el identificador", TipoPanel.texto);
		this.add(panelNombre);
		
		panelDestruir = new PanelLayout(listener, "¿El proceso debe Destruirse? ", "Selección si el proceso que se esta ingresando debe destruirse", TipoPanel.select);
		this.add(panelDestruir);
		
		panelSuspender = new PanelLayout(listener, "¿El proceso debe Suspederse? ", "Selección si el proceso que se esta ingresando debe suspenderse", TipoPanel.select);
		this.add(panelSuspender);
		
		panelBloqueo = new PanelLayout(listener, "¿El proceso debe bloquearse? ", "Selección si el proceso que se esta ingresando debe bloquearse", TipoPanel.select);
		this.add(panelBloqueo);
		
		panelComunicacion = new PanelLayout(listener, "¿El proceso se counicara? ", "Selección si el proceso que se esta ingresando debe comunicarse", TipoPanel.select);
		this.add(panelComunicacion);
		
		panelPrioridad = new PanelLayout(listener, "Prioridad del proceso: ", "Escriba la prioridad del proceso que desea agregar, se aceptan n�meros en un rango [1,10], donde 10 es la priodidad m�s alta y 1 la m�s baja", TipoPanel.texto);
		this.add(panelPrioridad);
		
		panelCambioPrioridad = new PanelLayout(listener, "Cambie la prioridad: ", "Escriba la prioridad del proceso que desea agregar, se aceptan n�meros en un rango [1,10], donde 10 es la priodidad m�s alta y 1 la m�s baja", TipoPanel.texto);
		this.add(panelCambioPrioridad);
		
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
	
	

	public PanelLayout getPanelDestruir() {
		return panelDestruir;
	}

	public void setPanelDestruir(PanelLayout panelDestruir) {
		this.panelDestruir = panelDestruir;
	}

	public PanelLayout getPanelComunicacion() {
		return panelComunicacion;
	}

	public void setPanelComunicacion(PanelLayout panelComunicacion) {
		this.panelComunicacion = panelComunicacion;
	}
	
	
	public PanelLayout getPanelSuspender() {
		return panelSuspender;
	}

	public void setPanelSuspender(PanelLayout panelSuspender) {
		this.panelSuspender = panelSuspender;
	}

	public void limpiarTexto(){
            panelNombre.limpiarTexto();
            panelPrioridad.limpiarTexto();
            panelTiempo.limpiarTexto();
        }

}
