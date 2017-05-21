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

public class PanelBotonesResultados extends JPanel implements ActionListener{

    private PanelLayout panelNombre;
    private PanelLayout panelDestruir;
    private PanelLayout panelSuspenderListo;
    private PanelLayout panelSuspenderBloqueado;
    private PanelLayout panelPrioridad;
    private PanelLayout panelCambioPrioridad;
    private PanelLayout panelTiempo;
    private PanelLayout panelTamanio;
    private PanelLayout panelParticion;
    private PanelLayout panelBloqueo;
    private PanelLayout panelComunicacion;
    private PanelLayout panelBoton;

    //private PanelBoton panelBoton;
    private Procesador procesador;

    public PanelBotonesResultados(Procesador procesador) {
        this.procesador = procesador;
        
        ArrayList<Particion> particiones = procesador.getParticiones();

        setLayout(new GridLayout(particiones.size()+1, 1)); // El numero de filas, el numero de columnas
        for (int i = 0; i < particiones.size(); i++) {
        	PanelLayout panelBoton = new PanelLayout(this, "Boton", "boton", TipoPanel.boton);
        	
        	JButton boton = new JButton("Resultados Particion " + (i+1));
        	boton.addActionListener(this);
        	
        	panelBoton.setBoton(boton);
            this.add(panelBoton);
		}

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

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getActionCommand().equals("Particion1")) {
			System.out.println("sisas");
		}
		
	}
    

}
