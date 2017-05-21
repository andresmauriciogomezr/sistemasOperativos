package GUI;

import java.awt.BorderLayout;

import main.Particion;
import main.Procesador;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelProceso2 extends JPanel {

    private PanelLayout panelNombre;
    private PanelLayout panelTamanio;
    
    private PanelLayout panelDestruir;
    private PanelLayout panelSuspenderListo;
    private PanelLayout panelSuspenderBloqueado;
    private PanelLayout panelPrioridad;
    private PanelLayout panelCambioPrioridad;
    private PanelLayout panelTiempo;
    
    private PanelLayout panelParticion;
    private PanelLayout panelBloqueo;
    private PanelLayout panelComunicacion;
    private PanelLayout panelBoton;

    //private PanelBoton panelBoton;
    private Procesador procesador;

    public PanelProceso2(Procesador procesador, ActionListener listener) {
        this.procesador = procesador;
        setLayout(new GridLayout(5, 1)); // El numero de filas, el numero de columnas

        // Nombre del proceso
        panelNombre = new PanelLayout(listener, "Nombre de la particion:", "Escriba el nombre del proceso que desea agregar, recuerde que el nombre ser� el identificador", TipoPanel.texto);
        //this.add(panelNombre);

        // tamanio del proceso
        panelTamanio = new PanelLayout(listener, "Tamanio de la particion: ", "Escriba la tamanioo del proceso que desea agregar, se aceptan solo numeros", TipoPanel.texto);
        this.add(panelTamanio);
        
        //panelBoton = new PanelBoton(listener);
        panelBoton = new PanelLayout(listener, "", "", TipoPanel.boton);
        JButton boton = new JButton("Agregar Particion");
        boton.addActionListener(listener);
        panelBoton.setBoton(boton);        
        this.add(panelBoton);

    }
    
    public void limpiar(){
    	this.panelTamanio.limpiarTexto();
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

    public PanelLayout getPanelSuspenderBloqueado() {
        return panelSuspenderBloqueado;
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
        return panelSuspenderListo;
    }

    public void setPanelSuspender(PanelLayout panelSuspender) {
        this.panelSuspenderListo = panelSuspender;
    }

    public PanelLayout getPanelCambioPrioridad() {
        return panelCambioPrioridad;
    }

    public void setPanelCambioPrioridad(PanelLayout panelCambioPrioridad) {
        this.panelCambioPrioridad = panelCambioPrioridad;
    }

    public PanelLayout getPanelTamanio() {
        return panelTamanio;
    }

    public PanelLayout getPanelParticion() {
        return panelParticion;
    }

    public void limpiarTexto() {
        panelNombre.limpiarTexto();
        panelTamanio.limpiarTexto();
        panelTiempo.limpiarTexto();
    }
    
    public void setParticiones(ArrayList<Particion> lista ){
    	String[] opciones = new String[lista.size()]; 
    	for (int i = 0; i < lista.size(); i++) {
			opciones[i] = "Particion " + String.valueOf(lista.get(i).getIndex()+1);
		}
    	this.panelParticion.setOpciones(opciones);
    }

	public void setPanelTamanio(PanelLayout panelTamanio) {
		this.panelTamanio = panelTamanio;
	}
    
    

}
