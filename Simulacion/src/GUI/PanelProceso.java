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

public class PanelProceso extends JPanel {

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

    public PanelProceso(Procesador procesador, ActionListener listener) {
        this.procesador = procesador;
        setLayout(new GridLayout(5, 1)); // El numero de filas, el numero de columnas

        // Nombre del proceso
        panelNombre = new PanelLayout(listener, "Nombre del proceso:", "Escriba el nombre del proceso que desea agregar, recuerde que el nombre ser� el identificador", TipoPanel.texto);
        this.add(panelNombre);

        // Tiempo del proceso
        panelTiempo = new PanelLayout(listener, "Tiempo de ejecucion del proceso: ", "Escriba el tiempo en segundos que se demorar el proceso en ejecucion, se aceptan numeros en el rango [1, 60]", TipoPanel.texto);
        this.add(panelTiempo);
        
        // tamanio del proceso
        panelTamanio = new PanelLayout(listener, "Tamanio del proceso: ", "Escriba la tamanioo del proceso que desea agregar, se aceptan solo numeros", TipoPanel.texto);
        this.add(panelTamanio);
        
                // Suspender Listo 
        panelParticion = new PanelLayout(listener, "Particion: ", "Seleccione a que particion pertenece el proceso que se esta ingresando ", TipoPanel.select);
        String[] opciones = {""};
        panelParticion.setOpciones(opciones);
        this.add(panelParticion);


//        // prioridad del proceso
//        panelPrioridad = new PanelLayout(listener, "Prioridad del proceso: ", "Escriba la prioridad del proceso que desea agregar, se aceptan n�meros en un rango [1,10], donde 10 es la priodidad m�s alta y 1 la m�s baja", TipoPanel.texto);
//        this.add(panelPrioridad);
//
//        // Cambio de prioridad del proceso
//        panelCambioPrioridad = new PanelLayout(listener, "Cambie la prioridad: ", "Escriba la prioridad del proceso que desea agregar, se aceptan n�meros en un rango [1,10], donde 10 es la priodidad m�s alta y 1 la m�s baja", TipoPanel.texto);
//        this.add(panelCambioPrioridad);
//
//        // Suspender Listo 
//        panelSuspenderListo = new PanelLayout(listener, "�El proceso debe pasar al estado Suspedido/Listo? ", "Selección si el proceso que se esta ingresando debe pasar al estado Suspedido/Listo, y si debe por el estado listo o el estados en ejecucion para llegar a dicho estado", TipoPanel.select);
//        String[] opciones2 = {"No", "Listo", "Ejecucion"};
//        panelSuspenderListo.setOpciones(opciones2);
//        this.add(panelSuspenderListo);
//
//        // Suspender Bloqueado
//        panelSuspenderBloqueado = new PanelLayout(listener, "�El proceso debe pasar al estado Suspedido/Bloqueado? ", "Selección si el proceso que se esta ingresando debe pasar al estado Suspedido/Bloqueado", TipoPanel.select);
//        String[] opciones1 = {"No", "Suspendido/Listo", "Bloqueado"};
//        panelSuspenderBloqueado.setOpciones(opciones1);
//        this.add(panelSuspenderBloqueado);
//
//        panelBloqueo = new PanelLayout(listener, "�El proceso debe bloquearse? ", "Selección si el proceso que se esta ingresando debe bloquearse", TipoPanel.select);
//        this.add(panelBloqueo);
//
//        panelComunicacion = new PanelLayout(listener, "�El proceso se comunicara? ", "Selección si el proceso que se esta ingresando debe comunicarse", TipoPanel.select);
//        String[] opciones = {""};
//        panelComunicacion.setOpciones(opciones);
//        this.add(panelComunicacion);
//
//        panelDestruir = new PanelLayout(listener, "�El proceso debe destruirse? ", "Selección si el proceso que se esta ingresando debe destruirse", TipoPanel.select);
//        this.add(panelDestruir);

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
    

}
